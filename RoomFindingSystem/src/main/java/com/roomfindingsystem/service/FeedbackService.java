package com.roomfindingsystem.service;

import com.roomfindingsystem.entity.FeedbackEntity;
import com.roomfindingsystem.entity.UserEntity;
import com.roomfindingsystem.vo.FeedbackDto;
import com.roomfindingsystem.vo.FeedbackHomeVo;

import java.util.List;

public interface FeedbackService {


    List<FeedbackDto> getFeedbackByHouseId(int houseId);

    List<FeedbackHomeVo> viewTop4Home();
    FeedbackEntity save(FeedbackEntity feedbackEntity);

    List <FeedbackEntity> getFeedbackEntityByUid(int houseId, int memberId);

    void deleteByHouseIdAndMemberId (int houseId, int memberId);
}
