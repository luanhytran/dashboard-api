package com.luanhydev.dashboard.contact;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;

@Configuration
public class ContactConfig {
    @Bean
    CommandLineRunner commandLineRunnerContact(
            ContactRepository repository) {
        return args -> {
            Contact contact1 = new Contact(
                    "Hy",
                    "Tran Luan",
                    "SWE",
                    "HCDC",
                    "KMS",
                    "...",
                    123
            );

            Contact contact2 = new Contact(
                    "Luan",
                    "Vo Thanh",
                    "SWE",
                    "HCDC",
                    "KMS",
                    "...",
                    456
            );

            repository.saveAll(
                    List.of(contact1, contact2)
            );
        };
    }
}
