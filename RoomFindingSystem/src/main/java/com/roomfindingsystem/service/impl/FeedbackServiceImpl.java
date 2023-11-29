package com.roomfindingsystem.service.impl;



import com.roomfindingsystem.dto.FeedbackHomeDto;
import com.roomfindingsystem.dto.FeedbackListAdminDto;
import com.roomfindingsystem.entity.FeedbackEntity;
import com.roomfindingsystem.repository.FeedbackRepository;
import com.roomfindingsystem.service.FeedbackService;


import com.roomfindingsystem.dto.FeedbackDto;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
@Service
public class FeedbackServiceImpl implements FeedbackService {
    @Autowired
    FeedbackRepository feedbackRepository;




    @Override
    public List<FeedbackDto> getFeedbackByHouseId(int houseId, List<Boolean> status) {
        return feedbackRepository.findFeedbackDtosByHouseId(houseId, status);
    }

    @Override
    public List<FeedbackDto> getFeedbackByHouseIdAndStar(int houseId, int star, List<Boolean> status) {
        return feedbackRepository.findFeedbackDtosByHouseIdAndStar(houseId, star, status);
    }

    @Override
    public List<FeedbackHomeDto> viewTop4Home() {
        List<FeedbackHomeDto> list = new ArrayList<>();
        if(!feedbackRepository.viewTop4Home().isEmpty()){
            if(feedbackRepository.viewTop4Home().size()>4){
                for(int i = 0; i<4; i++){
                    list.add(feedbackRepository.viewTop4Home().get(i));
                }
            } else return feedbackRepository.viewTop4Home();

        }
        return list;
    }

    @Override
    public List<FeedbackListAdminDto> getListFeedback() {
        return feedbackRepository.getFeedbackListForAdmin();
    }

    @Override
    public List<FeedbackListAdminDto> getListFeedbackForLandLord(int createdBy) {
        return feedbackRepository.getFeedbackListForLandLord(createdBy);
    }

    @Override
    public FeedbackEntity save(FeedbackEntity feedbackEntity) {
        return feedbackRepository.save(feedbackEntity);
    }

    @Override
    public List <FeedbackEntity> getFeedbackEntityByUid(int houseId, int memberId) {
        return feedbackRepository.getFeedbackEntityByUid(houseId, memberId);
    }

    @Override
    public void deleteByHouseIdAndMemberId(int houseId, int memberId) {
        feedbackRepository.deleteByHouseIdAndMemberId(houseId, memberId);}

    @Override
    public void updateStatusToTrue(int feedbackId) {
        feedbackRepository.updateStatusToTrue(feedbackId);
    }

    @Override
    public void updateStatusToFalse(int feedbackId) {
        feedbackRepository.updateStatusToFalse(feedbackId);

    }

}
