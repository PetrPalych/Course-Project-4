package kg.ash.hospital.controllers;

import jakarta.validation.Valid;
import kg.ash.hospital.entities.appointments.Appointment;
import kg.ash.hospital.services.interfaces.AppointmentService;
import kg.ash.hospital.services.interfaces.DoctorService;
import kg.ash.hospital.services.interfaces.PatientService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class RegistrarController {

    private final AppointmentService appointmentService;

    private final DoctorService doctorService;

    private final PatientService patientService;

    @Autowired
    public RegistrarController(AppointmentService appointmentService, PatientService patientService, DoctorService doctorService, PatientService patientService1) {
        this.appointmentService = appointmentService;
        this.doctorService = doctorService;
        this.patientService = patientService1;
    }

    @GetMapping("/appointments")
    public String appointments(Model model) {
        model.addAttribute("appointments", appointmentService.findAllOrderByIdDesk(25));

        return "appointments/list-page";
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
