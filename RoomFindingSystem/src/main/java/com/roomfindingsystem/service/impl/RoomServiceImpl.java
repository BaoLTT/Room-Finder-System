package com.roomfindingsystem.service.impl;

import com.roomfindingsystem.entity.*;
import com.roomfindingsystem.reponsitory.*;
import com.roomfindingsystem.service.RoomService;

import com.roomfindingsystem.vo.*;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
@AllArgsConstructor
@Transactional
public class RoomServiceImpl implements RoomService {

    private final RoomRepository roomRepository;
    private final RoomTypeRepository roomTypeRepository;
    private final ModelMapper modelMapper;
    private final ServiceRoomRepository serviceRoomRepository;
    private final ServiceDetailRepository serviceDetailRepository;
        
    @Override
    public RoomEntity getRoomById(int roomId) {
        return roomRepository.getRoomById(roomId);

    }

    @Override
    public List<RoomImagesEntity> roomImageByRoomId(int roomId) {

        return roomRepository.getImageByRoomId(roomId);

    }

    @Override
    public List<ServiceDetailEntity> getServiceByRoomId(int roomId) {
		return roomRepository.getServiceByRoomId(roomId);
    }

    @Override
    public List<RoomDto> getAll() {
        List<RoomEntity> roomEntities = roomRepository.findAllRooms();

        List<RoomDto> roomDtos = roomEntities.stream().map(roomEntity -> {
            RoomDto roomDto = modelMapper.map(roomEntity, RoomDto.class);
            roomDto.setTypeName(roomTypeRepository.findById(roomEntity.getRoomType()).get().getTypeName());
            if (roomEntity.getStatusId() == 1) {
                roomDto.setStatus("ACTIVE");
            } else {
                roomDto.setStatus("INACTIVE");
            }
            List<ServiceDetailEntity> serviceDetailEntities = roomRepository.getServiceByRoomId(roomDto.getRoomId());

            StringBuilder servicesBuilder = new StringBuilder();

            for (ServiceDetailEntity serviceDetailEntity : serviceDetailEntities) {
                if (!servicesBuilder.isEmpty()) {
                    servicesBuilder.append(", ");
                }
                servicesBuilder.append(serviceDetailEntity.getServiceName());
            }
            roomDto.setServices(servicesBuilder.toString());

            return roomDto;
        }).toList();
        return roomDtos;
        }

    @Override
    public List<RoomHomeVo> viewRoomInHome() {
        List<RoomHomeVo> list = new ArrayList<>();
        if(!roomRepository.viewTop4Home().isEmpty()){
            if(roomRepository.viewTop4Home().size()<8) list = roomRepository.viewTop4Home();
            else for(int i = 0; i<8; i++){
                list.add(roomRepository.viewTop4Home().get(i));
            }
        }
        return list;

    }

    @Override
    public RoomDto findById(Integer id) {
        Optional<RoomEntity> room = roomRepository.findById(id);
        if (room.isEmpty()) {
            return null;
        }
        RoomEntity roomEntity = room.get();
        RoomDto roomDto = modelMapper.map(roomEntity, RoomDto.class);
        roomDto.setTypeName(roomTypeRepository.findById(roomEntity.getRoomType()).get().getTypeName());
        roomDto.setTypeId(roomEntity.getRoomType());
        if (roomEntity.getStatusId() == 1) {
            roomDto.setStatus("ACTIVE");
        } else {
            roomDto.setStatus("INACTIVE");
        }
        List<ServiceDetailEntity> serviceDetailEntities = roomRepository.getServiceByRoomId(roomDto.getRoomId());

        StringBuilder servicesBuilder = new StringBuilder();
        List<String> serviceNames = new ArrayList<>();

        for (ServiceDetailEntity serviceDetailEntity : serviceDetailEntities) {
            if (!servicesBuilder.isEmpty()) {
                servicesBuilder.append(", ");
            }
            servicesBuilder.append(serviceDetailEntity.getServiceName());
            serviceNames.add(serviceDetailEntity.getServiceName());
        }
        roomDto.setServices(servicesBuilder.toString());
        List<ServiceDto> serviceDtos = serviceDetailEntities.stream().map(serviceDetailEntity -> {
            return modelMapper.map(serviceDetailEntity, ServiceDto.class);
        }).toList();
        roomDto.setServiceDtos(serviceDtos);
        roomDto.setServiceNames(serviceNames);
        return roomDto;
    }

