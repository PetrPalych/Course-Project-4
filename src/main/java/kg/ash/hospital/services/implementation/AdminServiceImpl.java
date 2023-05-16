package kg.ash.hospital.services.implementation;

import kg.ash.hospital.dao.repositories.doctors.AccountDataRepository;
import kg.ash.hospital.enums.Role;
import kg.ash.hospital.services.interfaces.AdminService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AdminServiceImpl implements AdminService {

    private final AccountDataRepository accountDataRepository;

    @Autowired
    public AdminServiceImpl(AccountDataRepository accountDataRepository) {
        this.accountDataRepository = accountDataRepository;
    }

    @Override
    public int countAllAccountData(Role role) {
        return accountDataRepository.findByRole(role).size();
    }

}
