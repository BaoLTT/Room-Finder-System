package com.roomfindingsystem.service;


import com.roomfindingsystem.dto.FeedbackDto;
import com.roomfindingsystem.dto.FeedbackHomeDto;
import com.roomfindingsystem.dto.FeedbackListAdminDto;
import com.roomfindingsystem.entity.FeedbackEntity;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;


import java.util.List;

public interface FeedbackService {


    List<FeedbackDto> getFeedbackByHouseId(int houseId);

    List<FeedbackDto> getFeedbackByHouseIdAndStar(int houseId, int star);


    FeedbackEntity save(FeedbackEntity feedbackEntity);

    List<FeedbackEntity> getFeedbackEntityByUid(int houseId, int memberId);

    void deleteByHouseIdAndMemberId(int houseId, int memberId);

    void updateStatusToTrue(int feedbackId);
    void updateStatusToFalse(int feedbackId);


    List<FeedbackHomeDto> viewTop4Home();

    List<FeedbackListAdminDto> getListFeedback();

    List<FeedbackListAdminDto> getListFeedbackForLandLord(int createdBy);


}