    @Override
    public void update(RoomDto roomDto) {
        RoomEntity room = roomRepository.findById(roomDto.getRoomId()).get();

        RoomEntity saveRoom = new RoomEntity();

        saveRoom.setRoomId(room.getRoomId());
        saveRoom.setArea(roomDto.getArea());
        saveRoom.setCreatedDate(room.getCreatedDate());
        saveRoom.setCreatedBy(room.getCreatedBy());
        saveRoom.setDescription(roomDto.getDescription());
        saveRoom.setHouseId(room.getHouseId());
        saveRoom.setLastModifiedBy(room.getLastModifiedBy());
        saveRoom.setLastModifiedDate(LocalDate.now());
        saveRoom.setPrice(roomDto.getPrice());
        saveRoom.setRoomName(roomDto.getRoomName());
        saveRoom.setRoomType(room.getRoomType());
        if (Objects.equals(roomDto.getStatus(), "ACTIVE")) {
            saveRoom.setStatusId(1);
        } else {
            saveRoom.setStatusId(0);
        }
        List<ServiceRoomEntity> serviceRoomEntities = serviceRoomRepository.findAllByRoomId(roomDto.getRoomId());
        for (ServiceRoomEntity serviceRoomEntity : serviceRoomEntities) {
            serviceRoomRepository.deleteByRoomIdAndServiceId(roomDto.getRoomId(), serviceRoomEntity.getServiceId());
        }
        for (ServiceDto serviceDto : roomDto.getServiceDtos()) {
            ServiceRoomEntity serviceRoomEntity = new ServiceRoomEntity();
            serviceRoomEntity.setServiceId(serviceDto.getServiceId());
            serviceRoomEntity.setRoomId(saveRoom.getRoomId());
            serviceRoomRepository.save(serviceRoomEntity);
        }
        roomRepository.save(saveRoom);
    }

    @Override
    public void deleteById(Integer id) {
        List<ServiceRoomEntity> serviceRoomEntities = serviceRoomRepository.findAllByRoomId(Math.toIntExact(id));
        for (ServiceRoomEntity serviceRoomEntity : serviceRoomEntities) {
            serviceRoomRepository.deleteByRoomIdAndServiceId(Math.toIntExact(id), serviceRoomEntity.getServiceId());
        }
        roomRepository.deleteById(id);
    }

    @Override
    public void save(RoomDto roomDto) {
        RoomEntity saveRoom = new RoomEntity();
        saveRoom.setArea(roomDto.getArea());
        saveRoom.setCreatedDate(LocalDate.now());
        saveRoom.setCreatedBy(1);
        saveRoom.setDescription(roomDto.getDescription());
        saveRoom.setHouseId(1);
        saveRoom.setLastModifiedBy(1);
        saveRoom.setLastModifiedDate(LocalDate.now());
        saveRoom.setPrice(roomDto.getPrice());
        saveRoom.setRoomName(roomDto.getRoomName());
        saveRoom.setRoomType(roomDto.getTypeId());
        if (Objects.equals(roomDto.getStatus(), "ACTIVE")) {
            saveRoom.setStatusId(1);
        } else {
            saveRoom.setStatusId(0);
        }

        roomRepository.save(saveRoom);

        for (ServiceDto serviceDto : roomDto.getServiceDtos()) {
            ServiceRoomEntity serviceRoomEntity = new ServiceRoomEntity();
            serviceRoomEntity.setServiceId(serviceDto.getServiceId());
            serviceRoomEntity.setRoomId(saveRoom.getRoomId());
            serviceRoomRepository.save(serviceRoomEntity);
        }
    }

    @Override
    public int countRoom() {
        return 0;
    }

    @Override
    public List<RoomDto> findRoom1(int i, int i1, String roomName, List<Integer> listType, int offset, int pageSize) {
        return null;
    }
}
