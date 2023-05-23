package kg.ash.hospital.entities.appointment;

import jakarta.persistence.*;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;

import jakarta.validation.constraints.NotNull;
import kg.ash.hospital.entities.doctor.Doctor;
import kg.ash.hospital.entities.patient.Patient;

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

    @Valid
    @OneToOne
    @JoinColumn(name = "patient_id", referencedColumnName = "id", nullable = false)
    private Patient patient;

    @Valid
    @OneToOne
    @JoinColumn(name = "doctor_id", referencedColumnName = "id", nullable = false)
    private Doctor doctor;

    @Valid
    @OneToMany(mappedBy = "treatment", cascade = CascadeType.ALL)
    private List<Survey> surveys;

    @NotNull(message = "Start date is required!")
    @Column(name = "treatment_start_date", nullable = false)
    private LocalDate treatmentStartDate;

    @NotBlank(message = "Clinical diagnosis is required!")
    @Column(name = "clinical_diagnosis", nullable = false)
    private String clinicalDiagnosis;

    @NotBlank(message = "Plan is required!")
    @Column(name = "plan", nullable = false)
    private String plan;

    @NotBlank(message = "Recommendations are required!")
    @Column(name = "recommendations", nullable = false)
    private String recommendations;

    @NotBlank(message = "Progress is required!")
    @Column(name = "progress", nullable = false)
    private String progress;

    @NotBlank(message = "Result is required!")
    @Column(name = "result", nullable = false)
    private String result;

    @NotNull(message = "End date is required!")
    @Column(name = "treatment_end_date", nullable = false)
    private LocalDate treatmentEndDate;

}
