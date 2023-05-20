package kg.ash.hospital.controllers;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import kg.ash.hospital.entities.Recipe;
import kg.ash.hospital.entities.appointments.Appointment;
import kg.ash.hospital.entities.patients.Patient;
import kg.ash.hospital.enums.RecipeType;
import kg.ash.hospital.services.interfaces.AppointmentService;

import kg.ash.hospital.services.interfaces.RecipeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class RecipeController {

    private final RecipeService recipeService;

    private final AppointmentService appointmentService;

    @Autowired
    public RecipeController(RecipeService recipeService, AppointmentService appointmentService) {
        this.recipeService = recipeService;
        this.appointmentService = appointmentService;
    }

    @GetMapping("/appointment/recipes")
    public String recipes(@RequestParam("appointmentId") int appointmentId, Model model) {
        model.addAttribute("recipes", appointmentService.find(appointmentId).getRecipes());
        model.addAttribute("appointmentId", appointmentId);

        return "recipes/list-page";
    }

    @GetMapping("/appointment/recipe")
    public String recipe(@RequestParam("recipeId") int recipeId, Model model) {
        model.addAttribute("recipe", recipeService.find(recipeId));

        return "recipes/details-page";
    }

    @GetMapping("/appointment/recipe/delete")
    public String delete(@RequestParam("recipeId") int recipeId, HttpServletRequest httpServletRequest) {
        recipeService.delete(recipeId);

        return "redirect:" + httpServletRequest.getHeader("Referer");
    }

    @GetMapping("/appointment/recipe/update")
    public String update(@RequestParam("recipeId") int recipeId, Model model) {
        model.addAttribute("recipe", recipeService.find(recipeId));
        model.addAttribute("recipeTypes", RecipeType.values());

        return "recipes/form-page";
    }

    @GetMapping("/appointment/recipe/add")
    public String add(@RequestParam("appointmentId") int appointmentId, Model model) {
        Appointment appointment = appointmentService.find(appointmentId);

        Recipe recipe = new Recipe();
        recipe.setAppointment(appointment);
        recipe.setPatient(appointment.getPatient());
        recipe.setDoctor(appointment.getDoctor());

        model.addAttribute("recipe", recipe);
        model.addAttribute("recipeTypes", RecipeType.values());
        return "recipes/form-page";
    }

    @PostMapping("/appointment/recipe/save")
    public String save(@ModelAttribute("recipe") @Valid Recipe recipe, BindingResult bindingResult, Model model) {
        model.addAttribute("recipeTypes", RecipeType.values());

        if (!bindingResult.hasErrors()) {
            recipeService.save(recipe);
            appointmentService.save(recipe.getAppointment());
            model.addAttribute("successMessage", "The patient has been saved successfully!");

        }

        else {
            model.addAttribute("errorMessage", "The patient hasn't been saved!");
        }

        return "recipes/form-page";
    }

}
