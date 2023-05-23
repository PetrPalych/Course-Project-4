package kg.ash.hospital.dao.repositories.patient;

import kg.ash.hospital.entities.patient.Address;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AddressRepository extends JpaRepository<Address, Integer> {}
