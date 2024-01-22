package com.example.rtdataassetcoord.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.example.rtdataassetcoord.dto.TaskListDto;
import com.example.rtdataassetcoord.dto.TaskReviewListDto;
import com.example.rtdataassetcoord.entity.Task;
import com.example.rtdataassetcoord.entity.TopicTask;
import com.baomidou.mybatisplus.extension.service.IService;
import com.example.rtdataassetcoord.page.PageModel;
import org.apache.ibatis.annotations.Param;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author ${author}
 * @since 2023-06-26
 */
public interface TopicTaskService extends IService<TopicTask> {

    PageModel<TaskListDto> getTaskByUserId(Integer id);

    PageModel<TaskReviewListDto> getReviewTaskByUserId(Integer id);
}
