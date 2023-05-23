package kg.ash.hospital.dao.repositories.appointment;

import kg.ash.hospital.entities.appointment.Survey;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SurveyRepository extends JpaRepository<Survey, Integer> {}
