package com.roomfindingsystem.service;



import com.roomfindingsystem.dto.FeedbackDto;
import com.roomfindingsystem.dto.FeedbackHomeDto;
import com.roomfindingsystem.entity.FeedbackEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;


import java.util.List;

public interface FeedbackService {


    List<FeedbackDto> getFeedbackByHouseId(int houseId);

    List<FeedbackHomeDto> viewTop4Home();

    Page<FeedbackEntity> getListFeedback(Pageable pageable);
}
