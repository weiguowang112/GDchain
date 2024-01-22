package com.example.rtdataassetcoord.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;

import com.example.rtdataassetcoord.common.BlockchainUtil;
import com.example.rtdataassetcoord.common.R;
import com.example.rtdataassetcoord.common.ThreadLocalUtil;
import com.example.rtdataassetcoord.dto.QrDetailDto;
import com.example.rtdataassetcoord.dto.QualificationReviewListDto;
import com.example.rtdataassetcoord.entity.QualificationReview;
import com.example.rtdataassetcoord.entity.User;
import com.example.rtdataassetcoord.entity.Vote;
import com.example.rtdataassetcoord.service.QualificationReviewService;
import com.example.rtdataassetcoord.service.UserService;
import com.example.rtdataassetcoord.service.VoteService;
import com.example.rtdataassetcoord.vo.QualificationReviewVo;
import com.example.rtdataassetcoord.vo.VoteVo;
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
 * @since 2023-04-23
 */
@RestController
@Api(tags = "资格审查")
@RequestMapping("/api/rtdataassetcoord/qualificationReview")
public class QualificationReviewController {


    @Autowired
    QualificationReviewService qualificationReviewService;

    @Autowired
    VoteService voteService;

    @Autowired
    UserService userService;

    //媒体端新增资格审查
    //此处上传后需要掉第三方接口
    @ApiOperation("媒体端新增资格审查")
    @PostMapping("/addQualificationReview")
    public R addQualificationReview(@RequestBody QualificationReviewVo qualificationReviewVo) {
        User user = ThreadLocalUtil.get("user");
        QualificationReview qualificationReview = new QualificationReview();
        qualificationReview.setStatus(0);
        qualificationReview.setUserId(user.getId());
        BeanUtils.copyProperties(qualificationReviewVo, qualificationReview);
        qualificationReviewService.save(qualificationReview);
        return new R().Success("新增成功");
    }



    //媒体端审核通过后信息展示
    @ApiOperation("媒体端审核通过后信息展示")
    @GetMapping("/getMediaInfo")
    public R<User> getMediaInfo(){
        User user = ThreadLocalUtil.get("user");
        QueryWrapper<QualificationReview> qualificationReviewQueryWrapper = new QueryWrapper<>();
        qualificationReviewQueryWrapper.eq("userId", user.getId());
        QualificationReview one = qualificationReviewService.getOne(qualificationReviewQueryWrapper);
        if (one == null) {
//            return new R().Error("您还未进行资格认证，请进行认证！！！");
            return new R().Success(-1);
        }
        if (one.getStatus() == 0) {
//            return new R().Error("正在审核中，请稍等....");
            return new R().Success(-2);
        } else if (one.getStatus() == 3) {
//            return new R().Error("审核不通过！");
            return new R().Success(-3);
        }
        return new R().Success(one);
    }



    //审核端list
    @ApiOperation("审核端list")
    @GetMapping("/getList")
    public R<QualificationReviewListDto> getList(){
        return new R().Success(qualificationReviewService.getQrList());

    }


    //投票详情
    @ApiOperation("投票详情")
    @GetMapping("/qrVoteDatail/{caseId}")
    public R<QrDetailDto> qrVoteDatail(@PathVariable String caseId){
        QrDetailDto qrDetailDto = new QrDetailDto();
        QualificationReview qualificationReview = qualificationReviewService.getById(caseId);
        BeanUtils.copyProperties(qualificationReview, qrDetailDto);
        User user = userService.getById(qualificationReview.getUserId());
        BeanUtils.copyProperties(user, qrDetailDto);
        return new R().Success(qrDetailDto);
    }





    //通用投票接口
    //干活就发token
    @ApiOperation("通用投票接口")
    @PostMapping("/qualificationReviewVote")
    public R qualificationReviewVote(@RequestBody VoteVo voteVo) {
        User user = ThreadLocalUtil.get("user");
        Vote vote = new Vote();
        vote.setUserId(user.getId());
        BeanUtils.copyProperties(voteVo, vote);
        voteService.save(vote);
        BlockchainUtil.transfer(user.getUsername(), 5, "token");
        return new R().Success();
    }






}

