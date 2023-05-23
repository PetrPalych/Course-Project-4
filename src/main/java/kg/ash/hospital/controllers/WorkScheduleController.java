package kg.ash.hospital.controllers;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;

import kg.ash.hospital.entities.doctor.WorkSchedule;
import kg.ash.hospital.enums.Day;
import kg.ash.hospital.services.interfaces.doctor.WorkScheduleService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class WorkScheduleController {

    private final WorkScheduleService workScheduleService;

    @Autowired
    public WorkScheduleController(WorkScheduleService workScheduleService) {
        this.workScheduleService = workScheduleService;
    }

    @GetMapping("/doctor/work-schedules")
    public String workSchedules(@RequestParam("id") int id, Model model) {
        model.addAttribute("doctorId", id);
        model.addAttribute("workSchedule", workScheduleService.findWorkScheduleByDoctorId(id));

        return "schedules/work-schedule-list-page";
    }

    @GetMapping("/doctor/work-schedule/add")
    public String add(@RequestParam("id") int id, Model model) {
        WorkSchedule workSchedule = new WorkSchedule();
        workSchedule.setDoctor(workScheduleService.findDoctorById(id));

        model.addAttribute("workSchedule", workSchedule);
        model.addAttribute("days", Day.values());

        return "schedules/work-schedule-form-page";
    }

    @GetMapping("/doctor/work-schedule/update")
    public String update(@RequestParam("id") int id, Model model) {
        model.addAttribute("workSchedule", workScheduleService.find(id));

        return "schedules/work-schedule-form-page";
    }

    @GetMapping("/doctor/work-schedule/delete")
    public String delete(@RequestParam("id") int id, HttpServletRequest httpServletRequest) {
        workScheduleService.delete(id);

        return "redirect:" + httpServletRequest.getHeader("Referer");
    }

    @PostMapping("/doctor/work-schedule/save")
    public String save(@ModelAttribute("workSchedule") @Valid WorkSchedule workSchedule,
                       BindingResult bindingResult,
                       Model model) {

        if (!bindingResult.hasErrors()) {
            workScheduleService.save(workSchedule);
            model.addAttribute("successMessage",
                    "The work schedule has been saved successfully!");

        }

        else {
            model.addAttribute("errorMessage",
                    "The work schedule hasn't been saved!");
        }

        return "schedules/work-schedule-form-page";
    }

}
