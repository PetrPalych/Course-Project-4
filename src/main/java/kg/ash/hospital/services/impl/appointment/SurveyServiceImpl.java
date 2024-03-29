package kg.ash.hospital.services.impl.appointment;

import kg.ash.hospital.dao.repositories.appointment.SurveyRepository;
import kg.ash.hospital.entities.appointment.Survey;
import kg.ash.hospital.errors.exceptions.NotFoundException;
import kg.ash.hospital.services.interfaces.appointment.SurveyService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class SurveyServiceImpl implements SurveyService {

    private final SurveyRepository surveyRepository;

    @Autowired
    public SurveyServiceImpl(SurveyRepository surveyRepository) {
        this.surveyRepository = surveyRepository;
    }

    @Override
    public Survey save(Survey survey) {
        return surveyRepository.save(survey);
    }

    @Override
    public Survey find(int id) {
        Optional<Survey> optionalSurvey = surveyRepository.findById(id);

        if (optionalSurvey.isPresent()) {
            return optionalSurvey.get();
        }

        else {
            throw new NotFoundException("Survey is not found! ID: " + id);
        }
    }

    @Override
    public List<Survey> findAll() {
        return surveyRepository.findAll();
    }

    @Override
    public void delete(int id) {
        surveyRepository.deleteById(id);
    }

}
