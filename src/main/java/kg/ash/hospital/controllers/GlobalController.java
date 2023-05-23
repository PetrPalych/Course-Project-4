package kg.ash.hospital.controllers;

import kg.ash.hospital.enums.Role;
import kg.ash.hospital.services.interfaces.AdminService;
import kg.ash.hospital.services.interfaces.AuthService;
import kg.ash.hospital.services.interfaces.doctor.DoctorService;
import kg.ash.hospital.services.interfaces.patient.PatientService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class GlobalController {

    private final AuthService authService;

    private final AdminService adminService;

    private final PatientService patientService;

    private final DoctorService doctorService;

    @Autowired
    public GlobalController(AuthService authService,
                            AdminService adminService,

                            PatientService patientService,
                            DoctorService doctorService) {

        this.authService = authService;
        this.adminService = adminService;

        this.patientService = patientService;
        this.doctorService = doctorService;
    }

    @GetMapping("/login")
    public String login() {
        return "login-page";
    }

    @GetMapping("/access-denied")
    public String accessDenied() {
        return "errors/access-denied-page";
    }

    @GetMapping("/")
    public String index(Model model) {
        model
                .addAttribute("username",
                        authService.getCurrentUser().getUsername())

                .addAttribute("role",
                        authService.getCurrentUser().getRole().name().replace("ROLE_", ""))

                .addAttribute("countAllAdmins",
                        adminService.countAllAccountData(Role.ROLE_ADMIN))

                .addAttribute("countAllDoctors",
                        adminService.countAllAccountData(Role.ROLE_DOCTOR))

                .addAttribute("countAllRegistrars",
                        adminService.countAllAccountData(Role.ROLE_REGISTRAR))

                .addAttribute("countAllPatients",
                        patientService.findAll().size())

                .addAttribute("patients",
                        patientService.findAllOrderByIdDesc(10, true))

                .addAttribute("doctors",
                        doctorService.findAllOrderByIdDesc(10, true));

        return "index";
    }

}
