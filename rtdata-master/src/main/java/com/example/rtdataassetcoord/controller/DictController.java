package com.example.rtdataassetcoord.controller;



import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.example.rtdataassetcoord.common.R;
import com.example.rtdataassetcoord.entity.DataCategory;
import com.example.rtdataassetcoord.entity.Right;
import com.example.rtdataassetcoord.service.DataCategoryService;
import com.example.rtdataassetcoord.service.RightService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Api(tags = "数据字典")
@RestController
@RequestMapping("/api/rtdataassetcoord/dict")
public class DictController {

    @Autowired
    RightService rightService;

    @Autowired
    DataCategoryService dataCategoryService;


    @ApiOperation("获取所有权益字典表")
    @GetMapping("/getAllRightList")
    public R<Right> getAllRightList(){
        List<Right> list = rightService.list(null);
        return new R().Success(list);
    }

    @ApiOperation("获取影响力权益字典表")
    @GetMapping("/getInfluenceAssetRightList")
    public R<Right> getInfluenceAssetRightList(){
        List<Right> list = rightService.list(new QueryWrapper<Right>().le("id",12));
        return new R().Success(list);
    }


    @ApiOperation("获取通用数据资产内容表")
    @GetMapping("/getGeneralAssetContentList")
    public R<Right> getGeneralAssetContentList(){
        List<Right> list = rightService.list(new QueryWrapper<Right>().gt("id",12));
        return new R().Success(list);
    }


    @ApiOperation("价值认证中心——获取所有数据类别")
    @GetMapping("/getAllDataCategory")
    public R<DataCategory> getAllDataCategory(){
        List<DataCategory> list = dataCategoryService.list(null);
        return new R().Success(list);
    }

}
