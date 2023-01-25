package com.luanhydev.dashboard.task;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;

@Configuration
public class TaskConfig {

    @Bean
    CommandLineRunner commandLineRunnerTask(
            TaskRepository repository) {
        return args -> {
            Task task1 = new Task(
                    "Do ticket 1",
                    false,
                    "1"
            );

            Task task2 = new Task(
                    "Do ticket 2",
                    false,
                    "1"
            );

            repository.saveAll(
                    List.of(task1, task2)
            );
        };
    }

}