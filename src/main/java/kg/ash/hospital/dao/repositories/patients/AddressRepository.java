package kg.ash.hospital.dao.repositories.patients;

import kg.ash.hospital.entities.patients.Address;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AddressRepository extends JpaRepository<Address, Integer> {}
