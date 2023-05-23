package kg.ash.hospital.services.impl.doctor;

import kg.ash.hospital.dao.repositories.doctor.DoctorRepository;
import kg.ash.hospital.dao.repositories.doctor.DutyScheduleRepository;
import kg.ash.hospital.dao.repositories.doctor.WorkScheduleRepository;
import kg.ash.hospital.entities.doctor.Doctor;
import kg.ash.hospital.errors.exceptions.NotFoundException;
import kg.ash.hospital.services.interfaces.doctor.DoctorService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Service
public class DoctorServiceImpl implements DoctorService {

    private final DoctorRepository doctorRepository;

    private final PasswordEncoder passwordEncoder;

    @Autowired
    public DoctorServiceImpl(DoctorRepository doctorRepository, WorkScheduleRepository workScheduleRepository, DutyScheduleRepository dutyScheduleRepository, PasswordEncoder passwordEncoder) {
        this.doctorRepository = doctorRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public Doctor save(Doctor doctor) {
        String password = doctor.getAccountData().getPassword();
        doctor.getAccountData().setPassword(passwordEncoder.encode(password));

        return doctorRepository.save(doctor);
    }

    @Override
    public Doctor find(int id) {
        Optional<Doctor> optionalDoctor = doctorRepository.findById(id);

        if (optionalDoctor.isPresent()) {
            return optionalDoctor.get();
        }

        else {
            throw new NotFoundException("Doctor is not found! ID: " + id);
        }
    }

    @Override
    public List<Doctor> findByFullName(String fullName) {
        List<String> components = Arrays.stream(
                fullName.split(" ")).map(String::trim).collect(Collectors.toList());

        Stream.of("", "", "").forEach(components::add);

        return doctorRepository.findByFullName(components.get(0), components.get(1), components.get(2));
    }

    @Override
    public List<Doctor> findAll() {
        return doctorRepository.findAll();
    }

    @Override
    public void delete(int id) {
        doctorRepository.deleteById(id);
    }

    @Override
    public List<Doctor> findAllOrderByIdDesc(int limit, boolean active) {
        return doctorRepository.findAllOrderByIdDesk(limit, active);
    }

}
