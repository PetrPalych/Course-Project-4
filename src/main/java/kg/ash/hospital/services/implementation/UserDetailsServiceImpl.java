package kg.ash.hospital.services.implementation;

import kg.ash.hospital.configs.UserDetailsImpl;
import kg.ash.hospital.dao.repositories.doctors.AccountDataRepository;
import kg.ash.hospital.entities.doctors.AccountData;

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
            throw new UsernameNotFoundException("User isn't found");

        return new UserDetailsImpl(accountData.get());
    }

}
