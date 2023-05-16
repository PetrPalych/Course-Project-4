package kg.ash.hospital.dao.repositories.doctors;

import kg.ash.hospital.entities.doctors.WorkSchedule;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface WorkScheduleRepository extends JpaRepository<WorkSchedule, Integer> {

    @Query(value = "SELECT * FROM work_schedule WHERE doctor_id = :doctor_id", nativeQuery = true)
    List<WorkSchedule> findByDoctorId(@Param("doctor_id") int doctorId);

}
