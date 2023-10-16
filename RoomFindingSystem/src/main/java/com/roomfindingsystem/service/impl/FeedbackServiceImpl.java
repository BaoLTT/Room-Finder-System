package com.roomfindingsystem.service.impl;

import com.roomfindingsystem.entity.FeedbackEntity;
import com.roomfindingsystem.entity.UserEntity;
import com.roomfindingsystem.reponsitory.FeedbackRepository;
import com.roomfindingsystem.service.FeedbackService;
import com.roomfindingsystem.vo.FeedbackDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class FeedbackServiceImpl implements FeedbackService {
    @Autowired
    FeedbackRepository feedbackRepository;


    @Override
    public List<FeedbackDto> getFeedbackByHouseId(int houseId) {
        return feedbackRepository.findFeedbackDtosByHouseId(houseId);
    }
}
