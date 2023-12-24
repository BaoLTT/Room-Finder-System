package com.roomfindingsystem.service.impl;

import com.roomfindingsystem.dto.HouseLandlordVo;
import com.roomfindingsystem.dto.HouseManagerTypeVo;
import com.roomfindingsystem.entity.HouseImagesEntity;
import com.roomfindingsystem.entity.HousesEntity;
import com.roomfindingsystem.entity.RoomImagesEntity;
import com.roomfindingsystem.entity.ServiceHouseEntity;
import com.roomfindingsystem.repository.HouseImageRepository;
import com.roomfindingsystem.repository.HouseManagerRepository;
import com.roomfindingsystem.repository.ServiceHouseRepository;
import com.roomfindingsystem.service.HouseLandlordService;
import com.roomfindingsystem.service.HouseManagerService;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Date;
import java.util.List;

@Service("houseManagerService")
public class HouseManagerServiceImpl implements HouseManagerService {
    private HouseManagerRepository houseManagerRepository;

    public HouseManagerServiceImpl(HouseManagerRepository houseManagerRepository){
        super();
        this.houseManagerRepository = houseManagerRepository;
    }
    @Autowired
    ServiceHouseRepository serviceHouseRepository;
    @Autowired
    GcsService gcsService;
    @Autowired
    HouseImageRepository houseImageRepository;
    @Autowired
    private HouseLandlordService houseLandlordService;

    @Override
    public List<HouseManagerTypeVo> findHouseManager() {
        return houseManagerRepository.findHouseManager();
    }

    @Override
    public boolean deleteHouse(Integer id) {
        if ( houseManagerRepository.findById(id).isEmpty()) {
            System.err.println("House with id: "+ id +" not found!");
        }
        houseManagerRepository.deleteById(id);
        return true;
    }

    @Override
    public HouseManagerTypeVo findHouseById(Integer id) {
        HouseManagerTypeVo house = houseManagerRepository.findHouseById(id);
        return house;
    }

    @Override
    public void insertHouse(HouseLandlordVo house,int addressID,MultipartFile[] files) throws IOException {
        LocalDate createdDate = LocalDate.now();
        HousesEntity housesEntity = new HousesEntity();
        housesEntity.setHouseId(house.getHouseID());
        housesEntity.setHouseName(house.getHouseName().trim().replaceAll("\\s+", " "));
        housesEntity.setDescription(house.getDescription().trim().replaceAll("\\s+", " "));
        housesEntity.setCreatedDate(createdDate);
        housesEntity.setStar(house.getStar());
        housesEntity.setCreatedBy(house.getCreatedBy());
        housesEntity.setTypeHouseId(house.getTypeHouseID());
        housesEntity.setStatus(house.getStatus());
        housesEntity.setAddressId(addressID);
        housesEntity.setUserId(house.getUserID());
        housesEntity.setLastModifiedBy(house.getLastModifiedBy());
        housesEntity.setLastModifiedDate(createdDate);
        //map
        housesEntity.setLatitude(house.getLatitude());
        housesEntity.setLongitude(house.getLongitude());
        houseManagerRepository.save(housesEntity);
        List<String> service = house.getService();
        if(service != null && !service.isEmpty()){
            for(int i =0; i<house.getService().size();i++){
                ServiceHouseEntity serviceHouseEntity = new ServiceHouseEntity();
                serviceHouseEntity.setHouseId( housesEntity.getHouseId());
                int serviceid = Integer.parseInt(house.getService().get(i));
                serviceHouseEntity.setServiceId(serviceid);

                serviceHouseRepository.save(serviceHouseEntity);
            }
        }

        long timestamp = System.currentTimeMillis();

        // Chuyển định dạng thời gian thành chuỗi
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyyMMdd_HHmmss");
        String formattedTimestamp = dateFormat.format(new Date(timestamp));

        int i = 1;
        for (MultipartFile file : files) {
            if (!file.isEmpty()) {
                HouseImagesEntity houseImagesEntity = new HouseImagesEntity();
                byte[] imageBytes = file.getBytes();
                gcsService.uploadImage("rfs_bucket", "House/house_" + formattedTimestamp+"_"+ i + "_"+housesEntity.getHouseId()+".jpg", imageBytes);
                houseImagesEntity.setImageLink("/rfs_bucket/House/"+"house_"+formattedTimestamp+"_"+ i + "_"+housesEntity.getHouseId()+".jpg");
                i++;
                houseImagesEntity.setHouseId(housesEntity.getHouseId());
                houseImagesEntity.setCreatedDate(LocalDate.now());
                houseImageRepository.save(houseImagesEntity);
            }else{
                    // If files are null or empty, set a default image
                    HouseImagesEntity defaultImage = new HouseImagesEntity();
                    defaultImage.setImageLink("/rfs_bucket/House/housenull.jpg");
                    defaultImage.setHouseId(housesEntity.getHouseId());
                    defaultImage.setCreatedDate(LocalDate.now());
                    houseImageRepository.save(defaultImage);

            }
        }
    }

