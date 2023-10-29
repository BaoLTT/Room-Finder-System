package com.roomfindingsystem.service;

import com.roomfindingsystem.vo.FeedbackDto;

import com.roomfindingsystem.dto.FeedbackDto;
import com.roomfindingsystem.dto.FeedbackHomeVo;

import java.util.List;

public interface FeedbackService {


    List<FeedbackDto> getFeedbackByHouseId(int houseId);

    List<FeedbackHomeVo> viewTop4Home();
}
