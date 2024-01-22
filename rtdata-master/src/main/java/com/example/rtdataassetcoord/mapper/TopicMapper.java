package com.example.rtdataassetcoord.mapper;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.example.rtdataassetcoord.dto.*;
import com.example.rtdataassetcoord.entity.Topic;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.rtdataassetcoord.page.PageModel;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author ${author}
 * @since 2023-06-13
 */

@Mapper
public interface TopicMapper extends BaseMapper<Topic> {

    IPage<TopicReviewListDto> getTopicReviewList(@Param("page") IPage page);


    IPage<TopicListDto> topicList (@Param("page") IPage page);

    TopicDataScreenDto topicDataScreen(Integer id);

    IPage<VoteListDto> voteList(@Param("page") IPage page, Integer id);

    List<CandidateDto> candidateList(String topicId);

    IPage<NewTopicListDto> getNewTopicList(@Param("page") IPage page);
}
