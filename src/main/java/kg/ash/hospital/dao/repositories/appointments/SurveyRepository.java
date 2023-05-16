package kg.ash.hospital.dao.repositories.appointments;

import kg.ash.hospital.entities.appointments.Survey;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SurveyRepository extends JpaRepository<Survey, Integer> {}
