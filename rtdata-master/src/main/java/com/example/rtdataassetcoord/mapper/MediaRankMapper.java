package com.example.rtdataassetcoord.mapper;

import com.example.rtdataassetcoord.dto.MediaRankMonthDto;
import com.example.rtdataassetcoord.entity.MediaRank;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.rtdataassetcoord.task.MediaRankRate;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author ${author}
 * @since 2023-06-05
 */
@Mapper
public interface MediaRankMapper extends BaseMapper<MediaRank> {

    List<MediaRankMonthDto> getAllMediaRank();

    List<MediaRankRate> getMediaRankRate();
}
