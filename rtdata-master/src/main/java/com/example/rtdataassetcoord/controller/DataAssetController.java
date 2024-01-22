package com.example.rtdataassetcoord.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.example.rtdataassetcoord.common.BlockchainUtil;
import com.example.rtdataassetcoord.common.R;
import com.example.rtdataassetcoord.common.ThreadLocalUtil;
import com.example.rtdataassetcoord.dto.*;
import com.example.rtdataassetcoord.entity.*;
import com.example.rtdataassetcoord.service.*;
import com.example.rtdataassetcoord.vo.GeneralAssetVo;
import com.example.rtdataassetcoord.vo.InfluenceAssetVo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author ${author}
 * @since 2023-05-30
 */

@Api(tags = "数据资产")
@RestController
@SuppressWarnings("all")
@RequestMapping("/api/rtdataassetcoord/dataAsset")
public class DataAssetController {
    @Autowired
    GeneralTokenService generalTokenService;

    @Autowired
    FileService fileService;

    @Autowired
    AssetRightService assetRightService;

    @Autowired
    UserService userService;

    @Autowired
    UserAssetService userAssetService;



    //新增通用数据资产
    @ApiOperation("新增通用数据资产")
    @PostMapping("/addGeneralAsset")
    public R addGeneralAsset(@RequestBody GeneralAssetVo generalAssetVo) {
        //添加数据资产
        GeneralToken generalToken = new GeneralToken();
        BeanUtils.copyProperties(generalAssetVo, generalToken);
        generalToken.setType(0);
        generalToken.setStatus(0);
        generalTokenService.save(generalToken);
        //在存储相关文件
        generalAssetVo.getFilePaths().forEach(file ->{
            File sysFile = new File();
            sysFile.setFilePath(file);
            sysFile.setBusinessId(generalToken.getId());
            fileService.save(sysFile);

        });
        //添加数据资产内容概括
        generalAssetVo.getRightIds().forEach(i->{
            AssetRight assetRight = new AssetRight();
            assetRight.setRightId(i);
            assetRight.setBusinessId(generalToken.getId());
            assetRightService.save(assetRight);
        });
        return new R().Success();
    }

    @ApiOperation("新增影响力数据资产")
    @PostMapping("/addInfluenceAsset")
    public R addInfluenceAsset(@RequestBody InfluenceAssetVo influenceAssetVo) {
        //添加数据资产
        GeneralToken influenceAsset = new GeneralToken();
        BeanUtils.copyProperties(influenceAssetVo, influenceAsset);
        influenceAsset.setType(1);
        influenceAsset.setStatus(0);
        generalTokenService.save(influenceAsset);
        //存储相关文件
        influenceAssetVo.getFilePaths().forEach(file ->{
            File sysFile = new File();
            sysFile.setFilePath(file);
            sysFile.setBusinessId(influenceAsset.getId());
            fileService.save(sysFile);

        });
        //添加权益
        influenceAssetVo.getRightIds().forEach(i->{
            AssetRight assetRight = new AssetRight();
            assetRight.setRightId(i);
            assetRight.setBusinessId(influenceAsset.getId());
            assetRightService.save(assetRight);
        });


        return new R().Success();
    }

    //审核端接口
    //获取数据资产审核list
    @ApiOperation("获取数据资产审核list")
    @GetMapping("/getDataAssetList")
    public R<DataAssetListDto> getDataAssetList(){

        return new R().Success(generalTokenService.getDataAssetList());

    }



    //数据资产审核详情
    @ApiOperation("数据资产审核详情")
    @GetMapping("/dataAssetDatail/{caseId}")
    public R<DataAssetDetailDto> qrVoteDatail(@PathVariable String caseId){
        DataAssetDetailDto dataAssetDetailDto = new DataAssetDetailDto();
        GeneralToken generalToken = generalTokenService.getById(caseId);
        BeanUtils.copyProperties(generalToken, dataAssetDetailDto);
        //设置文件
        List<FileDto> fileDtos = new ArrayList<>();
        FileDto fileDto1 = new FileDto();
        fileDto1.setFilePath(generalToken.getFilePath());
        fileDto1.setFileName(fileName(generalToken.getFilePath()));
        fileDtos.add(fileDto1);
        List<File> files = fileService.list(new QueryWrapper<File>().eq("businessId", caseId));
        files.forEach(file -> {
            FileDto fileDto = new FileDto();
            fileDto.setFilePath(file.getFilePath());
            fileDto.setFileName(fileName(file.getFilePath()));
            fileDtos.add(fileDto);
        });

        dataAssetDetailDto.setFiles(fileDtos);
        //设置用户信息
        User user = userService.getById(generalToken.getUserId());
        dataAssetDetailDto.setOrgName(user.getOrgName());

        //权益列表
        List<Integer> rightIds = assetRightService.list(new QueryWrapper<AssetRight>().eq("businessId", caseId)).stream().map(AssetRight::getRightId).collect(Collectors.toList());
        dataAssetDetailDto.setRightIds(rightIds);

        return new R().Success(dataAssetDetailDto);

    }

    public  String fileName(String filePath) {
        int i = filePath.lastIndexOf("/");
        return filePath.substring(i+1);
    }


    @ApiOperation("交易市场顶端数据总览")
    @GetMapping("/dataScreen")
    public R<DataScreenDto> dataScreen(){
        DataScreenDto dataScreenDto=generalTokenService.getDataScreen();

        return new R().Success(dataScreenDto);
    }

