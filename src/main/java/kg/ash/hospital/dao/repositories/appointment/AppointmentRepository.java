package kg.ash.hospital.dao.repositories.appointment;

import kg.ash.hospital.entities.appointment.Appointment;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AppointmentRepository extends JpaRepository<Appointment, Integer> {

    @Query(value = "SELECT * FROM appointment ORDER BY id DESC LIMIT :limit", nativeQuery = true)
    List<Appointment> findAllOrderByIdDesk(@Param("limit") int limit);

    List<Appointment> findByPatientId(int patientId);

    List<Appointment> findByDoctorId(int doctorId);

}
