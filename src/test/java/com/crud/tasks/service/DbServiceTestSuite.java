package com.crud.tasks.service;

import com.crud.tasks.domain.Task;
import com.crud.tasks.repository.TaskRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
public class DbServiceTestSuite {

    @InjectMocks
    DbService dbService;

    @Mock
    TaskRepository taskRepository;

    @Test
    public void getAllTasksTest() {
        //When
        dbService.getAllTasks();

        //Then
        verify(taskRepository, times(1)).findAll();
    }

    @Test
    public void getTaskTest() {
        //Given
        Task task = new Task(100L, "title", "desc");

        //When
        dbService.getTask(task.getId());

        //Then
        verify(taskRepository, times(1)).findById(100L);
    }

    @Test
    public void saveTaskTest() {
        //Given
        Task task = new Task(100L, "title", "desc");

        //When
        dbService.saveTask(task);

        //Then
        verify(taskRepository, times(1)).save(task);
    }

    @Test
    public void deleteTaskTest() {
        //Given
        Task task = new Task(100L, "title", "desc");

        //When
        dbService.deleteTask(task.getId());

        //Then
        verify(taskRepository, times(1)).deleteById(100L);
    }
}
