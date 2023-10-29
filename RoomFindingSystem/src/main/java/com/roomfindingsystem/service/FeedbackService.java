package com.roomfindingsystem.service;



import com.roomfindingsystem.dto.FeedbackDto;
import com.roomfindingsystem.dto.FeedbackHomeDto;


import java.util.List;

public interface FeedbackService {


    List<FeedbackDto> getFeedbackByHouseId(int houseId);

    List<FeedbackHomeDto> viewTop4Home();
}
