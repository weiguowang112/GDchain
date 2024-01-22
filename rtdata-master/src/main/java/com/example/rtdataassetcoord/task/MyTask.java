package com.example.rtdataassetcoord.task;

import com.example.rtdataassetcoord.entity.MediaRank;
import com.example.rtdataassetcoord.mapper.MediaRankMapper;
import com.example.rtdataassetcoord.service.MediaRankService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class MyTask {

    @Autowired
    MediaRankMapper mediaRankMapper;


    @Autowired
    MediaRankService mediaRankService;

    //评分定时任务，每个月一号的0点计算评分
    @Scheduled(cron = "0 0 0 1 * ?")
    public void myTaskMethod() {
        List<MediaRankRate> mediaRankRate = mediaRankMapper.getMediaRankRate();
        List<Double> readRateList = mediaRankRate.stream().map(MediaRankRate::getReadRate).collect(Collectors.toList());
        Double maxRead = Collections.max(readRateList);
        Double minRead = Collections.min(readRateList);
        List<Double> commentRateList = mediaRankRate.stream().map(MediaRankRate::getCommentRate).collect(Collectors.toList());
        Double maxComment = Collections.max(commentRateList);
        Double minComment = Collections.min(commentRateList);
        List<Double> forwardRateList = mediaRankRate.stream().map(MediaRankRate::getForwardRate).collect(Collectors.toList());
        Double maxForward = Collections.max(forwardRateList);
        Double minForward = Collections.min(forwardRateList);
        List<Double> likeRateList = mediaRankRate.stream().map(MediaRankRate::getLikeRate).collect(Collectors.toList());
        Double maxLike = Collections.max(likeRateList);
        Double minLike = Collections.min(likeRateList);


        ArrayList<MediaRank> mediaRanks = new ArrayList<>();

        mediaRankRate.forEach(i->{
            double readFactor= ((i.getReadRate()-minRead)/(maxRead-minRead))*0.6;
            double commentFactor=((i.getCommentRate()-minComment)/(maxComment-minComment))*0.1;
            double forwardFactor=((i.getForwardRate()-minForward)/(maxForward-minForward))*0.25;
            double likeFactor=((i.getLikeRate()-minLike)/(maxLike-minLike))*0.05;
            mediaRanks.add(new MediaRank().setGrade(BigDecimal.valueOf(readFactor + commentFactor + forwardFactor + likeFactor)).setUserId(i.getUserId()));
        });

        Collections.sort(mediaRanks, (o1, o2) -> o2.getGrade().compareTo(o1.getGrade()));
        int total = mediaRanks.size();
        int top10PercentIndex = (int) (total * 0.1);
        int top35PercentIndex = (int) (total * 0.35);
        int top60PercentIndex = (int) (total * 0.6);

        for (int i = 0; i < mediaRanks.size(); i++) {
            MediaRank mediaRank = mediaRanks.get(i);
            if (i < top10PercentIndex) {
                mediaRank.setRank(1);
            } else if (i < top35PercentIndex) {
                mediaRank.setRank(2);
            } else if (i < top60PercentIndex) {
                mediaRank.setRank(3);
            }
        }

        mediaRankService.saveBatch(mediaRanks);
    }

}
