package com.project.back_end.controllers;

import com.project.back_end.services.DoctorService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/doctors")
public class DoctorController {
    private final DoctorService doctorService;
    public DoctorController(DoctorService doctorService) { this.doctorService = doctorService; }

    @GetMapping
    public ResponseEntity<?> getAvailableDoctors(@RequestParam String specialty, @RequestParam String time) {
        return ResponseEntity.ok(doctorService.getAvailableDoctors(specialty, time));
    }
}
