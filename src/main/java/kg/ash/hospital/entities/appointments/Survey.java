package kg.ash.hospital.entities.appointments;

import jakarta.persistence.*;

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

    @Column(name = "complaints", nullable = false)
    private String complaints;

    @Column(name = "preliminary_diagnosis", nullable = false)
    private String preliminaryDiagnosis;

    @Column(name = "plan", nullable = false)
    private String plan;

    @Column(name = "result", nullable = false)
    private String result;

    @Column(name = "date", nullable = false)
    private LocalDate date;

}
