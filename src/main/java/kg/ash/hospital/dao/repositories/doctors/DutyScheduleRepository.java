package kg.ash.hospital.dao.repositories.doctors;

import kg.ash.hospital.entities.doctors.DutySchedule;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DutyScheduleRepository extends JpaRepository<DutySchedule, Integer> {

    @Query(value = "SELECT * FROM duty_schedule WHERE doctor_id = :doctor_id", nativeQuery = true)
    List<DutySchedule> findByDoctorId(@Param("doctor_id") int doctorId);

}
