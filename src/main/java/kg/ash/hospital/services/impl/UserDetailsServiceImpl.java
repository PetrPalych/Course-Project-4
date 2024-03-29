package kg.ash.hospital.services.impl;

import kg.ash.hospital.configs.security.UserDetailsImpl;
import kg.ash.hospital.dao.repositories.doctor.AccountDataRepository;
import kg.ash.hospital.entities.doctor.AccountData;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    private final AccountDataRepository accountDataRepository;

    @Autowired
    public UserDetailsServiceImpl(AccountDataRepository accountDataRepository) {
        this.accountDataRepository = accountDataRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<AccountData> accountData = accountDataRepository.findByUsername(username);

        if (accountData.isEmpty())
            throw new UsernameNotFoundException("User is not found");

        return new UserDetailsImpl(accountData.get());
    }

}
