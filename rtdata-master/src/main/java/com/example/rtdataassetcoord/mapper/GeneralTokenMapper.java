package com.example.rtdataassetcoord.mapper;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.rtdataassetcoord.dto.*;
import com.example.rtdataassetcoord.entity.GeneralToken;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author ${author}
 * @since 2023-05-30
 */

@Mapper
public interface GeneralTokenMapper extends BaseMapper<GeneralToken> {

    IPage<DataAssetListDto> getDataAssetList(@Param("page") IPage page);

    DataScreenDto getDataScreen();

    List<MarkAssetListDto> getMarkAssetList(@Param("type") Integer type);

    IPage<NewDataAssetListDto> getNewDataAssetList(@Param("page") IPage page);
}
