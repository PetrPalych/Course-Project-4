package kg.ash.hospital.entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import kg.ash.hospital.entities.appointment.Appointment;
import kg.ash.hospital.entities.doctor.Doctor;
import kg.ash.hospital.entities.patient.Patient;
import kg.ash.hospital.enums.RecipeType;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Entity
@Table(name = "recipe")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Recipe {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @ManyToOne
    @JoinColumn(name="appointment_id", nullable=false)
    private Appointment appointment;

    @OneToOne
    @JoinColumn(name = "patient_id", referencedColumnName = "id", nullable = false)
    private Patient patient;

    @OneToOne
    @JoinColumn(name = "doctor_id", referencedColumnName = "id", nullable = false)
    private Doctor doctor;

    @NotBlank(message = "Drug name is required!")
    @Column(name = "drug_name", nullable = false)
    private String drugName;

    @NotNull(message = "Recipe type is required!")
    @Enumerated(EnumType.STRING)
    @Column(name = "recipe_type", nullable = false)
    private RecipeType recipeType;

    @NotBlank(message = "Rules of medication are required!")
    @Column(name = "rules_of_medication", nullable = false)
    private String rulesOfMedication;

    @NotNull(message = "Date is required!")
    @Column(name = "date", nullable = false)
    private LocalDate date = LocalDate.now();

}
