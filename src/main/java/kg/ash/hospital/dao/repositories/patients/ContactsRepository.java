package kg.ash.hospital.dao.repositories.patients;

import kg.ash.hospital.entities.patients.Contacts;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ContactsRepository extends JpaRepository<Contacts, Integer> {}
