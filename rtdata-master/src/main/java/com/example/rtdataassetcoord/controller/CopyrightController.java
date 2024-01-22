package com.example.rtdataassetcoord.controller;



import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.example.rtdataassetcoord.common.R;
import com.example.rtdataassetcoord.common.ThreadLocalUtil;
import com.example.rtdataassetcoord.dto.UserCopyrightDto;
import com.example.rtdataassetcoord.entity.Copyright;
import com.example.rtdataassetcoord.entity.User;
import com.example.rtdataassetcoord.service.CopyrightService;
import com.example.rtdataassetcoord.vo.CopyrightVo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author ${author}
 * @since 2023-08-15
 */
@RestController
@Api(tags = "数据资产价值认证中心")
@RequestMapping("/api/rtdataassetcoord/copyright")
public class CopyrightController {

    @Autowired
    CopyrightService copyrightService;


    @ApiOperation("添加认证元数据信息")
    @PostMapping("/addCopyright")
    public R addCopyright(@RequestBody CopyrightVo copyrightVo) {
        Copyright copyright = new Copyright();
        BeanUtils.copyProperties(copyrightVo, copyright);
        copyrightService.save(copyright);
        return new R().Success();
    }


    @ApiOperation("查看个人所有认证")
    @GetMapping("/getUserCopyrights")
    public R<Copyright> getUserCopyrights() {
        User user = ThreadLocalUtil.get("user");
        QueryWrapper<Copyright> copyrightQueryWrapper = new QueryWrapper<>();
        copyrightQueryWrapper.eq("user_id", user.getId());
        List<Copyright> dtos = copyrightService.list(copyrightQueryWrapper);
        return new R().Success(dtos);
    }

    @ApiOperation("查看全部认证信息-所有机构")
    @GetMapping ("/getCopyRightList")
    public R<Copyright> getCopyRightList() {
        List<Copyright> list = copyrightService.list(null);
        return new R().Success(list);
    }


}
