package kg.ash.hospital.entities.appointment;

import jakarta.persistence.*;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;

import kg.ash.hospital.entities.Recipe;
import kg.ash.hospital.entities.doctor.Doctor;
import kg.ash.hospital.entities.patient.Patient;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "appointment")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Appointment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @Valid
    @OneToOne
    @JoinColumn(name = "patient_id", referencedColumnName = "id", nullable = false)
    private Patient patient;

    @Valid
    @OneToOne
    @JoinColumn(name = "doctor_id", referencedColumnName = "id", nullable = false)
    private Doctor doctor;

    @NotNull(message = "Datetime is required!")
    @Column(name = "date", nullable = false)
    private LocalDateTime dateTime = LocalDateTime.now();

    @Valid
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "treatment_id", referencedColumnName = "id")
    private Treatment treatment;

    @Valid
    @OneToMany(mappedBy="appointment", cascade = CascadeType.ALL)
    private List<Recipe> recipes;

}
