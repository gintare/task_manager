package com.gintare.tasksmanager.task;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class TaskControllerTest {

    private TaskController underTest = new TaskController();

    @Test
    void isSubtasksFinished() {
        //given
        Task task100 = new Task(
                "task100_id",
                null,
                "Task1",
                4,
                "MEDIUM_PRIORITY",
                "Gintare",
                false
        );
        Task task101 = new Task(
                "task101_id",
                "task100_id",
                "Task1",
                4,
                "MEDIUM_PRIORITY",
                "Gintare",
                true
        );
        Task task102 = new Task(
                "task102_id",
                "task100_id",
                "Task1",
                4,
                "MEDIUM_PRIORITY",
                "Gintare",
                true
        );
        List<Task> allSubtasks = new ArrayList<Task>();
        allSubtasks.add(task101);
        allSubtasks.add(task102);
        boolean subtasksFinished = true;

        //when
        boolean result = this.underTest.isSubtasksFinished(allSubtasks);

        //then
        assertEquals(result, subtasksFinished);

    }

    @Test
    void tasksSubtasks() {
        //given
        Task task100 = new Task(
                "task100_id",
                null,
                "Task1",
                4,
                "MEDIUM_PRIORITY",
                "Gintare",
                false
        );
        Task task101 = new Task(
                "task101_id",
                "task100_id",
                "Task1",
                4,
                "MEDIUM_PRIORITY",
                "Gintare",
                false
        );
        Task task102 = new Task(
                "task102_id",
                "task100_id",
                "Task1",
                4,
                "MEDIUM_PRIORITY",
                "Gintare",
                false
        );
        List<Task> allTasks = new ArrayList<Task>();
        allTasks.add(task100);
        allTasks.add(task101);
        allTasks.add(task102);
        int giventSubtasksCount = 2;

        //when
        List<Task> result = underTest.tasksSubtasks(task100, allTasks);

        //then
        assertEquals(result.size(), giventSubtasksCount);
    }
}