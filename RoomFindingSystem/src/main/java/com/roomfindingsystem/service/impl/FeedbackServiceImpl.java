package com.roomfindingsystem.service.impl;

import com.roomfindingsystem.reponsitory.FeedbackRepository;
import com.roomfindingsystem.service.FeedbackService;
import com.roomfindingsystem.vo.FeedbackHomeVo;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
@Service("feedbackService")
public class FeedbackServiceImpl implements FeedbackService {
    private FeedbackRepository feedbackRepository;

    public FeedbackServiceImpl(FeedbackRepository feedbackRepository){
        super();
        this.feedbackRepository = feedbackRepository;
    }
    @Override
    public List<FeedbackHomeVo> viewTop4Home() {
        List<FeedbackHomeVo> list = new ArrayList<>();
        if(!feedbackRepository.viewTop4Home().isEmpty()){
            if(feedbackRepository.viewTop4Home().size()>4){
                for(int i = 0; i<4; i++){
                    list.add(feedbackRepository.viewTop4Home().get(i));
                }
            } else return feedbackRepository.viewTop4Home();

        }
        return list;
    }
}