    @ApiOperation("交易市场数据资产展示")
    @GetMapping("/assetMarket")
    public R assetMarket(@RequestParam(required = false) Integer type){

        List<MarkAssetListDto> list = generalTokenService.getMarkAssetList(type);
        return new R().Success(list);
    }


    //数据资产提供方信息查看


    //资产购买页详情
    @ApiOperation("交易市场资产详情")
    @GetMapping("/assetMarketDetail")
    public R assetMarketDetail(@RequestParam String id){
        MarkAssetDetailDto markAssetDetailDto = new MarkAssetDetailDto();
        GeneralToken generalToken = generalTokenService.getById(id);
        BeanUtils.copyProperties(generalToken, markAssetDetailDto);
        User user = userService.getById(generalToken.getUserId());
        markAssetDetailDto.setOrgName(user.getOrgName());
        //java8新特性把对象list的一个属性抽出来形成一个list
        markAssetDetailDto.setRightIds(assetRightService.list(new QueryWrapper<AssetRight>().eq("businessId",id)).stream().map(AssetRight::getRightId).collect(Collectors.toList()));
        return new R().Success(markAssetDetailDto);
    }


    //数据资产购买
    @ApiOperation("数据资产购买")
    @PostMapping("/buyAsset/{id}")
    public R buyAsset(@PathVariable String id) {
        GeneralToken token = generalTokenService.getById(id);
        //就是将数组资产id和用户id绑定起来
        User user = ThreadLocalUtil.get("user");
        UserAsset userAsset = new UserAsset();
        userAsset.setAssetId(id);
        userAsset.setUserId(user.getId());
        userAsset.setStatus(0);
        //前端处理
//        //购买购买就是burn
//        BlockchainUtil.burn(user.getUsername(),token.getPrice(),"token");
        //minter转移到token的提出人
        BlockchainUtil.transfer(userService.getById(token.getUserId()).getUsername(),token.getPrice(),"token");

        userAssetService.save(userAsset);

        return new R().Success();



    }



    //用户个人中心可以查看数据
    //已购买所有权益
    @ApiOperation("已购买所有权益")
    @GetMapping("/getAllBuyAsset")
    public R getAllBuyAsset() {
        User user = ThreadLocalUtil.get("user");
        List<UserAsset> userAssets = userAssetService.list(new QueryWrapper<UserAsset>().eq("userId", user.getId()));
        List<GeneralToken> assetList = new ArrayList<>();
        userAssets.forEach(i->{
            assetList.add(generalTokenService.getById(i.getAssetId()));
        });

        return new R().Success(assetList);
    }


    //待使用权益
    @ApiOperation("待使用权益")
    @GetMapping("/getBuyAssetNotUse")
    public R getBuyAssetNotUse() {
        User user = ThreadLocalUtil.get("user");
        List<UserAsset> userAssets = userAssetService.list(new QueryWrapper<UserAsset>().eq("userId", user.getId()).eq("status",0));
        List<GeneralToken> assetList = new ArrayList<>();
        userAssets.forEach(i->{
            assetList.add(generalTokenService.getById(i.getAssetId()));
        });

        return new R().Success(assetList);
    }

    //权益详情
    @ApiOperation("权益详情")
    @GetMapping("/userAssetDetail")
    public R userAssetDetail(@RequestParam String id) {
        //获取数据资产
        GeneralToken asset = generalTokenService.getById(id);
        UserAssetDetailDto userAssetDetailDto = new UserAssetDetailDto();
        userAssetDetailDto.setId(asset.getId());
        userAssetDetailDto.setName(asset.getName());
        //获取数据资产对应的权益
        userAssetDetailDto.setRightIds(assetRightService.list(new QueryWrapper<AssetRight>().eq("businessId",id)).stream().map(AssetRight::getRightId).collect(Collectors.toList()));
        //设置文件
        List<FileDto> fileDtos = new ArrayList<>();

        List<File> files = fileService.list(new QueryWrapper<File>().eq("businessId", id));
        files.forEach(file -> {
            FileDto fileDto = new FileDto();
            fileDto.setFilePath(file.getFilePath());
            fileDto.setFileName(fileName(file.getFilePath()));
            fileDtos.add(fileDto);
        });
        userAssetDetailDto.setFileDtos(fileDtos);
        return new R().Success(userAssetDetailDto);

        // TODO: 2023/7/11 如果查看通用数据资产还要把其相关的文件带出来
    }


    //兑换权益-修改状态
    @ApiOperation("修改状态")
    @PostMapping("/modifyUserAssetStatus/{id}")
    public R modifyUserAssetStatus(@PathVariable String id) {
        User user = ThreadLocalUtil.get("user");
        UserAsset userAsset = userAssetService.getOne(new QueryWrapper<UserAsset>().eq("userId", user.getId()).eq("assetId", id));
        userAsset.setStatus(1);
        userAssetService.updateById(userAsset);
        return new R().Success();
        // TODO: 2023/7/11
        //如果兑换的权益为通用数据资产还要下载文件
    }

    //主页-新增数据资产公示
    @ApiOperation("主页-新增数据资产公示")
    @GetMapping("/getNewDataAssetList")
    public R<NewDataAssetListDto> getNewDataAssetList() {
        return new R().Success(generalTokenService.getNewDataAssetList());

    }








}

