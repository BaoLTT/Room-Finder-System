package com.roomfindingsystem.service.impl;

import com.roomfindingsystem.entity.RoomTypeEntity;
import com.roomfindingsystem.repository.RoomTypeRepository;
import com.roomfindingsystem.service.RoomTypeService;
import com.roomfindingsystem.dto.RoomTypeDto;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service("roomTypeService")
public class RoomTypeServiceImpl implements RoomTypeService {
    private final RoomTypeRepository roomTypeRepository;
    private final ModelMapper modelMapper;

    public RoomTypeServiceImpl(RoomTypeRepository roomTypeRepository, ModelMapper modelMapper){
        super();
        this.roomTypeRepository = roomTypeRepository;
        this.modelMapper = modelMapper;
    }
    @Override
    public List<RoomTypeDto> findAll() {
        List<RoomTypeEntity> roomTypeEntities = (List<RoomTypeEntity>) roomTypeRepository.findAll();
        return roomTypeEntities.stream().map(roomTypeEntity -> {
            return modelMapper.map(roomTypeEntity, RoomTypeDto.class);
        }).collect(Collectors.toList());
    }
}
