package com.example.rtdataassetcoord.service;

import com.example.rtdataassetcoord.dto.*;
import com.example.rtdataassetcoord.entity.GeneralToken;
import com.baomidou.mybatisplus.extension.service.IService;
import com.example.rtdataassetcoord.page.PageModel;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author ${author}
 * @since 2023-05-30
 */
public interface GeneralTokenService extends IService<GeneralToken> {

    PageModel<DataAssetListDto> getDataAssetList();

    DataScreenDto getDataScreen();

    List<MarkAssetListDto> getMarkAssetList(Integer type);

    PageModel<NewDataAssetListDto> getNewDataAssetList();
}
