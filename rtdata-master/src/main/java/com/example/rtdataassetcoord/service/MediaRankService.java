package com.example.rtdataassetcoord.service;

import com.example.rtdataassetcoord.dto.MediaRankMonthDto;
import com.example.rtdataassetcoord.entity.MediaRank;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author ${author}
 * @since 2023-06-05
 */
public interface MediaRankService extends IService<MediaRank> {

    List<MediaRankMonthDto> getAllMediaRank();
}
