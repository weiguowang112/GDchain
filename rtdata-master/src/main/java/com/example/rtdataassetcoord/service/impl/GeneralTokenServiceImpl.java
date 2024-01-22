package com.example.rtdataassetcoord.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.example.rtdataassetcoord.common.ThreadLocalUtil;
import com.example.rtdataassetcoord.dto.DataAssetListDto;
import com.example.rtdataassetcoord.dto.DataScreenDto;
import com.example.rtdataassetcoord.dto.MarkAssetListDto;
import com.example.rtdataassetcoord.dto.NewDataAssetListDto;
import com.example.rtdataassetcoord.entity.GeneralToken;
import com.example.rtdataassetcoord.entity.User;
import com.example.rtdataassetcoord.entity.Vote;
import com.example.rtdataassetcoord.mapper.GeneralTokenMapper;
import com.example.rtdataassetcoord.page.PageFactory;
import com.example.rtdataassetcoord.page.PageModel;
import com.example.rtdataassetcoord.service.GeneralTokenService;
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
 * @since 2023-05-30
 */
@Service
public class GeneralTokenServiceImpl extends ServiceImpl<GeneralTokenMapper, GeneralToken> implements GeneralTokenService {

    @Autowired
    private VoteService voteService;

    @Override
    public PageModel<DataAssetListDto> getDataAssetList() {
        User user = ThreadLocalUtil.get("user");
        IPage<DataAssetListDto> dataAssetListDtoIPage = baseMapper.getDataAssetList(PageFactory.defaultPage());
        dataAssetListDtoIPage.getRecords().forEach(i->{
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

        return PageModel.transform(dataAssetListDtoIPage);
    }

    @Override
    public DataScreenDto getDataScreen() {
        return baseMapper.getDataScreen();
    }

    @Override
    public List<MarkAssetListDto> getMarkAssetList(Integer type) {
        return baseMapper.getMarkAssetList(type);
    }

    @Override
    public PageModel<NewDataAssetListDto> getNewDataAssetList() {
        return PageModel.transform(baseMapper.getNewDataAssetList(PageFactory.defaultPage()));
    }
}
