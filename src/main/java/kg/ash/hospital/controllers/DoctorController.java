package kg.ash.hospital.controllers;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;

import kg.ash.hospital.entities.doctors.Doctor;
import kg.ash.hospital.services.interfaces.DoctorService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class DoctorController {

    private final DoctorService doctorService;

    @Autowired
    public DoctorController(DoctorService doctorService) {
        this.doctorService = doctorService;
    }

    @GetMapping("/doctors")
    public String doctors(@RequestParam("active") boolean active, Model model) {
        model.addAttribute("doctors", doctorService.findAllOrderByIdDesc(25, active));

        return "doctors/list-page";
    }

    @GetMapping("/doctor")
    public String patient(@RequestParam("id") int id, Model model) {
        model.addAttribute("doctor", doctorService.find(id));

        return "doctors/details-page";
    }

    @PostMapping("/doctor/find")
    public String find(@RequestParam("fullName") String fullName, Model model) {
        model.addAttribute("doctors", doctorService.findByFullName(fullName));

        return "doctors/list-page";
    }

    @GetMapping("/doctor/add")
    public String add(Model model) {
        Doctor doctor = new Doctor();
        model.addAttribute("doctor", doctor);

        return "doctors/form-page";
    }

    @GetMapping("/doctor/update")
    public String update(@RequestParam("id") int id, Model model) {
        model.addAttribute("doctor", doctorService.find(id));

        return "doctors/form-page";
    }

    @GetMapping("/doctor/delete")
    public String delete(@RequestParam("id") int id, HttpServletRequest httpServletRequest) {
        doctorService.delete(id);

        return "redirect:" + httpServletRequest.getHeader("Referer");
    }

    @PostMapping("/doctor/save")
    public String save(@ModelAttribute("doctor") @Valid Doctor doctor, BindingResult bindingResult, Model model) {
        if (!bindingResult.hasErrors()) {
            doctorService.save(doctor);
            model.addAttribute("successMessage", "The doctor has been saved successfully!");

        }

        else {
            model.addAttribute("errorMessage", "The doctor hasn't been saved!");
        }

        return "doctors/form-page";
    }
}
