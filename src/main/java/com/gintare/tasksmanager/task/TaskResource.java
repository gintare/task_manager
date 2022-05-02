package com.gintare.tasksmanager.task;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

@RestController
public class TaskResource {

    ConcurrentMap<String, Task> tasks = new ConcurrentHashMap<>();

    @GetMapping("/v1/api/{id}")
    @ApiOperation("Get the task with specific id")
    public Task getTask(@PathVariable String id){
        return tasks.get(id);
    }

    @GetMapping("/v1/api/")
    @ApiOperation("Get all tasks")
    public List<Task> getAllTasks(){
        return new ArrayList<Task>(tasks.values());
    }

    @PostMapping("/v1/api/")
    @ApiOperation("Add new task")
    public Task addTask(@RequestBody Task task){
        tasks.put(task.getId(), task);
        return task;
    }

    @DeleteMapping("/v1/api/{id}")
    @ApiOperation("Delete task")
    public Task deleteTask(@PathVariable String id){
        Task task = tasks.get(id);
        tasks.remove(id);
        return task;
    }


}
