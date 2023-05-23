package kg.ash.hospital.entities.patient;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "address")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Address {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @NotBlank(message = "City is required")
    @Column(name = "city", nullable = false)
    private String city;

    @NotBlank(message = "District is required")
    @Column(name = "district", nullable = false)
    private String district;

    @NotBlank(message = "Street is required")
    @Column(name = "street", nullable = false)
    private String street;

}
