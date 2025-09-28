package com.project.back_end.services;

import com.project.back_end.models.Appointment;
import com.project.back_end.repositories.AppointmentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

/**
 * Service class for managing appointments
 */
@Service
public class AppointmentService {

    @Autowired
    private AppointmentRepository appointmentRepository;

    /**
     * Book a new appointment
     *
     * @param appointment Appointment object to be saved
     * @return the saved Appointment
     */
    public Appointment bookAppointment(Appointment appointment) {
        // Save appointment to database
        return appointmentRepository.save(appointment);
    }

    /**
     * Get all appointments for a specific doctor on a given date
     *
     * @param doctorId Doctor ID
     * @param date     LocalDate of appointments
     * @return List of appointments
     */
    public List<Appointment> getAppointmentsByDoctorAndDate(Long doctorId, LocalDate date) {
        return appointmentRepository.findByDoctorIdAndAppointmentTimeBetween(
                doctorId,
                date.atStartOfDay(),
                date.atTime(23, 59, 59)
        );
    }
}
