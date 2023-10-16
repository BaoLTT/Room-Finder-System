package com.roomfindingsystem.service;

import com.roomfindingsystem.entity.FeedbackEntity;
import com.roomfindingsystem.entity.UserEntity;
import com.roomfindingsystem.vo.FeedbackDto;

import java.util.List;

public interface FeedbackService {

    List<FeedbackDto> getFeedbackByHouseId(int houseId);
}
