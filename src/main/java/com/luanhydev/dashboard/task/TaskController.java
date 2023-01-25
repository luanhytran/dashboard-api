package com.luanhydev.dashboard.task;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "api/v1/tasks")
public class TaskController {

    private final TaskService taskService;

    @Autowired
    public TaskController(TaskService taskService) {
        this.taskService = taskService;
    }

    @GetMapping
    public List<Task> getTasks() {
        return taskService.getTasks();
    }

    @GetMapping(path = "{taskId}")
    public Task getTask(@PathVariable("taskId") Long taskId) {
        return taskService.getTask(taskId);
    }

    @PostMapping
    public void saveNewTask(@RequestBody Task task) {
        taskService.addNewTask(task);
    }

    @DeleteMapping(path = "{taskId}")
    public void deleteTask(@PathVariable("taskId") Long taskId) {
        taskService.deleteTask(taskId);
    }

    @PutMapping(path = "{taskId}")
    public void updateTask(
            @PathVariable("taskId") Long taskId,
            @RequestParam(required = false) String task,
            @RequestParam(required = false) Boolean isCompleted,
            @RequestParam(required = false) String userId) {
        taskService.updateTask(taskId, task, isCompleted, userId);
    }
}
