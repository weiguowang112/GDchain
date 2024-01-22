package com.example.rtdataassetcoord.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.example.rtdataassetcoord.common.ThreadLocalUtil;
import com.example.rtdataassetcoord.dto.*;
import com.example.rtdataassetcoord.entity.Topic;
import com.example.rtdataassetcoord.entity.TopicJoin;
import com.example.rtdataassetcoord.entity.User;
import com.example.rtdataassetcoord.entity.Vote;
import com.example.rtdataassetcoord.mapper.TopicMapper;
import com.example.rtdataassetcoord.page.PageFactory;
import com.example.rtdataassetcoord.page.PageModel;
import com.example.rtdataassetcoord.service.TopicJoinService;
import com.example.rtdataassetcoord.service.TopicService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.rtdataassetcoord.service.VoteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.ZoneId;
import java.time.temporal.ChronoUnit;
import java.util.List;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author ${author}
 * @since 2023-06-13
 */
@Service
public class TopicServiceImpl extends ServiceImpl<TopicMapper, Topic> implements TopicService {

    @Autowired
    private VoteService voteService;

    @Autowired
    private TopicJoinService topicJoinService;


    @Override
    public PageModel<TopicReviewListDto> getTopicReviewList() {
        User user = ThreadLocalUtil.get("user");
        IPage<TopicReviewListDto> topicReviewListDtoIPage = baseMapper.getTopicReviewList(PageFactory.defaultPage());
        topicReviewListDtoIPage.getRecords().forEach(i->{
            QueryWrapper<Vote> voteQueryWrapper = new QueryWrapper<>();
            voteQueryWrapper.eq("caseId", i.getCaseId());
            List<Vote> list = voteService.list(voteQueryWrapper);
            i.setStatus(0);
            if (list == null) {
                i.setReviewSituation(0 + "/" + 5);
            } else {
                i.setReviewSituation(list.size() + "/" + 5);
                list.forEach(a->{
                    if (a.getUserId() == user.getId()) {
                        i.setStatus(1);
                    }
                });
            }
        });

        return PageModel.transform(topicReviewListDtoIPage);
    }




    //处理项目状态
    //处理用户加入状态
    @Override
    public PageModel<TopicListDto> topicList() {
        User user = ThreadLocalUtil.get("user");
        IPage<TopicListDto> topicListDtoIPage = baseMapper.topicList(PageFactory.defaultPage());
        topicListDtoIPage.getRecords().forEach(topicListDto -> {
            // 将开始时间解析为LocalDate
            LocalDate startDate = topicListDto.getCreateTime().toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
            // 计算当前日期和开始日期之间的天数
            long days = ChronoUnit.DAYS.between(startDate, LocalDate.now());
            //成员加入阶段
            if (days <= 4) {
                topicListDto.setTopicStatus(2);
            } else if (days <= 7) {
                topicListDto.setTopicStatus(3);
            } else {
                topicListDto.setTopicStatus(4);
            }
            //加入状态
            QueryWrapper<TopicJoin> eq = new QueryWrapper<TopicJoin>().eq("userId", user.getId()).eq("topicId", topicListDto.getId());
            TopicJoin topicJoin = topicJoinService.getOne(eq);
            if (topicJoin == null) {
                topicListDto.setJoinStatus(0);
            } else {
                topicListDto.setJoinStatus(1);
            }

        });
        return PageModel.transform(topicListDtoIPage);
    }

    @Override
    public TopicDataScreenDto topicDataScreen(Integer id) {
        return baseMapper.topicDataScreen(id);
    }

    @Override
    public PageModel<VoteListDto> voteList(Integer id) {
        IPage<VoteListDto> voteListDtoIPage = baseMapper.voteList(PageFactory.defaultPage(), id);
        return PageModel.transform(voteListDtoIPage);
    }

    @Override
    public List<CandidateDto> candidateList(String topicId) {
        return baseMapper.candidateList(topicId);
    }

    @Override
    public PageModel<NewTopicListDto> getNewTopicList() {
        IPage<NewTopicListDto> newTopicList = baseMapper.getNewTopicList(PageFactory.defaultPage());
        newTopicList.getRecords().forEach(i->{
            // 将开始时间解析为LocalDate
            LocalDate startDate = i.getCreateTime().toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
            // 计算当前日期和开始日期之间的天数
            long days = ChronoUnit.DAYS.between(startDate, LocalDate.now());
            //成员加入阶段
            if (days <= 4) {
                i.setTopicStatus(2);
            } else if (days <= 7) {
                i.setTopicStatus(3);
            } else {
                i.setTopicStatus(4);
            }
        });
        return PageModel.transform(newTopicList);
    }

}
