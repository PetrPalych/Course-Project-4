package kg.ash.hospital.dao.repositories.patients;

import kg.ash.hospital.entities.patients.Patient;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PatientRepository extends JpaRepository<Patient, Integer> {

    @Query(value = "SELECT * FROM patient WHERE active = :active ORDER BY id DESC LIMIT :limit",
            nativeQuery = true)
    List<Patient> findAllOrderByIdDesk(@Param("limit") int limit, @Param("active") boolean active);

    @Query(value =
            "SELECT * FROM patient " +
            "WHERE last_name LIKE %:lastName% " +
            "AND first_name LIKE %:firstName% " +
            "AND middle_name LIKE %:middleName%",

            nativeQuery = true)
    List<Patient> findByFullName(@Param("lastName") String lastName,
                                 @Param("firstName") String firstName,
                                 @Param("middleName") String middleName);

}
