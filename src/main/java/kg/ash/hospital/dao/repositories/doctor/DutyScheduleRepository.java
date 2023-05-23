package kg.ash.hospital.dao.repositories.doctor;

import kg.ash.hospital.entities.doctor.DutySchedule;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DutyScheduleRepository extends JpaRepository<DutySchedule, Integer> {

    List<DutySchedule> findByDoctorId(int doctorId);

}
