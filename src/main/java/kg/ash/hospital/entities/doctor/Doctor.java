package kg.ash.hospital.entities.doctor;

import jakarta.persistence.*;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Entity
@Table(name = "doctor")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Doctor {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @NotBlank(message = "Last name is required")
    @Column(name = "last_name", nullable = false)
    private String lastName;

    @NotBlank(message = "First name is required")
    @Column(name = "first_name", nullable = false)
    private String firstName;

    @Column(name = "middle_name")
    private String middleName;

    @NotBlank(message = "Position is required")
    @Column(name = "position", nullable = false)
    private String position;

    @NotBlank(message = "Speciality is required")
    @Column(name = "speciality", nullable = false)
    private String speciality;

    @Column(name = "cabinet_number", nullable = false)
    private int cabinetNumber;

    @OneToMany(mappedBy = "doctor")
    private List<WorkSchedule> workSchedule;

    @OneToMany(mappedBy = "doctor")
    private List<DutySchedule> dutySchedule;

    @Valid
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "account_data", referencedColumnName = "id", nullable = false)
    private AccountData accountData;

}
