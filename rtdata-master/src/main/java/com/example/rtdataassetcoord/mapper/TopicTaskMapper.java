package com.example.rtdataassetcoord.mapper;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.rtdataassetcoord.dto.TaskListDto;
import com.example.rtdataassetcoord.dto.TaskReviewListDto;
import com.example.rtdataassetcoord.dto.VoteListDto;
import com.example.rtdataassetcoord.entity.TopicTask;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author ${author}
 * @since 2023-06-26
 */

@Mapper
public interface TopicTaskMapper extends BaseMapper<TopicTask> {


    IPage<TaskListDto> getTaskByUserId(@Param("page") IPage page, Integer id);

    IPage<TaskReviewListDto> getReviewTaskByUserId(@Param("page") IPage page, Integer id);


}
