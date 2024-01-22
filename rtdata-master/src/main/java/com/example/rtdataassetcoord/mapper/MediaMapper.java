package com.example.rtdataassetcoord.mapper;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.example.rtdataassetcoord.dto.DataAssetListDto;
import com.example.rtdataassetcoord.dto.MediaRankListDto;
import com.example.rtdataassetcoord.entity.Media;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author ${author}
 * @since 2023-06-10
 */
@Mapper
public interface MediaMapper extends BaseMapper<Media> {

    IPage<MediaRankListDto> getMediaRankList(@Param("page") IPage page);

}
