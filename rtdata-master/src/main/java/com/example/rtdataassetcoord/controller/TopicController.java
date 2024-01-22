package com.example.rtdataassetcoord.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.example.rtdataassetcoord.common.R;
import com.example.rtdataassetcoord.common.ThreadLocalUtil;
import com.example.rtdataassetcoord.dto.*;
import com.example.rtdataassetcoord.entity.*;
import com.example.rtdataassetcoord.service.*;
import com.example.rtdataassetcoord.vo.JudgeTaskVo;
import com.example.rtdataassetcoord.vo.TopicTaskVo;
import com.example.rtdataassetcoord.vo.TopicVo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author ${author}
 * @since 2023-06-13
 */

@Api( tags= "选题")
@RestController
@RequestMapping("/api/rtdataassetcoord/topic")
public class TopicController {
    @Autowired
    TopicService topicService;
    @Autowired
    UserService userService;

    @Autowired
    TopicJoinService topicJoinService;

    @Autowired
    TopicVoteService topicVoteService;

    @Autowired
    TopicTaskService topicTaskService;

    @Autowired
    TaskService taskService;

    @Autowired
    FileService fileService;

    //新增选题
    @ApiOperation("新增选题")
    @PostMapping("/addTopic")
    public R addTopic(@RequestBody TopicVo topicVo) {
        User user = ThreadLocalUtil.get("user");
        Topic topic = new Topic();
        BeanUtils.copyProperties(topicVo, topic);
        topic.setStatus(0);
        topic.setUserId(user.getId());
        topicService.save(topic);
        return new R().Success();
    }

    //审核端list
    @ApiOperation("获取选题审核list")
    @GetMapping("/getTopicReviewList")
    public R<TopicReviewListDto> getTopicReviewList(){
        return new R().Success(topicService.getTopicReviewList());
    }

    //审核详情
    @ApiOperation("选题审查审核详情")
    @GetMapping("/topicReviewDatail/{caseId}")
    public R<TopicReviewDetailDto> topicReviewDatail(@PathVariable String caseId){
        TopicReviewDetailDto topicReviewDetailDto = new TopicReviewDetailDto();
        Topic topic = topicService.getById(caseId);
        BeanUtils.copyProperties(topic,topicReviewDetailDto);

        User user = userService.getById(topic.getUserId());
        topicReviewDetailDto.setOrgName(user.getOrgName());
        //本月协同选题数量还没设置
        return new R().Success(topicReviewDetailDto);
    }



    //选题列表上方数据总览
    @ApiOperation("选题列表上方数据总览")
    @GetMapping("/topicDataScreen")
    public R<TopicDataScreenDto> topicDataScreen(){
        User user = ThreadLocalUtil.get("user");
        return new R().Success(topicService.topicDataScreen(user.getId()));
    }


    //选题列表
    @ApiOperation("选题列表")
    @GetMapping("/topicList")
    public R<TopicListDto> topicList(){
        return new R().Success(topicService.topicList());
    }

    //加入选题
    @ApiOperation("加入选题")
    @PostMapping("/joinTopic")
    public R joinTopic(@RequestBody TopicJoin topicJoin) {
        topicJoinService.save(topicJoin);
        return new R().Success();
    }


    //投票系统-投票列表
    @ApiOperation("投票列表")
    @GetMapping("/voteList")
    public R<VoteListDto> voteList(){
        User user = ThreadLocalUtil.get("user");
        return new R().Success(topicService.voteList(user.getId()));
    }

    //投票钥匙持有人
    //候选人下拉列表
    @ApiOperation("候选人下拉列表")
    @GetMapping("/candidateList/{topicId}")
    public R<CandidateDto> candidateList(@PathVariable String topicId) {
        return new R().Success(topicService.candidateList(topicId));
    }


    //投票
    @ApiOperation("投票")
    @PostMapping("/voteKeyHolder")
    public R voteKeyHolder(@RequestBody VoteKeyHolderDto voteKeyHolderDto) {
        TopicVote topicVote = new TopicVote();
        BeanUtils.copyProperties(voteKeyHolderDto, topicVote);
        topicVoteService.save(topicVote);
        return new R().Success();
    }

    //发布任务
    @ApiOperation("发布任务")
    @PostMapping("/publishTask")
    public R publishTask(@RequestBody TopicTaskVo topicTaskVo) {
        TopicTask topicTask = new TopicTask();
        BeanUtils.copyProperties(topicTaskVo, topicTask);
        topicTaskService.save(topicTask);
        //存储任务列表
        topicTaskVo.getTasks().forEach(i->{
            taskService.save(new Task().setTask(i).setBusinessId(topicTask.getId()));
        });

        return new R().Success();
    }

    //个人中心/任务中心/给我派发的任务
    @ApiOperation("给我派发的任务")
    @GetMapping("/getTask")
    public R publishTask() {
        User user = ThreadLocalUtil.get("user");
        return new R().Success(topicTaskService.getTaskByUserId(user.getId()));
    }

    //个人中心/任务中心/待我审核的任务
    @ApiOperation("待我审核的任务")
    @GetMapping("/reviewTask")
    public R reviewTask() {
        User user = ThreadLocalUtil.get("user");
        return new R().Success(topicTaskService.getReviewTaskByUserId(user.getId()));
    }

    @ApiOperation("任务附件上传")
    @PostMapping("/upTaskFile")
    public R upTaskFile(@RequestBody File file) {
        fileService.save(file);
        topicTaskService.updateById(new TopicTask().setId(file.getBusinessId()).setStatus(1));
        return new R().Success();
    }


    @ApiOperation("钥匙持有人审核任务")
    @PostMapping("/judgeTask")
    public R judgeTask(@RequestBody JudgeTaskVo judgeTaskVo) {
        TopicTask topicTask = new TopicTask();
        topicTask.setId(judgeTaskVo.getId());
        //1完成任务，任务状态置2
        if (judgeTaskVo.getStatus() == 1) {
            topicTask.setStatus(2);
        } else {
            topicTask.setStatus(0);
            fileService.remove(new QueryWrapper<File>().eq("businessId", judgeTaskVo.getId()));
        }
        topicTaskService.updateById(topicTask);
        return new R().Success();
        //未完成任务状态，任务状态置0
    }

    //主页-新增协作项目公式
    @ApiOperation("主页-新增协作项目公示")
    @GetMapping("/getNewTopicList")
    public R<NewTopicListDto> getNewTopicList() {
        return new R().Success(topicService.getNewTopicList());

    }






}

