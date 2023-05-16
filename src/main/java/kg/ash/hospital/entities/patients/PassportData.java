package kg.ash.hospital.entities.patients;

import jakarta.persistence.*;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "passport_data")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class PassportData {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @NotBlank(message = "Document ID is required")
    @Column(name = "document_id", nullable = false, unique = true)
    private String documentId;

    @NotBlank(message = "Personal number is required")
    @Column(name = "personal_number", nullable = false, unique = true)
    private String personalNumber;

}
