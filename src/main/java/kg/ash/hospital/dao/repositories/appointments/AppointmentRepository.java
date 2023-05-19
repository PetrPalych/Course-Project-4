package kg.ash.hospital.dao.repositories.appointments;

import kg.ash.hospital.entities.appointments.Appointment;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AppointmentRepository extends JpaRepository<Appointment, Integer> {

    @Query(value = "SELECT * FROM appointment ORDER BY id DESC LIMIT :limit", nativeQuery = true)
    List<Appointment> findAllOrderByIdDesk(@Param("limit") int limit);

}
