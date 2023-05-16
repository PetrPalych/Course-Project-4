package kg.ash.hospital.dao.repositories.doctors;

import kg.ash.hospital.entities.doctors.AccountData;
import kg.ash.hospital.enums.Role;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface AccountDataRepository extends JpaRepository<AccountData, Integer> {

    Optional<AccountData> findByUsername(String username);

    List<AccountData> findByRole(Role role);

}
