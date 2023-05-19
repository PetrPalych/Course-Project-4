package kg.ash.hospital.services.impl;

import kg.ash.hospital.dao.repositories.doctors.AccountDataRepository;
import kg.ash.hospital.entities.doctors.AccountData;
import kg.ash.hospital.services.interfaces.AuthService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AuthServiceImpl implements AuthService {

    private final AccountDataRepository accountDataRepository;

    @Autowired
    public AuthServiceImpl(AccountDataRepository accountDataRepository) {
        this.accountDataRepository = accountDataRepository;
    }

    @Override
    public AccountData getCurrentUser() {
        Optional<AccountData> optionalAccountData =
                accountDataRepository.findByUsername(
                        SecurityContextHolder.getContext().getAuthentication().getName());

        if (optionalAccountData.isPresent()) {
            return optionalAccountData.get();
        }

        else {
            throw new RuntimeException("Not found"); // TODO
        }
    }

}
