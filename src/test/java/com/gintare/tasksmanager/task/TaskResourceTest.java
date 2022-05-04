package com.gintare.tasksmanager.task;


import org.assertj.core.util.Lists;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.List;
import java.util.Objects;

import static org.junit.jupiter.api.Assertions.*;

//@DataJpaTest
class TaskResourceTest {

    //@Autowired
    private TaskResource underTest = new TaskResource();


    @Test
    void getTask() {
        //given
        Task task1 = new Task(
                "task1_id",
                null,
                "Task1",
                4,
                "MEDIUM_PRIORITY",
                "Gintare",
                false
        );
        underTest.addTask(task1);
        Task task2 = new Task(
                "task2_id",
                null,
                "Task2",
                3,
                "MEDIUM_PRIORITY",
                "Gintare",
                false
        );
        underTest.addTask(task2);

        //when
        Task resultTask = underTest.getTask("task1_id");

        //then
        assert(resultTask).equals(task1);
    }

    @Test
    void getAllTasks() {
        //given
        Task task1 = new Task(
                "task1_id",
                null,
                "Task1",
                4,
                "MEDIUM_PRIORITY",
                "Gintare",
                false
        );
        underTest.addTask(task1);
        Task task2 = new Task(
                "task2_id",
                null,
                "Task2",
                3,
                "MEDIUM_PRIORITY",
                "Gintare",
                false
        );
        underTest.addTask(task2);
        int addedTasks = 2;

        //when
        List<Task> result = underTest.getAllTasks();

        //then
        assertEquals(result.size(),  addedTasks);

    }

    @Test
    void addTask() {
        //given
        Task task1 = new Task(
                "task100_id",
                null,
                "Task1",
                4,
                "MEDIUM_PRIORITY",
                "Gintare",
                false
        );
        underTest.addTask(task1);

        //when
        Task resultTask = underTest.getTask("task100_id");

        //then
        assertEquals(resultTask, task1);
    }

    @Test
    void deleteTask() {
        //given
        Task task1 = new Task(
                "task200_id",
                null,
                "Task1",
                4,
                "MEDIUM_PRIORITY",
                "Gintare",
                false
        );
        underTest.addTask(task1);

        //when
        underTest.deleteTask("task200_id");

        //then
        assertEquals(underTest.getAllTasks().size(), 0);
    }
}