package kg.ash.hospital.entities.doctor;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;

import jakarta.validation.constraints.NotNull;
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

    @NotNull(message = "Day is required!")
    @Enumerated(EnumType.STRING)
    @Column(name = "day", nullable = false)
    private Day day;

    @NotNull(message = "Work start is required!")
    @Column(name = "work_start", nullable = false)
    private LocalTime workStart;

    @NotNull(message = "Work end is required!")
    @Column(name = "work_end", nullable = false)
    private LocalTime workEnd;

    @NotNull(message = "Lunch start is required!")
    @Column(name = "lunch_start", nullable = false)
    private LocalTime lunchStart;

    @NotNull(message = "Lunch end is required!")
    @Column(name = "lunch_end", nullable = false)
    private LocalTime lunchEnd;

}
