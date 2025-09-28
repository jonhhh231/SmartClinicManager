package com.project.back_end.models;

import jakarta.persistence.*;
import java.util.List;

@Entity
public class Doctor {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String firstName;
    private String lastName;
    private String specialization;

    @ElementCollection
    private List<String> availableTimes;

    private String email;
    private String phone;

    // getters and setters
}
