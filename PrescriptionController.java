package com.project.back_end.controllers;

import com.project.back_end.models.Prescription;
import com.project.back_end.services.PrescriptionService;
import com.project.back_end.services.TokenService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/api/prescriptions")
public class PrescriptionController {

    @Autowired
    private PrescriptionService prescriptionService;

    @Autowired
    private TokenService tokenService;

    /**
     * Save a new prescription
     * Token is passed as a PathVariable for authentication
     */
    @PostMapping("/save/{token}")
    public ResponseEntity<?> savePrescription(
            @PathVariable("token") String token,
            @Valid @RequestBody Prescription prescription) {

        // Validate token
        if (!tokenService.validateToken(token)) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                    .body(Map.of("message", "Invalid or expired token"));
        }

        // Save prescription using service
        Prescription savedPrescription = prescriptionService.savePrescription(prescription);

        return ResponseEntity.ok(Map.of(
                "message", "Prescription saved successfully",
                "prescriptionId", savedPrescription.getId()
        ));
    }
}
