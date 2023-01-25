package com.luanhydev.dashboard.widget;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class WidgetConfig {

    @Bean
    CommandLineRunner commandLineRunnerWidget(
        WidgetRepository repository) {
            return args -> {

            };
        }
}
