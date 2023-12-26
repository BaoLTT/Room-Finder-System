package com.roomfindingsystem.service.impl;

import com.roomfindingsystem.entity.*;
import com.roomfindingsystem.repository.*;
import com.roomfindingsystem.service.RoomHistoryService;
import com.roomfindingsystem.service.RoomService;

import com.roomfindingsystem.dto.*;
import jakarta.persistence.Tuple;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;
import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.*;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
@Transactional
public class RoomServiceImpl implements RoomService {

    private final RoomRepository roomRepository;
    private final RoomTypeRepository roomTypeRepository;
    private final ModelMapper modelMapper;
    private final ServiceRoomRepository serviceRoomRepository;
    private final ServiceDetailRepository serviceDetailRepository;
    private final RoomImageRepository roomImageRepository;
    private final HouseRepository houseRepository;
    private final GcsService gcsService;
    private final RoomHistoryService roomHistoryService ;

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
        return roomRepository.getAllServicesByRoomId(roomId);
    }

    @Override
    public List<RoomDto> getAll() {
        List<RoomDto> roomDtos = roomRepository.findRoomsDetail();
        for (RoomDto roomDto : roomDtos) {
            StringBuilder servicesBuilder = new StringBuilder();
            List<ServiceDetailEntity> serviceDetailEntities = roomRepository.getServiceByRoomId(roomDto.getRoomId());
            for (ServiceDetailEntity serviceDetailEntity : serviceDetailEntities) {
                if (!servicesBuilder.isEmpty()) {
                    servicesBuilder.append(", ");
                }
                servicesBuilder.append(serviceDetailEntity.getServiceName());
            }
            roomDto.setServices(servicesBuilder.toString());
        }
        return roomDtos;
    }

    @Override
    public int countRoom(int houseId) {
        return roomRepository.countRoomEntityByHouseId(houseId);
    }

    @Override
    public List<RoomHomeDto> viewRoomInHome() {
        List<Tuple> tuples = roomRepository.viewRoomInHome();
        List<RoomHomeDto> roomHomeDtos = new ArrayList<>();
        List<String> imageLinks;

        for (Tuple tuple : tuples) {
            RoomHomeDto roomHomeDto = new RoomHomeDto();
            roomHomeDto.setRoomId(tuple.get("RoomID", Integer.class));
            roomHomeDto.setRoomName(tuple.get("Room_Name", String.class));
            roomHomeDto.setHouseName(tuple.get("House_Name", String.class));
            roomHomeDto.setRoomType(tuple.get("Type_Name", String.class));
            roomHomeDto.setAddressDetail(tuple.get("Address_Details", String.class));
            String imageLink = (tuple.get("Image_Link", String.class));
            if (imageLink == null) {
                List<HouseImagesEntity> houseImagesEntities = roomRepository.getImageHouseByRoomId(roomHomeDto.getRoomId());
                if(!houseImagesEntities.isEmpty()) roomHomeDto.setRoomImageLink(houseImagesEntities.get(0).getImageLink()); else roomHomeDto.setRoomImageLink(null);
            } else {
                imageLinks = Arrays.asList(imageLink.split(","));
                roomHomeDto.setRoomImageLink(imageLinks.get(0));
            }
            roomHomeDto.setProvince(tuple.get("province_name", String.class));
            roomHomeDto.setDistrict(tuple.get("district_name", String.class));
            roomHomeDto.setWard(tuple.get("ward_name", String.class));
            roomHomeDto.setArea(tuple.get("area", Double.class));
            roomHomeDto.setPrice(tuple.get("price", Integer.class));

            roomHomeDtos.add(roomHomeDto);
        }
        return roomHomeDtos;

    }

    @Override
    public List<RoomHomeDto> viewRoomNearPrice(int price, int id) {
        List<Tuple> tuples = roomRepository.findRoomsNearPrice(BigDecimal.valueOf(price), id);
        List<RoomHomeDto> roomHomeDtos = new ArrayList<>();
        List<String> imageLinks;


        for (Tuple tuple : tuples) {
            RoomHomeDto roomHomeDto = new RoomHomeDto();
            roomHomeDto.setRoomId(tuple.get("RoomID", Integer.class));
            roomHomeDto.setRoomName(tuple.get("Room_Name", String.class));
            roomHomeDto.setHouseName(tuple.get("House_Name", String.class));
            roomHomeDto.setRoomType(tuple.get("Type_Name", String.class));
            roomHomeDto.setAddressDetail(tuple.get("Address_Details", String.class));
            String imageLink = (tuple.get("Image_Link", String.class));
            if (imageLink == null) {
                List<HouseImagesEntity> houseImagesEntities = roomRepository.getImageHouseByRoomId(roomHomeDto.getRoomId());
                roomHomeDto.setRoomImageLink(houseImagesEntities.get(0).getImageLink());
            } else {
                imageLinks = Arrays.asList(imageLink.split(","));
                roomHomeDto.setRoomImageLink(imageLinks.get(0));
            }
            roomHomeDto.setProvince(tuple.get("province_name", String.class));
            roomHomeDto.setDistrict(tuple.get("district_name", String.class));
            roomHomeDto.setWard(tuple.get("ward_name", String.class));
            roomHomeDto.setArea(tuple.get("area", Double.class));
            roomHomeDto.setPrice(tuple.get("price", Integer.class));

            roomHomeDtos.add(roomHomeDto);
        }
        return roomHomeDtos;
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
        roomDto.setFloor(roomEntity.getFloor());
        if (roomEntity.getStatusid() == 1) {
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

        List<RoomImagesEntity> roomImagesEntities = roomImageRepository.getImageByRoomId(id);
        List<RoomImageDto> roomImageDtos = roomImagesEntities.stream().map(roomImagesEntity -> {
            return modelMapper.map(roomImagesEntity, RoomImageDto.class);
        }).toList();
        roomDto.setImgs(roomImageDtos);
        roomDto.setServiceDtos(serviceDtos);
        roomDto.setServiceNames(serviceNames);
        return roomDto;
    }

    @Override
    public void update(RoomDto roomDto, MultipartFile[] files) throws IOException {
        RoomEntity room = roomRepository.findById(roomDto.getRoomId()).get();
        RoomEntity saveRoom = new RoomEntity();
        List<RoomImagesEntity> roomImagesEntities = roomImageRepository.getImageByRoomId(roomDto.getRoomId());
        int i = roomImagesEntities.size() + 1;
        long timestamp = System.currentTimeMillis();

        // Chuyển định dạng thời gian thành chuỗi
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyyMMdd_HHmmss");
        String formattedTimestamp = dateFormat.format(new Date(timestamp));
        for (MultipartFile file : files) {
            if (!file.isEmpty()) {
                RoomImagesEntity roomImagesEntity = new RoomImagesEntity();
                byte[] imageBytes = file.getBytes();
                gcsService.uploadImage("rfs_bucket", "Room/room_" + formattedTimestamp+"_"+ i + "_" + room.getRoomid() + ".jpg", imageBytes);
                roomImagesEntity.setImageLink("/rfs_bucket/Room/" + "room_" + formattedTimestamp+"_"+ i + "_" + room.getRoomid() + ".jpg");
                i++;
                roomImagesEntity.setRoomId(roomDto.getRoomId());
                roomImagesEntity.setCreatedDate(LocalDate.now());
                roomImagesEntity.setLastModifiedDate(LocalDate.now());
                roomImageRepository.save(roomImagesEntity);
            }
        }
        saveRoom.setRoomid(room.getRoomid());
        saveRoom.setArea(roomDto.getArea());
        saveRoom.setFloor(roomDto.getFloor());
        saveRoom.setCreatedDate(room.getCreatedDate());
        saveRoom.setCreatedBy(room.getCreatedBy());
        saveRoom.setDescription(roomDto.getDescription());
        saveRoom.setHouseid(room.getHouseid());
        saveRoom.setLastModifiedBy(room.getLastModifiedBy());
        saveRoom.setLastModifiedDate(LocalDate.now());
        saveRoom.setPrice(roomDto.getPrice());
        saveRoom.setRoomName(roomDto.getRoomName());
        saveRoom.setRoomType(roomDto.getTypeId());
        saveRoom.setStatusUpdateDate(room.getStatusUpdateDate());
        if (Objects.equals(roomDto.getStatus(), "ACTIVE")) {
            saveRoom.setStatusId(1);
        } else {
            saveRoom.setStatusId(0);
        }
        List<ServiceRoomEntity> serviceRoomEntities = serviceRoomRepository.findAllByRoomId(roomDto.getRoomId());
        for (ServiceRoomEntity serviceRoomEntity : serviceRoomEntities) {
            serviceRoomRepository.deleteByRoomIdAndServiceId(roomDto.getRoomId(), serviceRoomEntity.getServiceId());
        }
        if (roomDto.getServiceDtos() != null) {
            for (ServiceDto serviceDto : roomDto.getServiceDtos()) {
                ServiceRoomEntity serviceRoomEntity = new ServiceRoomEntity();
                serviceRoomEntity.setServiceId(serviceDto.getServiceId());
                serviceRoomEntity.setRoomId(saveRoom.getRoomid());
                serviceRoomRepository.save(serviceRoomEntity);
            }
        }

        //baoltt history
        int statusUpdate = 1;
        if (Objects.equals(roomDto.getStatus(), "ACTIVE")) {
            statusUpdate = 1;
        } else {
            statusUpdate = 0;
        }
        roomHistoryService.updateRoomStatus(roomDto.getRoomId(), statusUpdate);


        roomRepository.save(saveRoom);
    }

    @Override
    public void deleteById(Integer id) {
        List<ServiceRoomEntity> serviceRoomEntities = serviceRoomRepository.findAllByRoomId(Math.toIntExact(id));
        for (ServiceRoomEntity serviceRoomEntity : serviceRoomEntities) {
            serviceRoomRepository.deleteByRoomIdAndServiceId(Math.toIntExact(id), serviceRoomEntity.getServiceId());
        }
        List<RoomImagesEntity> roomImagesEntities = roomImageRepository.getImageByRoomId(id);
        for (RoomImagesEntity roomImagesEntity : roomImagesEntities) {
            roomImageRepository.deleteByImageId(roomImagesEntity.getImageId());
        }
        roomRepository.deleteById(id);
    }

    @Override
    public void saveRoomAdmin(RoomDto roomDto, MultipartFile[] files) throws IOException {
        RoomEntity saveRoom = new RoomEntity();
        saveRoom.setArea(roomDto.getArea());
        saveRoom.setCreatedDate(LocalDate.now());
        saveRoom.setFloor(roomDto.getFloor());
        saveRoom.setCreatedBy(roomDto.getCreatedBy());
        saveRoom.setDescription(roomDto.getDescription());
        saveRoom.setHouseid(roomDto.getHouseId());
        saveRoom.setLastModifiedBy(roomDto.getLastModifiedBy());
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
//        roomHistoryService.addRoomHistory(saveRoom.getRoomName(), saveRoom.getHouseid());
        if (roomDto.getServiceDtos() != null) {
            for (ServiceDto serviceDto : roomDto.getServiceDtos()) {
                ServiceRoomEntity serviceRoomEntity = new ServiceRoomEntity();
                serviceRoomEntity.setServiceId(serviceDto.getServiceId());
                serviceRoomEntity.setRoomId(saveRoom.getRoomid());
                serviceRoomRepository.save(serviceRoomEntity);
            }
        }
        long timestamp = System.currentTimeMillis();

        // Chuyển định dạng thời gian thành chuỗi
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyyMMdd_HHmmss");
        String formattedTimestamp = dateFormat.format(new Date(timestamp));
        int i = 1;
        for (MultipartFile file : files) {
            if (!file.isEmpty()) {
                RoomImagesEntity roomImagesEntity = new RoomImagesEntity();
                byte[] imageBytes = file.getBytes();
                gcsService.uploadImage("rfs_bucket", "Room/room_" + formattedTimestamp+"_"+ i + "_" + saveRoom.getRoomid() + ".jpg", imageBytes);
                roomImagesEntity.setImageLink("/rfs_bucket/Room/" + "room_" + formattedTimestamp+"_"+ i + "_" + saveRoom.getRoomid() + ".jpg");
                i++;
                roomImagesEntity.setRoomId(saveRoom.getRoomid());
                roomImagesEntity.setCreatedDate(LocalDate.now());
                roomImagesEntity.setLastModifiedDate(LocalDate.now());
                roomImageRepository.save(roomImagesEntity);
            }
        }
    }

    @Override
    public void saveRoomLandlord(RoomDto roomDto, MultipartFile[] files) throws IOException {
        RoomEntity saveRoom = new RoomEntity();
        saveRoom.setArea(roomDto.getArea());
        saveRoom.setCreatedDate(LocalDate.now());
        saveRoom.setCreatedBy(roomDto.getCreatedBy());
        saveRoom.setFloor(roomDto.getFloor());
        saveRoom.setDescription(roomDto.getDescription());
        saveRoom.setHouseid(roomDto.getHouseId());
        saveRoom.setLastModifiedBy(roomDto.getLastModifiedBy());
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
        roomHistoryService.addRoomHistory(saveRoom.getRoomName(), saveRoom.getHouseid());
        if (roomDto.getServiceDtos() != null) {
            for (ServiceDto serviceDto : roomDto.getServiceDtos()) {
                ServiceRoomEntity serviceRoomEntity = new ServiceRoomEntity();
                serviceRoomEntity.setServiceId(serviceDto.getServiceId());
                serviceRoomEntity.setRoomId(saveRoom.getRoomid());
                serviceRoomRepository.save(serviceRoomEntity);
            }
        }
        long timestamp = System.currentTimeMillis();

        // Chuyển định dạng thời gian thành chuỗi
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyyMMdd_HHmmss");
        String formattedTimestamp = dateFormat.format(new Date(timestamp));
        int i = 1;
        for (MultipartFile file : files) {
            if (!file.isEmpty()) {
                RoomImagesEntity roomImagesEntity = new RoomImagesEntity();
                byte[] imageBytes = file.getBytes();
                gcsService.uploadImage("rfs_bucket", "Room/room_" + formattedTimestamp+"_"+ i + "_" + saveRoom.getRoomid() + ".jpg", imageBytes);
                roomImagesEntity.setImageLink("/rfs_bucket/Room/" + "room_" + formattedTimestamp+"_"+ i + "_" + saveRoom.getRoomid() + ".jpg");
                roomImagesEntity.setRoomId(saveRoom.getRoomid());
                roomImagesEntity.setCreatedDate(LocalDate.now());
                roomImagesEntity.setLastModifiedDate(LocalDate.now());
                roomImageRepository.save(roomImagesEntity);
            }
        }
    }

    @Override
    public int countRoom(int min1, int max1, int min2, int max2, int min3, int max3,  String roomName, List<Integer> type, List<Integer> floor) {
        return roomRepository.countRoom(min1, max1, min2, max2, min3, max3, roomName, type, floor);
    }


    @Override
    public List<RoomHouseDetailDto> viewRoomInHouse(int houseId) {
        List<Tuple> tuples = roomRepository.viewRoomInHouseDetail(houseId);
        List<RoomHouseDetailDto> roomDtos = new ArrayList<>();
        List<String> roomList = null;
        List<String> idList = null;
        List<String> statusList = null;
        List<String> priceList = null;
        Set<String> uniquePairs = new HashSet<>();

        for (Tuple tuple : tuples) {
//            int houseId = tuple.get("HouseID", Integer.class);
            Integer typeId = tuple.get("room_type", Integer.class);
            Integer floor = tuple.get("floor", Integer.class);

            // Kiểm tra xem cặp (HouseID, TypeID) đã xuất hiện chưa
//            if (!uniquePairs.contains(pair)) {
                RoomHouseDetailDto roomHouseDto = new RoomHouseDetailDto();
                roomHouseDto.setRoomId(tuple.get("RoomID", Integer.class));
                roomHouseDto.setRoomName(tuple.get("room_name", String.class));
                roomHouseDto.setTypeId(typeId);
                roomHouseDto.setTypeName(tuple.get("type_name", String.class));
                roomHouseDto.setHouseId(houseId);
                roomHouseDto.setHouseName(tuple.get("house_name", String.class));
                roomHouseDto.setFloor(floor);
                Integer intPrice = tuple.get("price",Integer.class);
                // Định dạng số nguyên với dấu phẩy sau mỗi ba chữ số
                DecimalFormat decimalFormat = new DecimalFormat("#,###");

                // Áp dụng định dạng cho số
                String formattedPrice = decimalFormat.format(intPrice);
                roomHouseDto.setPrice(formattedPrice);
                roomHouseDto.setStatus(tuple.get("statusid",Integer.class));

//                String roomName = tuple.get("room_list", String.class);
//                if(roomName!=null) roomList = Arrays.asList(roomName.split(","));
//
//
//                String idName = tuple.get("id_list", String.class);
//                if(idName!=null) idList = Arrays.asList(idName.split(","));
//
//                String statusId = tuple.get("status_list", String.class);
//                if(statusId!=null) statusList = Arrays.asList(statusId.split(","));
//
//                String price = tuple.get("price_list", String.class);
//                if(statusId!=null) priceList = Arrays.asList(statusId.split(","));
//
//                List<String> combinedList = new ArrayList<>();
//                if (roomName == null) {
//                    roomHouseDto.setRoomList(null);
//                } else {
//                    for (int i = 0; i < roomList.size(); i++) {
//                        String combinedValue = roomList.get(i) + "-" + idList.get(i)+ "-" + statusList.get(i)+ "-" + priceList.get(i);
//                        combinedList.add(combinedValue);
//                    }
//                    roomHouseDto.setRoomList(combinedList);
//                }






                roomDtos.add(roomHouseDto);

            }

        // Sắp xếp danh sách phòng theo tầng và thể loại phòng
        Comparator<RoomHouseDetailDto> comparator = Comparator
                .comparing(RoomHouseDetailDto::getFloor)
                .thenComparing(RoomHouseDetailDto::getTypeId);

        return roomDtos.stream()
                .sorted(comparator)
                .collect(Collectors.toList());
    }

    @Override
    public String getRoomNameById(String id) {
        return roomRepository.getRoomNameById(id);
    }

    @Override
    public List<RoomDtoN> findRoom1(int min1, int max1, int min2, int max2, int min3, int max3, String roomName, List<Integer> type, int pageIndex, int pageSize, List<Integer> floor) {
        List<Tuple> tuples = roomRepository.getRoomList(min1, max1, min2, max2, min3, max3, roomName, type, pageIndex, pageSize, floor);
        List<RoomDtoN> roomDtos = new ArrayList<>();
        List<String> imageLinks;
        for (Tuple tuple : tuples) {
            RoomDtoN roomDto = new RoomDtoN();
            roomDto.setRoomId(tuple.get("roomid", Integer.class));
            roomDto.setRoomName(tuple.get("room_name", String.class));
            roomDto.setHouseName(tuple.get("house_name", String.class));
            roomDto.setPrice(tuple.get("price", Integer.class));
            roomDto.setRoomType(tuple.get("type_name", String.class));
            String imageLink = (tuple.get("images", String.class));
            roomDto.setFloor(tuple.get("floor", Integer.class).toString());
            if (imageLink == null) {
                List<HouseImagesEntity> houseImagesEntities = roomRepository.getImageHouseByRoomId(roomDto.getRoomId());
                roomDto.setRoomImages(houseImagesEntities.get(0).getImageLink());
            } else {
                imageLinks = Arrays.asList(imageLink.split(","));
                roomDto.setRoomImages(imageLinks.get(0));
            }


            roomDtos.add(roomDto);
        }
        return roomDtos;
    }
    public List<String> findAllDistinctFloors(){
        return roomRepository.findAllDistinctFloors();
    }

    @Override
    public void importRooms(RoomDto roomDto,MultipartFile file) {
        InputStream inputStream = null;
        try {
            inputStream = file.getInputStream();
        } catch (IOException e) {
            e.printStackTrace();
        }

        Workbook workbook = null;
        try {
            workbook = new XSSFWorkbook(inputStream);
        } catch (IOException e) {
            e.printStackTrace();
        }

        Sheet sheet = workbook.getSheetAt(0);

        List<RoomEntity> rooms = new ArrayList<>();

        int lastRowNum = sheet.getLastRowNum();
        int startRow = 3;
        for (int i = startRow; i <= lastRowNum - 3; i++) {
            try {
                RoomEntity room = new RoomEntity();
                Row row = sheet.getRow(i);

                Cell cellName = row.getCell(0);
                room.setRoomName(cellName.getStringCellValue());

                Cell cellType = row.getCell(1);
                int typeId = (int) cellType.getNumericCellValue();
                room.setRoomType(typeId);

                Cell cellFloor = row.getCell(2);
                Integer floor =(int) cellFloor.getNumericCellValue();
                room.setFloor(floor);

                Cell cellArea = row.getCell(3);
                Double area = cellArea.getNumericCellValue();
                room.setArea(area);

                Cell cellPrice = row.getCell(4);
                Integer price = (int) cellPrice.getNumericCellValue();
                room.setPrice(price);

                Cell cellDesc = row.getCell(5);
                room.setDescription(cellDesc.getStringCellValue());
                room.setStatusId(1);
                room.setHouseid(roomDto.getHouseId());
                room.setCreatedBy(roomDto.getCreatedBy());
                room.setLastModifiedBy(roomDto.getLastModifiedBy());
                room.setCreatedDate(LocalDate.now());
                room.setLastModifiedDate(LocalDate.now());

                Cell cellServices = row.getCell(6);
                String serviceString = cellServices.getStringCellValue();
                String[] services = serviceString.split(",");


                roomRepository.save(room);

                RoomEntity roomEntity = roomRepository.getRoomByHouseIdAndName(cellName.getStringCellValue(), roomDto.getHouseId());
                for (String service : services) {
                    service = service.trim();
                    ServiceDetailEntity serviceDetailEntity = serviceDetailRepository.findByServiceName(service).get();
                    ServiceRoomEntity serviceRoomEntity = new ServiceRoomEntity();
                    serviceRoomEntity.setServiceId(serviceDetailEntity.getServiceId());
                    serviceRoomEntity.setRoomId(roomEntity.getRoomid());
                    serviceRoomRepository.save(serviceRoomEntity);
                }
            } catch (Exception ex) {
            }
        }
    }

    @Override
    public List<RoomAdminDashboardDto> getRoomStatusInAdminDashboard() {
        List<Tuple> tuples = roomRepository.getRoomStatusInAdminDashboard();
        List<RoomAdminDashboardDto> list = new ArrayList<>();
        for (Tuple tuple : tuples) {
            RoomAdminDashboardDto roomAdminDashboardDto = new RoomAdminDashboardDto();
            roomAdminDashboardDto.setRoomId(tuple.get("RoomId", Integer.class));
            roomAdminDashboardDto.setHouseName(tuple.get("House_Name", String.class));
            roomAdminDashboardDto.setRoomName(tuple.get("Room_Name", String.class));
            roomAdminDashboardDto.setFullName(tuple.get("full_Name", String.class));
            Integer statusIDint = tuple.get("statusId", Integer.class);
            if (statusIDint != null) {
                if (statusIDint == 1) {
                    roomAdminDashboardDto.setStatus("Còn trống");
                } else if (statusIDint == 0) {
                    roomAdminDashboardDto.setStatus("Hết phòng");
                }
            } else statusIDint = 1;
            java.sql.Date sqlDate = (java.sql.Date) tuple.get("status_update_date", Date.class);
            if (sqlDate == null) {
                roomAdminDashboardDto.setStatusUpdateDate(null);
            } else {
                LocalDate localDate = sqlDate.toLocalDate();
                roomAdminDashboardDto.setStatusUpdateDate(localDate);
            }
            list.add(roomAdminDashboardDto);
        }
        list.sort((room1, room2) -> {
            LocalDate currentDate = LocalDate.now();
            LocalDate date1 = room1.getStatusUpdateDate() != null ? room1.getStatusUpdateDate() : currentDate;
            LocalDate date2 = room2.getStatusUpdateDate() != null ? room2.getStatusUpdateDate() : currentDate;
            return currentDate.compareTo(date1) - currentDate.compareTo(date2);
        });
        return list;
    }

    @Transactional
    public void updateStatusDate(int roomId, int statusId) {
        RoomEntity room = roomRepository.findById(roomId).orElse(null);
        if (room != null) {
            roomHistoryService.updateRoomStatus(roomId,statusId);
            room.setStatusUpdateDate(LocalDate.now());
            room.setStatusId(statusId);
            roomRepository.save(room);

        }
    }

    @Override
    public void deleteRoomImage(Integer imageId) {
        roomImageRepository.deleteByImageId(imageId);
    }

    @Override
    public List<RoomDto> getRoomsInHouse(int houseId) {
        List<RoomDto> roomDtos = roomRepository.findRoomsInHouse(houseId);
        for (RoomDto roomDto : roomDtos) {
            StringBuilder servicesBuilder = new StringBuilder();
            List<ServiceDetailEntity> serviceDetailEntities = roomRepository.getServiceByRoomId(roomDto.getRoomId());
            for (ServiceDetailEntity serviceDetailEntity : serviceDetailEntities) {
                if (!servicesBuilder.isEmpty()) {
                    servicesBuilder.append(", ");
                }
                servicesBuilder.append(serviceDetailEntity.getServiceName());
            }
            roomDto.setServices(servicesBuilder.toString());
        }
        return roomDtos;
    }

    @Override
    public int countEmptyRoom() {
        return roomRepository.countEmptyRoom();
    }

    @Override
    public int countInhabitedRoom() {
        return roomRepository.countInhabitedRoom();
    }
}