package com.gintare.tasksmanager.task;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;

//@Entity
//@Table
public class Task {
    //@Id
    //@GeneratedValue(strategy = GenerationType.AUTO, generator = "system-uuid")
    //@GenericGenerator(name = "system-uuid", strategy = "uuid2")
    private String id;
    private String parentTaskId;
    private String name;
    private int timeSpend;
    private String group;
    private String assignee;
    private boolean isFinished;

    public Task() {
    }

    public Task(String id,
                String parentTaskId,
                String name,
                int timeSpend,
                String group,
                String assignee,
                boolean isFinished) {
        this.id = id;
        this.parentTaskId = parentTaskId;
        this.name = name;
        this.timeSpend = timeSpend;
        this.group = group;
        this.assignee = assignee;
        this.isFinished = isFinished;
    }

    public Task(String parentTaskId,
                String name,
                int timeSpend,
                String group,
                String assignee,
                boolean isFinished) {
        this.parentTaskId = parentTaskId;
        this.name = name;
        this.timeSpend = timeSpend;
        this.group = group;
        this.assignee = assignee;
        this.isFinished = isFinished;
    }

    public String getId() {
        return id;
    }

    public String getParentTaskId() {
        return parentTaskId;
    }

    public String getName() {
        return name;
    }

    public int getTimeSpend() {
        return timeSpend;
    }

    public String getGroup() {
        return group;
    }

    public String getAssignee() {
        return assignee;
    }

    public boolean isFinished() {
        return isFinished;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setParentTaskId(String parentTaskId) {
        this.parentTaskId = parentTaskId;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setTimeSpend(int timeSpend) {
        this.timeSpend = timeSpend;
    }

    public void setGroup(String group) {
        this.group = group;
    }

    public void setAssignee(String assignee) {
        this.assignee = assignee;
    }

    public void setFinished(boolean finished) {
        isFinished = finished;
    }

    @Override
    public String toString() {
        return "Task{" +
                "id='" + id + '\'' +
                ", parentTaskId='" + parentTaskId + '\'' +
                ", name='" + name + '\'' +
                ", timeSpend=" + timeSpend +
                ", group='" + group + '\'' +
                ", assignee='" + assignee + '\'' +
                ", isFinished=" + isFinished +
                '}';
    }

}
