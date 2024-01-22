package com.example.rtdataassetcoord.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.example.rtdataassetcoord.common.BlockchainUtil;
import com.example.rtdataassetcoord.common.R;
import com.example.rtdataassetcoord.dto.UserDto;
import com.example.rtdataassetcoord.dto.UserLoginDto;
import com.example.rtdataassetcoord.dto.UserRegDto;
import com.example.rtdataassetcoord.entity.MediaRank;
import com.example.rtdataassetcoord.entity.Space;
import com.example.rtdataassetcoord.entity.User;
import com.example.rtdataassetcoord.entity.UserToken;
import com.example.rtdataassetcoord.service.MediaRankService;
import com.example.rtdataassetcoord.service.SpaceService;
import com.example.rtdataassetcoord.service.UserService;
import com.example.rtdataassetcoord.service.UserTokenService;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.UUID;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author huangs
 * @since 2023-04-14
 */
@RestController
@RequestMapping("/api/rtdataassetcoord/auth")
@Api(tags = "登录注册模块")
public class AuthController {

    @Autowired
    UserService userService;
    @Autowired
    UserTokenService userTokenService;
    @Autowired
    SpaceService spaceService;

    @Autowired
    MediaRankService mediaRankService;

    @ApiOperation("登录")
    @PostMapping("/login")
    public R login(@RequestBody UserLoginDto userLoginDto){
        QueryWrapper<User> userQueryWrapper = new QueryWrapper<>();
        userQueryWrapper.eq("username", userLoginDto.getUsername());
        User user = userService.getOne(userQueryWrapper);

        if(user!=null && user.getPassword().equals(userLoginDto.getPassword())){
            UserToken userToken = new UserToken();
            userToken.setUserId(user.getId()).setToken(UUID.randomUUID().toString()).setCreateTime(new Date()).setExpireTime(new Date(userToken.getCreateTime().getTime() + 1000 * 60 * 60 * 24 * 7));
            userTokenService.save(userToken);
            return new R().Success(userToken.getToken());
        }
        return new R().Error("用户不存在或密码错误");
    }

    //注册
    @ApiOperation("注册")
    @PostMapping("/Reg")
    public R<User> Reg(@RequestBody UserRegDto userRegDto){
        Space space = spaceService.getById(userRegDto.getSpaceId());
        if(space!=null){
            User user = userService.getOne(new QueryWrapper<User>().eq("username", userRegDto.getUsername()));
            if(user!=null){
                return new R().Error("用户已存在");
            }
            else {
                user = new User();
                user.setSpaceId(userRegDto.getSpaceId())
                    .setUuid(UUID.randomUUID().toString())
                    .setUsername(userRegDto.getUsername())
                    .setPassword(userRegDto.getPassword())
                    .setOrgName(userRegDto.getOrgName())
                    .setPhone(userRegDto.getPhone())
                            .setHeadImg(userRegDto.getHeadImg());
                userService.save(user);
                //区块链网络身份注册
                BlockchainUtil.enroll(userRegDto.getSpaceId(), userRegDto.getUsername());

                //账户创建（通证转移）
                BlockchainUtil.transfer(userRegDto.getUsername(), 5, "token");

                return new R().Success(user);
            }
        }
        else {
            return new R().Error("space信息不存在");
        }
    }


    //注销
    //需要携带token
    @ApiOperation("注销")
    @PostMapping("/logout")
    public R logout(@RequestHeader("token") String token){
        QueryWrapper<UserToken> tokenQueryWrapper = new QueryWrapper<UserToken>().eq("token", token);
        UserToken userToken = userTokenService.getOne(tokenQueryWrapper);
        if(userToken!=null){
            userTokenService.removeById(userToken);
        }
        return new R().Success();
    }


    @ApiOperation("查看当前登录用户信息")
    @GetMapping("/user")
    public R<UserDto> UserAuth(@RequestHeader("token") String token){
        if(token!=null){
            UserToken userToken = userTokenService.getOne(new QueryWrapper<UserToken>().eq("token", token));
            if(userToken!=null){
                UserDto userDto = new UserDto();
                User user = userService.getById(userToken.getUserId());
                BeanUtils.copyProperties(user,userDto);
                //设置评级，评分

                // 获取当前日期和时间
                LocalDateTime now = LocalDateTime.now();

                // 获取上个月的日期
                LocalDateTime lastMonth = now.minusMonths(1);

                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
                String lastMonthString = lastMonth.withDayOfMonth(1).format(formatter);
                MediaRank one = mediaRankService.getOne(new QueryWrapper<MediaRank>().eq("userId",user.getId()).ge("create_time", lastMonthString)
                        .lt("create_time", now.format(formatter)));
                if (one!=null) {
                    userDto.setRank(one.getRank());
                    userDto.setGrade(one.getGrade());
                }

                return new R().Success(userDto);
            }
            else {
                return new R().Error("授权信息错误");
            }
        }
        else {
            return new R().Error("缺少授权信息");
        }

    }



}

