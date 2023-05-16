package kg.ash.hospital.dao.repositories.doctors;

import kg.ash.hospital.entities.doctors.Doctor;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DoctorRepository extends JpaRepository<Doctor, Integer> {

    @Query(value =
            "SELECT d.* FROM doctor d " +
            "JOIN account_data ad ON d.account_data = ad.id " +
            "WHERE ad.active = :active " +
            "ORDER BY id DESC " +
            "LIMIT :limit",

            nativeQuery = true)
    List<Doctor> findAllOrderByIdDesk(@Param("limit") int limit, @Param("active") boolean active);

    @Query(value =
            "SELECT * FROM doctor " +
            "WHERE last_name LIKE %:lastName% " +
            "AND first_name LIKE %:firstName% " +
            "AND middle_name LIKE %:middleName%",

            nativeQuery = true)
    List<Doctor> findByFullName(@Param("lastName") String lastName,
                                 @Param("firstName") String firstName,
                                 @Param("middleName") String middleName);

}
