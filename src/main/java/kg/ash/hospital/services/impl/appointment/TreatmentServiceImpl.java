package kg.ash.hospital.services.impl.appointment;

import kg.ash.hospital.dao.repositories.appointment.TreatmentRepository;
import kg.ash.hospital.entities.appointment.Treatment;
import kg.ash.hospital.errors.exceptions.NotFoundException;
import kg.ash.hospital.services.interfaces.appointment.TreatmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TreatmentServiceImpl implements TreatmentService {

    private final TreatmentRepository treatmentRepository;

    @Autowired
    public TreatmentServiceImpl(TreatmentRepository treatmentRepository) {
        this.treatmentRepository = treatmentRepository;
    }

    @Override
    public Treatment save(Treatment treatment) {
        return treatmentRepository.save(treatment);
    }

    @Override
    public Treatment find(int id) {
        Optional<Treatment> optionalTreatment = treatmentRepository.findById(id);

        if (optionalTreatment.isPresent()) {
            return optionalTreatment.get();
        }

        else {
            throw new NotFoundException("Treatment is not found! ID: " + id);
        }
    }

    @Override
    public List<Treatment> findAll() {
        return treatmentRepository.findAll();
    }

    @Override
    public void delete(int id) {
        treatmentRepository.deleteById(id);
    }

    @Override
    public List<Treatment> findByPatientId(int patientId) {
        return treatmentRepository.findByPatientId(patientId);
    }

}
