package com.project.back_end.services;

import com.project.back_end.models.Appointment;
import com.project.back_end.models.Doctor;
import com.project.back_end.repositories.AppointmentRepository;
import com.project.back_end.repositories.DoctorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class DoctorService {

    @Autowired
    private DoctorRepository doctorRepository;

    @Autowired
    private AppointmentRepository appointmentRepository;

    /**
     * Validate doctor login credentials
     *
     * @param email    Doctor's email
     * @param password Doctor's password
     * @return Doctor object if valid, null otherwise
     */
    public Doctor validateDoctorLogin(String email, String password) {
        return doctorRepository.findByEmailAndPassword(email, password).orElse(null);
    }

    /**
     * Get available time slots for a doctor on a specific date
     *
     * @param doctorId Doctor ID
     * @param date     LocalDate
     * @return List of available times as strings (HH:mm)
     */
    public List<String> getAvailableTimes(Long doctorId, LocalDate date) {
        // Fetch doctor
        Doctor doctor = doctorRepository.findById(doctorId).orElse(null);
        if (doctor == null) {
            return List.of(); // empty list if doctor not found
        }

        // Get all booked appointments for that doctor on the date
        List<LocalTime> bookedTimes = appointmentRepository
                .findByDoctorIdAndAppointmentTimeBetween(
                        doctorId,
                        date.atStartOfDay(),
                        date.atTime(23, 59, 59))
                .stream()
                .map(a -> a.getAppointmentTime().toLocalTime())
                .collect(Collectors.toList());

        // Filter available times
        return doctor.getAvailableTimes().stream()
                .filter(time -> !bookedTimes.contains(time))
                .collect(Collectors.toList());
    }
}
