package kg.ash.hospital.entities.patient;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Pattern;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "contacts")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Contacts {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @Pattern(regexp = "\\+\\d\\s\\d{3}\\s\\d{3}\\s\\d{3}", message = "Invalid phone number")
    @Column(name = "phone_number", nullable = false)
    private String phoneNumber;

    @Email(message = "Invalid email address")
    @Column(name = "email")
    private String email;

}