    @Override
    public HousesEntity getLastHouse() {
        return houseManagerRepository.getLastHouse();
    }

    @Override
    public void deleteImageById(int imageId) {
        houseImageRepository.deleteById(imageId);
    }


    @Transactional
    @Override
    public void updateHouse(HouseLandlordVo houses, int houseID,List<Integer> service,MultipartFile[] files) throws IOException {
        LocalDate localDate = LocalDate.now();
        List<HouseImagesEntity> houseImagesEntity = houseImageRepository.getImageByHouseId(houseID);
        houseManagerRepository.updateHouse(houses.getHouseName().trim().replaceAll("\\s+", " "), houses.getTypeHouseID(),houses.getDescription().trim().replaceAll("\\s+", " "),houses.getLastModifiedBy(),localDate,houses.getStatus(),houseID, houses.getLatitude(), houses.getLongitude());
        serviceHouseRepository.deleteByHouseId(houseID);
        if(!service.contains(0)){
            for(int i =0; i<service.size();i++){
                ServiceHouseEntity serviceHouseEntity = new ServiceHouseEntity();
                serviceHouseEntity.setHouseId( houseID);
                int serviceid = service.get(i);
                serviceHouseEntity.setServiceId(serviceid);
                serviceHouseRepository.save(serviceHouseEntity);

            }
        }
        long timestamp = System.currentTimeMillis();

        // Chuyển định dạng thời gian thành chuỗi
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyyMMdd_HHmmss");
        String formattedTimestamp = dateFormat.format(new Date(timestamp));
        int i = houseImagesEntity.size() + 2;
        for (MultipartFile file : files) {
            if (!file.isEmpty()) {
                HouseImagesEntity houseImages = new HouseImagesEntity();
                byte[] imageBytes = file.getBytes();
                gcsService.uploadImage("rfs_bucket", "House/house_" + formattedTimestamp+"_"+ i + "_"+houseID+".jpg", imageBytes);
                houseImages.setImageLink("/rfs_bucket/House/"+"house_"+formattedTimestamp+"_"+ i + "_"+houseID+".jpg");
                i++;
                houseImages.setHouseId(houseID);
                houseImages.setCreatedDate(LocalDate.now());
                houseImageRepository.save(houseImages);
            }else{
                HouseLandlordVo  houseImage = houseLandlordService.findHouseByID(houses.getHouseID());
                    if (houseImage.getListImage() == null ) {
                        HouseImagesEntity defaultImage = new HouseImagesEntity();
                        defaultImage.setImageLink("/rfs_bucket/House/housenull.jpg");
                        defaultImage.setHouseId(houses.getHouseID());
                        defaultImage.setCreatedDate(LocalDate.now());
                        houseImageRepository.save(defaultImage);
                    }
            }

        }


    }

}
