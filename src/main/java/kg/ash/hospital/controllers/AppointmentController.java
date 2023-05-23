package kg.ash.hospital.controllers;

import kg.ash.hospital.entities.appointment.Appointment;
import kg.ash.hospital.services.interfaces.appointment.AppointmentService;
import kg.ash.hospital.services.interfaces.doctor.DoctorService;
import kg.ash.hospital.services.interfaces.patient.PatientService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class AppointmentController {

    private final AppointmentService appointmentService;

    private final DoctorService doctorService;

    private final PatientService patientService;

    @Autowired
    public AppointmentController(AppointmentService appointmentService, DoctorService doctorService, PatientService patientService) {
        this.appointmentService = appointmentService;
        this.doctorService = doctorService;
        this.patientService = patientService;
    }

    @GetMapping("/appointments")
    public String appointments(Model model) {
        model.addAttribute("appointments", appointmentService.findByCurrentDoctor());

        return "appointments/list-page";
    }

    @GetMapping("/appointments/patient")
    public String patientAppointments(@RequestParam("id") int id, Model model) {
        model.addAttribute("appointments", appointmentService.findByPatientId(id));

        return "appointments/list-page";
    }

    @GetMapping("/appointment")
    public String appointment(@RequestParam("id") int id, Model model) {
        model.addAttribute("appointment", appointmentService.find(id));

        return "appointments/details-page";
    }

    @GetMapping("/appointments/add")
    public String add(@RequestParam("id") int patientId, Model model) {
        model.addAttribute("patientId", patientId);
        model.addAttribute("doctors", doctorService.findAllOrderByIdDesc(25, true));

        return "appointments/doctor-select-page";
    }

    @PostMapping("/appointment/doctor/find")
    public String findDoctor(@RequestParam("fullName") String fullName,
                             @RequestParam("patientId") int patientId, Model model) {

        model.addAttribute("patientId", patientId);
        model.addAttribute("doctors", doctorService.findByFullName(fullName));

        return "appointments/doctor-select-page";
    }

    @GetMapping("/appointment/form")
    public String form(@RequestParam("patientId") int patientId,
                       @RequestParam("doctorId") int doctorId,
                       Model model) {

        Appointment appointment = new Appointment();
        appointment.setPatient(patientService.find(patientId));
        appointment.setDoctor(doctorService.find(doctorId));

        model.addAttribute("appointment", appointment);

        return "appointments/form-page";
    }

    @GetMapping("/appointment/delete")
    public String delete(@RequestParam("id") int id) {
        appointmentService.delete(id);

        return "redirect:/appointments";
    }

    @PostMapping("/appointment/save")
    public String save(@ModelAttribute("appointment") Appointment appointment, Model model) {
        appointmentService.save(appointment);

        return "redirect:/appointments";
    }

}
