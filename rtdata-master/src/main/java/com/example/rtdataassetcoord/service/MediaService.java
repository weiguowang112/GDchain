package com.example.rtdataassetcoord.service;

import com.example.rtdataassetcoord.dto.MediaRankListDto;
import com.example.rtdataassetcoord.entity.Media;
import com.baomidou.mybatisplus.extension.service.IService;
import com.example.rtdataassetcoord.page.PageModel;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author ${author}
 * @since 2023-06-10
 */
public interface MediaService extends IService<Media> {

    PageModel<MediaRankListDto> getMediaRankList();
}
