package com.example.rtdataassetcoord.service;

import com.example.rtdataassetcoord.dto.QualificationReviewListDto;
import com.example.rtdataassetcoord.entity.QualificationReview;
import com.baomidou.mybatisplus.extension.service.IService;
import com.example.rtdataassetcoord.page.PageModel;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author ${author}
 * @since 2023-04-23
 */
public interface QualificationReviewService extends IService<QualificationReview> {

    PageModel<QualificationReviewListDto> getQrList();

}
