package com.crud.tasks.mapper;

import com.crud.tasks.domain.Task;
import com.crud.tasks.domain.TaskDto;
import org.junit.Assert;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Arrays;
import java.util.List;


@RunWith(SpringRunner.class)
@SpringBootTest
class TaskMapperTestSuite {
    @Autowired
    TaskMapper taskMapper;

    @Test
    void mapToTask() {
        //Given
        TaskDto taskDto = new TaskDto(1L, "title", "content");
        //When
        Task resultMapper = taskMapper.mapToTask(taskDto);
        //Then
        Assert.assertEquals(Long.valueOf(1L), resultMapper.getId());
        Assert.assertEquals("title", resultMapper.getTitle());
        Assert.assertEquals("content", resultMapper.getContent());
    }

    @Test
    void mapToTaskDto() {
        //Given
        Task task = new Task(1L, "title", "content");
        //When
        TaskDto resultMapper = taskMapper.mapToTaskDto(task);
        //Then
        Assert.assertEquals(Long.valueOf(1L), resultMapper.getId());
        Assert.assertEquals("title", resultMapper.getTitle());
        Assert.assertEquals("content", resultMapper.getContent());
    }

    @Test
    void mapToTaskDtoList() {
        Task task = new Task(1L, "title", "content");
        Task task2 = new Task(2L, "title2", "content2");
        Task task3 = new Task(3L, "title3", "content3");
        List<Task> tasks = Arrays.asList(task, task2, task3);
        //When
        List<TaskDto> resultMapper = taskMapper.mapToTaskDtoList(tasks);
        //Then
        Assert.assertEquals(3, resultMapper.size());
        Assert.assertEquals(Long.valueOf(1L), resultMapper.get(0).getId());
        Assert.assertEquals("title", resultMapper.get(0).getTitle());
        Assert.assertEquals("content", resultMapper.get(0).getContent());
        Assert.assertEquals(Long.valueOf(2L), resultMapper.get(1).getId());
        Assert.assertEquals("title2", resultMapper.get(1).getTitle());
        Assert.assertEquals("content2", resultMapper.get(1).getContent());
        Assert.assertEquals(Long.valueOf(3L), resultMapper.get(2).getId());
        Assert.assertEquals("title3", resultMapper.get(2).getTitle());
        Assert.assertEquals("content3", resultMapper.get(2).getContent());
    }
}