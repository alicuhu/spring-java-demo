package com.example.demo.student;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.time.LocalDate;
import java.time.Month;
import java.util.List;

@Configuration
public class StudentConfig {
    @Bean
    CommandLineRunner commandLineRunner(StudentRepository studentRepository) {
        return args -> {
            Student davion = new Student(
                    "Davion",
                    "dav@mail.com",
                    LocalDate.of(1996, Month.NOVEMBER, 17),
                    26
            );
            Student alex = new Student(
                    "Alex",
                    "alex@mail.com",
                    LocalDate.of(1996, Month.OCTOBER, 17),
                    26
            );

            studentRepository.saveAll(
                    List.of(davion, alex)
            );
        };
    }
}
