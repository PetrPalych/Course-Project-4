package kg.ash.hospital.entities.appointments;

import jakarta.persistence.*;

import kg.ash.hospital.entities.Recipe;
import kg.ash.hospital.entities.doctors.Doctor;
import kg.ash.hospital.entities.patients.Patient;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
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

    @OneToOne
    @JoinColumn(name = "patient_id", referencedColumnName = "id", nullable = false)
    private Patient patient;

    @OneToOne
    @JoinColumn(name = "doctor_id", referencedColumnName = "id", nullable = false)
    private Doctor doctor;

    @Column(name = "date", nullable = false)
    private LocalDateTime dateTime = LocalDateTime.now();

    @OneToOne
    @JoinColumn(name = "treatment_id", referencedColumnName = "id")
    private Treatment treatment;

    @OneToMany(mappedBy="appointment")
    private List<Recipe> recipes;

}
