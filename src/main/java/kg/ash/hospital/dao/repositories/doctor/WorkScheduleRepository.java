package kg.ash.hospital.dao.repositories.doctor;

import kg.ash.hospital.entities.doctor.WorkSchedule;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface WorkScheduleRepository extends JpaRepository<WorkSchedule, Integer> {

    List<WorkSchedule> findByDoctorId(int doctorId);

}
