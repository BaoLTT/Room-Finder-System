package com.roomfindingsystem.service.impl;


import com.roomfindingsystem.dto.FeedbackHomeDto;
import com.roomfindingsystem.reponsitory.FeedbackRepository;
import com.roomfindingsystem.service.FeedbackService;


import com.roomfindingsystem.dto.FeedbackDto;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
@Service
public class FeedbackServiceImpl implements FeedbackService {
    @Autowired
    FeedbackRepository feedbackRepository;


    @Override
    public List<FeedbackDto> getFeedbackByHouseId(int houseId) {
        return feedbackRepository.findFeedbackDtosByHouseId(houseId);
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
}
