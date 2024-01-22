package com.example.rtdataassetcoord.controller;


import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.example.rtdataassetcoord.common.R;
import com.example.rtdataassetcoord.common.ThreadLocalUtil;
import com.example.rtdataassetcoord.dto.*;
import com.example.rtdataassetcoord.entity.*;
import com.example.rtdataassetcoord.service.*;
import com.example.rtdataassetcoord.vo.GeneralAssetVo;
import com.example.rtdataassetcoord.vo.MediaRankVo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author ${author}
 * @since 2023-06-05
 */

@Api(tags = "媒体评级")
@RestController
@RequestMapping("/api/rtdataassetcoord/mediaRank")
public class MediaRankController {

    @Autowired
    MediaService mediaService;

    @Autowired
    MediaContentService mediaContentService;

    @Autowired
    FileService fileService;

    @Autowired
    UserService userService;

    @Autowired
    MediaRankService mediaRankService;




    //新建媒体评级
    @ApiOperation("新建媒体评级")
    @PostMapping("/addMediaRank")
    public R addMediaRank(@RequestBody MediaRankVo mediaRankVo) {
        User user = ThreadLocalUtil.get("user");
        //先存储主体
        Media media = new Media();
        BeanUtils.copyProperties(mediaRankVo, media);
        media.setUserId(user.getId());
        media.setStatus(0);
        mediaService.save(media);
        //在存储评级内容
        mediaRankVo.getMediaContents().forEach(i -> {
            MediaContent mediaContent = new MediaContent();
            BeanUtils.copyProperties(i,mediaContent);
            mediaContent.setMediaId(media.getId());
            mediaContentService.save(mediaContent);
        });

        //在存储文件
        mediaRankVo.getFilePaths().forEach(file->{
            File sysFile = new File();
            sysFile.setFilePath(file);
            sysFile.setBusinessId(media.getId());
            fileService.save(sysFile);
        });

        return new R().Success();
    }


    //媒体评级审查list
    @ApiOperation("媒体评级审查list")
    @GetMapping("/getMediaRankList")
    public R<MediaRankListDto> getMediaRankList(){

        return new R().Success(mediaService.getMediaRankList());

    }

    //评级详情
    @ApiOperation("媒体评级详情")
    @GetMapping("/getMediaRankDetail")
    public R<MediaRankListDto> getMediaRankDetail(@RequestParam String id) {
        MediaRankDetailDto mediaRankDetailDto = new MediaRankDetailDto();
        Media media = mediaService.getById(id);
        BeanUtils.copyProperties(media,mediaRankDetailDto);

        User user = userService.getById(media.getUserId());
        mediaRankDetailDto.setOrgName(user.getOrgName());
        //此处还没设置本月选题数

        //设置文件
        List<FileDto> fileDtos = new ArrayList<>();
        List<File> files = fileService.list(new QueryWrapper<File>().eq("businessId", id));
        files.forEach(file -> {
            FileDto fileDto = new FileDto();
            fileDto.setFilePath(file.getFilePath());
            fileDto.setFileName(fileName(file.getFilePath()));
            fileDtos.add(fileDto);
        });
        mediaRankDetailDto.setFiles(fileDtos);
        //设置内容
        mediaRankDetailDto.setMediaContents(mediaContentService.list(new QueryWrapper<MediaContent>().eq("mediaId", id)));

        return new R().Success(mediaRankDetailDto);
    }

    public  String fileName(String filePath) {
        int i = filePath.lastIndexOf("/");
        return filePath.substring(i+1);
    }

    //自家评级展示
    @ApiOperation("自家评级展示")
    @GetMapping("/getOwnMediaRank")
    public R<MediaRank> getOwnMediaRank() {
        User user = ThreadLocalUtil.get("user");
        LambdaQueryWrapper<MediaRank> wapper = new LambdaQueryWrapper<>();
        // 获取上个月的时间范围
        LocalDateTime now = LocalDateTime.now();
        LocalDateTime start = LocalDateTime.of(now.getYear(), now.getMonth().minus(1), 1, 0, 0, 0);
        LocalDateTime end = LocalDateTime.of(now.getYear(), now.getMonth().minus(1), start.getMonth().length(start.toLocalDate().isLeapYear()), 23, 59, 59);

        wapper.eq(MediaRank::getUserId, user.getId()).between(MediaRank::getCreateTime,start,end);
        MediaRank mediaRank = mediaRankService.getOne(wapper);
        return new R().Success(mediaRank);
    }


    //本月评级媒体评级展示（所有）
    @ApiOperation("本月评级媒体评级展示（所有）")
    @GetMapping("/getAllMediaRank")
    public R<AllMediaRankDto> getAllMediaRank() {
        List<MediaRankMonthDto> list=mediaRankService.getAllMediaRank();
        // 按照 rank 分别存储查询结果
        List<MediaRankMonthDto> rank1List = new ArrayList<>();
        List<MediaRankMonthDto> rank2List = new ArrayList<>();
        List<MediaRankMonthDto> rank3List = new ArrayList<>();

        for (MediaRankMonthDto mediaRankMonthDto : list) {
            int rank = mediaRankMonthDto.getRank();
            if (rank == 1) {
                rank1List.add(mediaRankMonthDto);
            } else if (rank == 2) {
                rank2List.add(mediaRankMonthDto);
            } else if (rank == 3) {
                rank3List.add(mediaRankMonthDto);
            }
        }
        AllMediaRankDto allMediaRankDto = new AllMediaRankDto();
        allMediaRankDto.setRank1List(rank1List);
        allMediaRankDto.setRank2List(rank2List);
        allMediaRankDto.setRank3List(rank3List);
        return new R().Success(allMediaRankDto);
    }




}

