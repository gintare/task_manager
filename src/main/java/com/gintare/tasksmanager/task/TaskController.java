package com.gintare.tasksmanager.task;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.UUID;

@Controller
public class TaskController {

    private TaskResource taskResource;

    @Autowired
    public void setTaskResource(TaskResource taskResource) {
        this.taskResource = taskResource;
    }

    @RequestMapping(path = "/")
    public String index(){
        return "index";
    }

    @RequestMapping(path = "/tasks/add", method = RequestMethod.GET)
    public String createTask(Model model){
        model.addAttribute("task", new Task());
        return "edit";
    }

    @RequestMapping(path = "tasks", method = RequestMethod.POST)
    public String saveTask(Task task){
        System.out.println("Gintare loging");
        task.setId(UUID.randomUUID().toString().toUpperCase().replace("-", ""));
        taskResource.addTask(task);
        return "redirect:/tasks";
    }

    @RequestMapping(path = "/tasks", method = RequestMethod.GET)
    public String getAllTasks(Model model){
        model.addAttribute("tasks", taskResource.getAllTasks());
        return "tasks";
    }

    @RequestMapping(path = "/tasks/edit/{id}", method = RequestMethod.GET)
    public String editTask(Model model, @PathVariable(value = "id") String id){
        model.addAttribute("task", taskResource.getTask(id));
        return "edit";
    }

    @RequestMapping(path = "/tasks/delete/{id}", method = RequestMethod.GET)
    public String deleteTask(Model model, @PathVariable(value = "id") String id){
        taskResource.deleteTask(id);
        return "redirect:/tasks";
    }
}
