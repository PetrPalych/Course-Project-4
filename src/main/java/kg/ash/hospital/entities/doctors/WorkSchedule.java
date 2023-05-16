package kg.ash.hospital.entities.doctors;

import jakarta.persistence.*;

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

    @Enumerated(EnumType.STRING)
    @Column(name = "day", nullable = false)
    private Day day;

    @Column(name = "work_start", nullable = false)
    private LocalTime workStart;

    @Column(name = "work_end", nullable = false)
    private LocalTime workEnd;

    @Column(name = "lunch_start", nullable = false)
    private LocalTime lunchStart;

    @Column(name = "lunch_end", nullable = false)
    private LocalTime lunchEnd;

}
