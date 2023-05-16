package kg.ash.hospital.entities.patients;

import jakarta.persistence.*;
import jakarta.validation.Valid;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Entity
@Table(name = "patient")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Patient {

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

    @Column(name = "sex", nullable = false)
    private boolean sex = true;

    @NotNull(message = "Birth date is required")
    @Column(name = "birth_date", nullable = false)
    private LocalDate birthDate;

    @Valid
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "address_id", referencedColumnName = "id", nullable = false)
    private Address address;

    @Valid
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "contacts_id", referencedColumnName = "id", nullable = false)
    private Contacts contacts;

    @Valid
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "passport_data_id", referencedColumnName = "id", nullable = false)
    private PassportData passportData;

    @Column(name = "active", nullable = false)
    private boolean active = true;

}
