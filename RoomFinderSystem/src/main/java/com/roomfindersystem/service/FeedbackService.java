package com.roomfindersystem.service;


import com.roomfindersystem.dto.FeedbackDto;
import com.roomfindersystem.dto.FeedbackDtoAdmin;
import com.roomfindersystem.dto.FeedbackHomeDto;
import com.roomfindersystem.dto.FeedbackListAdminDto;
import com.roomfindersystem.entity.FeedbackEntity;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;


import java.util.List;

public interface FeedbackService {


    List<FeedbackDto> getFeedbackByHouseId(int houseId, List<Boolean> status);

    List<FeedbackDtoAdmin> getFeedback(List<Boolean> status);

    List<FeedbackDto> getFeedbackByHouseIdAndStar(int houseId, int star, List<Boolean> status);
    List<FeedbackDtoAdmin> getFeedbackByStar(int star, List<Boolean> status);


    FeedbackEntity save(FeedbackEntity feedbackEntity);

    List<FeedbackEntity> getFeedbackEntityByUid(int houseId, int memberId);

    void deleteByHouseIdAndMemberId(int houseId, int memberId);

    void updateStatusToTrue(int feedbackId);
    void updateStatusToFalse(int feedbackId);


    List<FeedbackHomeDto> viewTop4Home();

    List<FeedbackListAdminDto> getListFeedback();

    List<FeedbackListAdminDto> getListFeedbackForLandLord(int createdBy);


}
