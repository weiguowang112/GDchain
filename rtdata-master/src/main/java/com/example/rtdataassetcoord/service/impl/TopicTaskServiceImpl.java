package com.example.rtdataassetcoord.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.example.rtdataassetcoord.dto.TaskListDto;
import com.example.rtdataassetcoord.dto.TaskReviewListDto;
import com.example.rtdataassetcoord.entity.AssetRight;
import com.example.rtdataassetcoord.entity.File;
import com.example.rtdataassetcoord.entity.Task;
import com.example.rtdataassetcoord.entity.TopicTask;
import com.example.rtdataassetcoord.mapper.TopicTaskMapper;
import com.example.rtdataassetcoord.page.PageFactory;
import com.example.rtdataassetcoord.page.PageModel;
import com.example.rtdataassetcoord.service.FileService;
import com.example.rtdataassetcoord.service.TaskService;
import com.example.rtdataassetcoord.service.TopicTaskService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.stream.Collectors;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author ${author}
 * @since 2023-06-26
 */
@Service
public class TopicTaskServiceImpl extends ServiceImpl<TopicTaskMapper, TopicTask> implements TopicTaskService {
    @Autowired
    TaskService taskService;


    @Autowired
    FileService fileService;
    @Override
    public PageModel<TaskListDto> getTaskByUserId(Integer id) {
        IPage<TaskListDto> taskListDtoIPage = baseMapper.getTaskByUserId(PageFactory.defaultPage(), id);
        taskListDtoIPage.getRecords().forEach(taskListDto -> {
            taskListDto.setTasks(taskService.list(new QueryWrapper<Task>().eq("business_id", taskListDto.getId())).stream().map(Task::getTask).collect(Collectors.toList()));
        });
        return PageModel.transform(taskListDtoIPage);
    }

    @Override
    public PageModel<TaskReviewListDto> getReviewTaskByUserId(Integer id) {
        IPage<TaskReviewListDto> taskReviewListDtoIPage = baseMapper.getReviewTaskByUserId(PageFactory.defaultPage(), id);
        taskReviewListDtoIPage.getRecords().forEach(taskListDto -> {
            taskListDto.setTasks(taskService.list(new QueryWrapper<Task>().eq("business_id", taskListDto.getId())).stream().map(Task::getTask).collect(Collectors.toList()));
            taskListDto.setFileName(fileService.getOne(new QueryWrapper<File>().eq("businessId",taskListDto.getId())).getFilePath());
        });
        return PageModel.transform(taskReviewListDtoIPage);
    }

}
