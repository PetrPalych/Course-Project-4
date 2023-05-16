package kg.ash.hospital.services.implementation;

import kg.ash.hospital.dao.repositories.patients.PatientRepository;
import kg.ash.hospital.entities.patients.Patient;
import kg.ash.hospital.errors.exceptions.PatientNotFoundException;
import kg.ash.hospital.services.interfaces.PatientService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Service
public class PatientServiceImpl implements PatientService {

    private final PatientRepository patientRepository;

    @Autowired
    public PatientServiceImpl(PatientRepository patientRepository) {
        this.patientRepository = patientRepository;
    }

    @Override
    public Patient save(Patient patient) {
        return patientRepository.save(patient);
    }

    @Override
    public Patient find(int id) {
        Optional<Patient> optionalPatient = patientRepository.findById(id);

        if (optionalPatient.isPresent()) {
            return optionalPatient.get();
        }

        else {
            throw new PatientNotFoundException("Patient isn't found. ID: " + id);
        }
    }

    @Override
    public List<Patient> findByFullName(String fullName) {
        List<String> components = Arrays.stream(
                fullName.split(" ")).map(String::trim).collect(Collectors.toList());

        Stream.of("", "", "").forEach(components::add);

        return patientRepository.findByFullName(components.get(0), components.get(1), components.get(2));
    }

    @Override
    public List<Patient> findAll() {
        return patientRepository.findAll();
    }

    @Override
    public void delete(int id) {
        patientRepository.deleteById(id);
    }

    @Override
    public List<Patient> findAllOrderByIdDesc(int limit, boolean active) {
        return patientRepository.findAllOrderByIdDesk(limit, active);
    }

}
