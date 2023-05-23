package kg.ash.hospital.entities.doctor;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;

import kg.ash.hospital.enums.Day;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalTime;

@Entity
@Table(name = "work_schedule")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class WorkSchedule {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @ManyToOne
    @JoinColumn(name="doctor_id")
    private Doctor doctor;

    @NotBlank(message = "Day is required!")
    @Enumerated(EnumType.STRING)
    @Column(name = "day", nullable = false)
    private Day day;

    @NotBlank(message = "Work start is required!")
    @Column(name = "work_start", nullable = false)
    private LocalTime workStart;

    @NotBlank(message = "Work end is required!")
    @Column(name = "work_end", nullable = false)
    private LocalTime workEnd;

    @NotBlank(message = "Lunch start is required!")
    @Column(name = "lunch_start", nullable = false)
    private LocalTime lunchStart;

    @NotBlank(message = "Lunch end is required!")
    @Column(name = "lunch_end", nullable = false)
    private LocalTime lunchEnd;

}
