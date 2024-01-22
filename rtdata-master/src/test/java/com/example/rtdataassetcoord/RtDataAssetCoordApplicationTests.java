package com.example.rtdataassetcoord;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.example.rtdataassetcoord.common.BlockchainUtil;
import com.example.rtdataassetcoord.dto.NewTopicListDto;
import com.example.rtdataassetcoord.entity.MediaRank;
import com.example.rtdataassetcoord.mapper.MediaRankMapper;
import com.example.rtdataassetcoord.mapper.QualificationReviewMapper;
import com.example.rtdataassetcoord.mapper.TopicMapper;
import com.example.rtdataassetcoord.page.PageFactory;
import com.example.rtdataassetcoord.task.MediaRankRate;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import java.math.BigDecimal;
import java.util.*;
import java.util.stream.Collectors;

@SpringBootTest
class RtDataAssetCoordApplicationTests { ;

    @Autowired
    RestTemplate restTemplate;


    @Test
    void contextLoads() throws JsonProcessingException {


    }

    @Test
    void test(){
    }

}
