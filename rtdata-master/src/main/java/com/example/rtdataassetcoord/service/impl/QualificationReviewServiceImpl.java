package com.example.rtdataassetcoord.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.rtdataassetcoord.common.ThreadLocalUtil;
import com.example.rtdataassetcoord.dto.QualificationReviewListDto;
import com.example.rtdataassetcoord.entity.QualificationReview;
import com.example.rtdataassetcoord.entity.User;
import com.example.rtdataassetcoord.entity.Vote;
import com.example.rtdataassetcoord.mapper.QualificationReviewMapper;
import com.example.rtdataassetcoord.page.PageFactory;
import com.example.rtdataassetcoord.page.PageModel;
import com.example.rtdataassetcoord.service.QualificationReviewService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.rtdataassetcoord.service.VoteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author ${author}
 * @since 2023-04-23
 */
@Service
public class QualificationReviewServiceImpl extends ServiceImpl<QualificationReviewMapper, QualificationReview> implements QualificationReviewService {

    @Autowired
    private VoteService voteService;

    @Override
    public PageModel<QualificationReviewListDto> getQrList() {
        User user = ThreadLocalUtil.get("user");

        IPage<QualificationReviewListDto> qrListDtoIPage = baseMapper.getQrList(PageFactory.defaultPage());
        qrListDtoIPage.getRecords().forEach(i->{
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

        return PageModel.transform(qrListDtoIPage);
    }
}
