package kg.ash.hospital.controllers;

import jakarta.servlet.http.HttpServletRequest;

import jakarta.validation.Valid;
import kg.ash.hospital.entities.doctors.DutySchedule;
import kg.ash.hospital.enums.Day;
import kg.ash.hospital.services.interfaces.DutyScheduleService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class DutyScheduleController {

    private final DutyScheduleService dutyScheduleService;

    @Autowired
    public DutyScheduleController(DutyScheduleService dutyScheduleService) {
        this.dutyScheduleService = dutyScheduleService;
    }

    @GetMapping("/doctor/duty-schedules")
    public String workSchedules(@RequestParam("id") int id, Model model) {
        model.addAttribute("doctorId", id);
        model.addAttribute("dutySchedule", dutyScheduleService.findDutyScheduleByDoctorId(id));

        return "schedules/duty-schedule-list-page";
    }

    @GetMapping("/doctor/duty-schedule/add")
    public String add(@RequestParam("id") int id, Model model) {
        DutySchedule dutySchedule = new DutySchedule();
        dutySchedule.setDoctor(dutyScheduleService.findDoctorById(id));

        model.addAttribute("dutySchedule", dutySchedule);
        model.addAttribute("days", Day.values());

        return "schedules/duty-schedule-form-page";
    }

    @GetMapping("/doctor/duty-schedule/update")
    public String update(@RequestParam("id") int id, Model model) {
        model.addAttribute("dutySchedule", dutyScheduleService.find(id));

        return "schedules/duty-schedule-form-page";
    }

    @GetMapping("/doctor/duty-schedule/delete")
    public String delete(@RequestParam("id") int id, HttpServletRequest httpServletRequest) {
        dutyScheduleService.delete(id);

        return "redirect:" + httpServletRequest.getHeader("Referer");
    }

    @PostMapping("/doctor/duty-schedule/save")
    public String save(@ModelAttribute("dutySchedule") @Valid DutySchedule dutySchedule,
                       BindingResult bindingResult,
                       Model model) {

        if (!bindingResult.hasErrors()) {
            dutyScheduleService.save(dutySchedule);
            model.addAttribute("successMessage",
                    "The duty schedule has been saved successfully!");

        }

        else {
            model.addAttribute("errorMessage",
                    "The duty schedule hasn't been saved!");
        }

        return "schedules/duty-schedule-form-page";
    }
}
