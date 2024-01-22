package com.example.rtdataassetcoord.mapper;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.example.rtdataassetcoord.dto.QualificationReviewListDto;
import com.example.rtdataassetcoord.entity.QualificationReview;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author ${author}
 * @since 2023-04-23
 */
@Mapper
public interface QualificationReviewMapper extends BaseMapper<QualificationReview> {

    IPage<QualificationReviewListDto> getQrList(@Param("page") IPage page);

    int test();

}
