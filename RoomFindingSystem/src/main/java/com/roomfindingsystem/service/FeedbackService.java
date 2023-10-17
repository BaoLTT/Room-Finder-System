package com.roomfindingsystem.service;

import com.roomfindingsystem.vo.FeedbackHomeVo;

import java.util.List;

public interface FeedbackService {
    List<FeedbackHomeVo> viewTop4Home();
}
