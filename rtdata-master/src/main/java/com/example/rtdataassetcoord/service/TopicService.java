package com.example.rtdataassetcoord.service;

import com.example.rtdataassetcoord.dto.*;
import com.example.rtdataassetcoord.entity.Topic;
import com.baomidou.mybatisplus.extension.service.IService;
import com.example.rtdataassetcoord.page.PageModel;
import org.apache.ibatis.javassist.expr.NewArray;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author ${author}
 * @since 2023-06-13
 */
public interface TopicService extends IService<Topic> {

    PageModel<TopicReviewListDto> getTopicReviewList();

    PageModel<TopicListDto> topicList();

    TopicDataScreenDto topicDataScreen(Integer id);

    PageModel<VoteListDto> voteList(Integer id);

    List<CandidateDto> candidateList(String topicId);

    PageModel<NewTopicListDto> getNewTopicList();
}
