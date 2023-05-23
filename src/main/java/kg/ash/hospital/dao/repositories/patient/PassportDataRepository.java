package kg.ash.hospital.dao.repositories.patient;

import kg.ash.hospital.entities.patient.PassportData;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PassportDataRepository extends JpaRepository<PassportData, Integer> {}
