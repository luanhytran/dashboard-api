package com.luanhydev.dashboard.dashboard;

import com.luanhydev.dashboard.widget.Widget;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.io.File;
import java.util.List;

@Configuration
public class DashboardConfig {

    @Bean
    CommandLineRunner commandLineRunnerDashboard(
       DashboardRepository repository) {
        return args -> {
            ObjectMapper objectMapper = new ObjectMapper();

            ObjectNode widgetConfigs = objectMapper.readValue(new File("src/main/java/com/luanhydev/dashboard/widget_configs.json"), ObjectNode.class);

            Dashboard dashboard1 = new Dashboard(
                    123L,
                    "Finance",
                    "Process"
            );

            Widget widget1 = new Widget(
                    "To do list",
                    "To do",
                    100,
                    100,
                    widgetConfigs.toString()
            );

            dashboard1.addWidget(widget1);

            repository.saveAll(
                    List.of(dashboard1)
            );
        };
    }
}
