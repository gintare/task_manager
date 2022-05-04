package com.gintare.tasksmanager.task;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Controller
public class TaskController {
    private static Logger LOG = LoggerFactory.getLogger( TaskController.class);

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
        LOG.info("Gintares log: entered tasks/add controller.");
        model.addAttribute("tasks", taskResource.getAllTasks());;
        model.addAttribute("task", new Task());

        return "edit";
    }

    @RequestMapping(path = "tasks", method = RequestMethod.POST)
    public String saveTask(Task task){
        System.out.println("Gintare loging: enetered tasks POST controller.");
        if(task.getId().isEmpty()){
            task.setId(UUID.randomUUID().toString().toUpperCase().replace("-", ""));
        }else{
            taskResource.deleteTask(task.getId());
        }
        taskResource.addTask(task);


        return "redirect:/tasks";
    }

    @RequestMapping(path = "/tasks", method = RequestMethod.GET)
    public String getAllTasks(Model model){
        System.out.println("Gintares logging: entered tasks GET controller.");
        model.addAttribute("tasks", taskResource.getAllTasks());
        return "tasks";
    }

    @RequestMapping(path = "/tasks/edit/{id}", method = RequestMethod.GET)
    public String editTask(Model model, @PathVariable(value = "id") String id){
        System.out.println("Gintares logging: entered tasks/edit/id GET controller.");
        model.addAttribute("tasks", taskResource.getAllTasks());
        model.addAttribute("task", taskResource.getTask(id));
        return "edit";
    }

    @RequestMapping(path = "/tasks/delete/{id}", method = RequestMethod.GET)
    public String deleteTask(Model model, @PathVariable(value = "id") String id){
        taskResource.deleteTask(id);
        return "redirect:/tasks";
    }

    @RequestMapping(path = "/tasks/finished/{id}", method = RequestMethod.GET)
    public String finishedTask(Model model, @PathVariable(value = "id") String id){
        Task task = this.taskResource.getTask(id);
        List<Task> allTasks = this.taskResource.getAllTasks();
        List<Task> subtasks = tasksSubtasks(task, allTasks);
        boolean isSubtasksFinished = isSubtasksFinished(subtasks);

        if(isSubtasksFinished == false){
            task.setFinished(false);
            return "redirect:/tasks?error_message_subtasks_not_finished";
        }

        task.setFinished(true);
        model.addAttribute("task", task);
        //model.addAttribute("message", "Subtasks not finished");
        return "redirect:/tasks";
    }

    /**
     * This method return boolean value that indicates all subtasks are finsished or not
     * @param subtasks
     * @return
     */
    public boolean isSubtasksFinished(List<Task> subtasks){
        boolean subtasksFinished = true;
        for(Task subtask : subtasks){
            if(subtask.isFinished() == false){
                subtasksFinished = false;
            }
        }
        return subtasksFinished;
    }

    /**
     * Method return subtask's list of give task
     * @param task
     * @return
     */
    public List<Task> tasksSubtasks(Task task, List<Task> allTasks){
        List<Task> subtasks = new ArrayList<Task>();
        for(Task task1 : allTasks) {
            if( task.getId().equalsIgnoreCase(task1.getParentTaskId())){
                subtasks.add(task1);
            }
        }
        return subtasks;
    }
}
