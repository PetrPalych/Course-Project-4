package kg.ash.hospital.dao.repositories.patient;

import kg.ash.hospital.entities.patient.Contacts;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ContactsRepository extends JpaRepository<Contacts, Integer> {}
