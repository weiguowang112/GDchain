package com.example.rtdataassetcoord.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.example.rtdataassetcoord.common.ThreadLocalUtil;
import com.example.rtdataassetcoord.dto.MediaRankListDto;
import com.example.rtdataassetcoord.entity.Media;
import com.example.rtdataassetcoord.entity.User;
import com.example.rtdataassetcoord.entity.Vote;
import com.example.rtdataassetcoord.mapper.MediaMapper;
import com.example.rtdataassetcoord.page.PageFactory;
import com.example.rtdataassetcoord.page.PageModel;
import com.example.rtdataassetcoord.service.MediaService;
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
 * @since 2023-06-10
 */
@Service
public class MediaServiceImpl extends ServiceImpl<MediaMapper, Media> implements MediaService {

    @Autowired
    private VoteService voteService;

    @Override
    public PageModel<MediaRankListDto> getMediaRankList() {
        User user = ThreadLocalUtil.get("user");
        IPage<MediaRankListDto> mediaRankListDtoIPage = baseMapper.getMediaRankList(PageFactory.defaultPage());
        mediaRankListDtoIPage.getRecords().forEach(i->{
            QueryWrapper<Vote> voteQueryWrapper = new QueryWrapper<>();
            voteQueryWrapper.eq("caseId", i.getId());
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

        return PageModel.transform(mediaRankListDtoIPage);
    }
}
