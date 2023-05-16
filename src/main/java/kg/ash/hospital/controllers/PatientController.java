package kg.ash.hospital.controllers;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;

import kg.ash.hospital.entities.patients.Patient;
import kg.ash.hospital.services.interfaces.PatientService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

@Controller
public class PatientController {

    private final PatientService patientService;

    @Autowired
    public PatientController(PatientService patientService) {
        this.patientService = patientService;
    }

    @GetMapping("/patients")
    public String patients(@RequestParam("active") boolean active, Model model) {
        model.addAttribute("patients", patientService.findAllOrderByIdDesc(25, active));

        return "patients/list-page";
    }

    @GetMapping("/patient")
    public String patient(@RequestParam("id") int id, Model model) {
        model.addAttribute("patient", patientService.find(id));

        return "patients/details-page";
    }

    @PostMapping("/patient/find")
    public String find(@RequestParam("fullName") String fullName, Model model) {
        model.addAttribute("patients", patientService.findByFullName(fullName));

        return "patients/list-page";
    }

    @GetMapping("/patient/add")
    public String add(Model model) {
        Patient patient = new Patient();
        model.addAttribute("patient", patient);

        return "patients/form-page";
    }

    @GetMapping("/patient/update")
    public String update(@RequestParam("id") int id, Model model) {
        model.addAttribute("patient", patientService.find(id));

        return "patients/form-page";
    }

    @GetMapping("/patient/delete")
    public String delete(@RequestParam("id") int id, HttpServletRequest httpServletRequest) {
        patientService.delete(id);

        return "redirect:" + httpServletRequest.getHeader("Referer");
    }

    @PostMapping("/patient/save")
    public String save(@ModelAttribute("patient") @Valid Patient patient, BindingResult bindingResult, Model model) {
        if (!bindingResult.hasErrors()) {
            patientService.save(patient);
            model.addAttribute("successMessage", "The patient has been saved successfully!");

        }

        else {
            model.addAttribute("errorMessage", "The patient hasn't been saved!");
        }
        return "patients/form-page";
    }
    
}
