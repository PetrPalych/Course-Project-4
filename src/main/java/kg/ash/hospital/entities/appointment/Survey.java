package kg.ash.hospital.entities.appointment;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Entity
@Table(name = "survey")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Survey {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @ManyToOne
    @JoinColumn(name="treatment_id", nullable=false)
    private Treatment treatment;

    @NotBlank(message = "Complaints are required!")
    @Column(name = "complaints", nullable = false)
    private String complaints;

    @NotBlank(message = "Preliminary diagnosis is required!")
    @Column(name = "preliminary_diagnosis", nullable = false)
    private String preliminaryDiagnosis;

    @NotBlank(message = "Plan is required!")
    @Column(name = "plan", nullable = false)
    private String plan;

    @NotBlank(message = "Result is required!")
    @Column(name = "result", nullable = false)
    private String result;

    @NotNull(message = "Date is required!")
    @Column(name = "date", nullable = false)
    private LocalDate date;

}
