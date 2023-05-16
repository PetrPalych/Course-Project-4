package kg.ash.hospital.dao.repositories.doctors;

import kg.ash.hospital.entities.doctors.DutySchedule;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DutyScheduleRepository extends JpaRepository<DutySchedule, Integer> {}
