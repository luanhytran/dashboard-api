package com.luanhydev.dashboard.task;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class TaskService {

    private final TaskRepository taskRepository;

    public List<Task> getTasks() {
        return taskRepository.findAll();
    }

    public Task getTask(Long taskId) {
        Optional<Task> task = taskRepository.findById(taskId);
        if(task.isPresent()) {
            return task.get();
        }
        throw new IllegalStateException("task with id " + taskId + " does not exists");
    }

    public void addNewTask(Task task) {
        taskRepository.save(task);
    }

    public void deleteTask(Long taskId) {
        boolean exists = taskRepository.existsById(taskId);
        if(!exists) {
            throw new IllegalStateException("task with id " + taskId + " does not exists");
        }
        taskRepository.deleteById(taskId);
    }

    @Transactional // Help don't have to write jpql and can use setter to update data
    public void updateTask(Long taskId, String task, Boolean isCompleted, String userId) {
        Task currentTask = taskRepository.findById(taskId).orElseThrow(() -> {
            throw new IllegalStateException("task with id " + taskId + " does not exists");
        });

        if(task != null && task.length() > 0) {
            currentTask.setTask(task);
        }

        if(isCompleted != null) {
            currentTask.setIsCompleted(isCompleted);
        }else {
            currentTask.setIsCompleted(false);
        }

        if(userId != null && userId.length() > 0) {
            currentTask.setUserId(userId);
        }
    }
}
