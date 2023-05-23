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
@Table(name = "duty_schedule")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class DutySchedule {

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

    @NotNull(message = "Duty start is required!")
    @Column(name = "duty_start", nullable = false)
    private LocalTime dutyStart;

    @NotNull(message = "Duty end is required!")
    @Column(name = "duty_end", nullable = false)
    private LocalTime dutyEnd;

}
