package com.example.rtdataassetcoord.service.impl;

import com.example.rtdataassetcoord.dto.MediaRankMonthDto;
import com.example.rtdataassetcoord.entity.MediaRank;
import com.example.rtdataassetcoord.mapper.MediaRankMapper;
import com.example.rtdataassetcoord.service.MediaRankService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author ${author}
 * @since 2023-06-05
 */
@Service
public class MediaRankServiceImpl extends ServiceImpl<MediaRankMapper, MediaRank> implements MediaRankService {

    @Override
    public List<MediaRankMonthDto> getAllMediaRank() {
        return baseMapper.getAllMediaRank();
    }
}
