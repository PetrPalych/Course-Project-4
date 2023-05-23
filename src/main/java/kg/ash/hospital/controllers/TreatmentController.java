package kg.ash.hospital.controllers;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;

import kg.ash.hospital.entities.appointment.Appointment;
import kg.ash.hospital.entities.appointment.Survey;
import kg.ash.hospital.entities.appointment.Treatment;

import kg.ash.hospital.services.interfaces.appointment.AppointmentService;
import kg.ash.hospital.services.interfaces.appointment.SurveyService;
import kg.ash.hospital.services.interfaces.appointment.TreatmentService;
import kg.ash.hospital.services.interfaces.doctor.DoctorService;
import kg.ash.hospital.services.interfaces.patient.PatientService;
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

    private final SurveyService surveyService;

    @Autowired
    public TreatmentController(TreatmentService treatmentService, AppointmentService appointmentService, PatientService patientService, DoctorService doctorService, SurveyService surveyService) {
        this.treatmentService = treatmentService;
        this.appointmentService = appointmentService;
        this.patientService = patientService;
        this.doctorService = doctorService;
        this.surveyService = surveyService;
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

    @GetMapping("/treatment/update")
    public String update(@RequestParam("treatmentId") int treatmentId, @RequestParam("appointmentId") int appointmentId, Model model) {
        model.addAttribute("appointmentId", appointmentId);
        model.addAttribute("treatment", treatmentService.find(treatmentId));

        return "treatments/form-page";
    }

    @GetMapping("/treatment")
    public String treatment(@RequestParam("treatmentId") int treatmentId, @RequestParam("appointmentId") int appointmentId, Model model) {
        model.addAttribute("treatment", treatmentService.find(treatmentId));
        model.addAttribute("appointmentId", appointmentId);

        return "treatments/details-page";
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

    @GetMapping("/treatment/surveys")
    public String surveys(@RequestParam("treatmentId") int treatmentId, Model model) {
        model.addAttribute("treatmentId", treatmentId);
        model.addAttribute("surveys", treatmentService.find(treatmentId).getSurveys());

        return "surveys/list-page";
    }

    @GetMapping("/treatment/survey")
    public String survey(@RequestParam("surveyId") int surveyId, Model model) {
        model.addAttribute("survey", surveyService.find(surveyId));

        return "surveys/details-page";
    }

    @GetMapping("/treatment/survey/add")
    public String addSurvey(@RequestParam("treatmentId") int treatmentId, Model model) {
        Survey survey = new Survey();
        survey.setTreatment(treatmentService.find(treatmentId));

        model.addAttribute("survey", survey);

        return "surveys/form-page";
    }

    @GetMapping("/treatment/survey/update")
    public String updateSurvey(@RequestParam("surveyId") int surveyId, Model model) {
        model.addAttribute("survey", surveyService.find(surveyId));

        return "surveys/form-page";
    }

    @GetMapping("/treatment/survey/delete")
    public String deleteSurvey(@RequestParam("surveyId") int surveyId, HttpServletRequest httpServletRequest) {
        surveyService.delete(surveyId);

        return "redirect:" + httpServletRequest.getHeader("Referer");
    }

    @PostMapping("/treatment/survey/save")
    public String save(@ModelAttribute("survey") @Valid Survey survey, BindingResult bindingResult, Model model) {
        if (!bindingResult.hasErrors()) {
            surveyService.save(survey);
            treatmentService.save(survey.getTreatment());
            model.addAttribute("successMessage", "The patient has been saved successfully!");

        }

        else {
            model.addAttribute("errorMessage", "The patient hasn't been saved!");
        }

        return "surveys/form-page";
    }
}
