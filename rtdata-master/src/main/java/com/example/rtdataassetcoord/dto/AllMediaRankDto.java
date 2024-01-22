package com.example.rtdataassetcoord.dto;


import com.example.rtdataassetcoord.entity.MediaRank;
import lombok.Data;

import java.util.List;

@Data
public class AllMediaRankDto {

    List<MediaRankMonthDto> rank1List;
    List<MediaRankMonthDto> rank2List;
    List<MediaRankMonthDto> rank3List;
}
