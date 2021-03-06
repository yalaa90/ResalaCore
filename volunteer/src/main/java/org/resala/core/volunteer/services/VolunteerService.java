package org.resala.core.volunteer.services;

import org.resala.core.volunteer.entities.VolunteerEntity;
import org.resala.core.volunteer.repository.VolunteerRepository;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class VolunteerService {

    private final VolunteerRepository volunteerRepository;

    VolunteerService(final VolunteerRepository xVolunteerRepository) {
        volunteerRepository = xVolunteerRepository;
    }

    public VolunteerEntity saveData(final VolunteerEntity volunteerEntity) {
        volunteerRepository.save(volunteerEntity);
        return volunteerEntity;
    }

    public VolunteerEntity find(Long id) {
        return volunteerRepository.findById(id).orElse(null);
    }

    public List<VolunteerEntity> getAllVolunteers() {
        return volunteerRepository.findAll();
    }


    public List<VolunteerEntity> GetByName(String name) {

        return volunteerRepository.findByName(name.toLowerCase());
    }

    public VolunteerEntity getVolunteerById(final Long id) {
        return volunteerRepository.findById(id).orElse(null);
    }

    public Boolean isVolunteerExist(String identificationNumber, String phone) {

        return volunteerRepository.findByIdOrPhone(identificationNumber, phone) != null ? true : false;
    }

    public String findMaxCode(String firstSegment) {
        Long code = volunteerRepository.findMaxCode(firstSegment);
        return code == null ? "1" : code.toString();
    }

    public VolunteerEntity findByCode(final String code) {
        return volunteerRepository.findByCode(code);
    }

    public VolunteerEntity findByPhoneNumber(final String phoneNumber) {
        return volunteerRepository.findByPhoneNumber(phoneNumber);
    }

    public boolean isVolunteerExist(final String phoneNumber, final String idNumber, final String email) {
        Example<VolunteerEntity> modelMatcher = getVolunteerEntityExample(phoneNumber, email);

        return volunteerRepository.exists(modelMatcher);
    }

    public VolunteerEntity findAny(final String phoneNumber, final String idNumber, final String email) {
        Example<VolunteerEntity> modelMatcher = getVolunteerEntityExample(phoneNumber, email);
        List<VolunteerEntity> list = volunteerRepository.findAll(modelMatcher);
        return list.isEmpty() ? null : list.get(0);
    }

    private Example<VolunteerEntity> getVolunteerEntityExample(String phoneNumber, String email) {
        VolunteerEntity entity = new VolunteerEntity();
        entity.setPhoneNumber(phoneNumber);
        entity.setEMail(email);
        return Example.of(entity, ExampleMatcher.matchingAny());
    }

}
