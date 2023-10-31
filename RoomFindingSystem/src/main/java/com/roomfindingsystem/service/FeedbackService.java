package com.roomfindingsystem.service;

import com.roomfindingsystem.entity.FeedbackEntity;
import com.roomfindingsystem.vo.FeedbackDto;
import com.roomfindingsystem.vo.FeedbackHomeVo;
import com.roomfindingsystem.vo.FeedbackListVo;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
public interface FeedbackService {


    List<FeedbackDto> getFeedbackByHouseId(int houseId);

    List<FeedbackHomeVo> viewTop4Home();

    Page<FeedbackEntity> getListFeedback(Pageable pageable);
}
