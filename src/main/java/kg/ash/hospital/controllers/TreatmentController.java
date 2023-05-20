package kg.ash.hospital.controllers;

import jakarta.validation.Valid;
import kg.ash.hospital.entities.appointments.Appointment;
import kg.ash.hospital.entities.appointments.Treatment;
import kg.ash.hospital.entities.doctors.Doctor;
import kg.ash.hospital.services.interfaces.AppointmentService;
import kg.ash.hospital.services.interfaces.DoctorService;
import kg.ash.hospital.services.interfaces.PatientService;
import kg.ash.hospital.services.interfaces.TreatmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class TreatmentController {

    private final TreatmentService treatmentService;

    private final AppointmentService appointmentService;

    private final PatientService patientService;

    private final DoctorService doctorService;

    @Autowired
    public TreatmentController(TreatmentService treatmentService, AppointmentService appointmentService, PatientService patientService, DoctorService doctorService) {
        this.treatmentService = treatmentService;
        this.appointmentService = appointmentService;
        this.patientService = patientService;
        this.doctorService = doctorService;
    }

    @GetMapping("/treatment/add")
    public String add(@RequestParam("appointmentId") int appointmentId, @RequestParam("patientId") int patientId, @RequestParam("doctorId") int doctorId, Model model) {
        Treatment treatment = new Treatment();
        treatment.setPatient(patientService.find(patientId));
        treatment.setDoctor(doctorService.find(doctorId));

        model.addAttribute("appointmentId", appointmentId);
        model.addAttribute("treatment", treatment);

        return "treatments/form-page";
    }

    @PostMapping("/treatment/save")
    public String save(@ModelAttribute("treatment") @Valid Treatment treatment, BindingResult bindingResult, @RequestParam("appointmentId") int appointmentId, Model model) {
        if (!bindingResult.hasErrors()) {
            treatmentService.save(treatment);

            Appointment appointment = appointmentService.find(appointmentId);
            appointment.setTreatment(treatment);

            appointmentService.save(appointment);

            model.addAttribute("successMessage", "The treatment has been saved successfully!");
        }

        else {
            model.addAttribute("errorMessage", "The treatment hasn't been saved!");
        }

        return "treatments/form-page";
    }
}
