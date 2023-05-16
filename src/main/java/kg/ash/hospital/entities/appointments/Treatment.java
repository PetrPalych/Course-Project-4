package kg.ash.hospital.entities.appointments;

import jakarta.persistence.*;

import kg.ash.hospital.entities.doctors.Doctor;
import kg.ash.hospital.entities.patients.Patient;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.util.List;

@Entity
@Table(name = "treatment")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Treatment {

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

    @OneToMany(mappedBy = "treatment")
    private List<Survey> surveys;

    @Column(name = "treatment_start_date", nullable = false)
    private LocalDate treatmentStartDate;

    @Column(name = "clinical_diagnosis", nullable = false)
    private String clinicalDiagnosis;

    @Column(name = "plan", nullable = false)
    private String plan;

    @Column(name = "recommendations", nullable = false)
    private String recommendations;

    @Column(name = "progress", nullable = false)
    private String progress;

    @Column(name = "result", nullable = false)
    private String result;

    @Column(name = "treatment_end_date", nullable = false)
    private LocalDate treatmentEndDate;

}
