package com.forsakenecho.patientservice.service;

import com.forsakenecho.patientservice.dto.PatientRequestDTO;
import com.forsakenecho.patientservice.dto.PatientResponseDTO;
import com.forsakenecho.patientservice.mapper.PatientMapper;
import com.forsakenecho.patientservice.model.Patient;
import com.forsakenecho.patientservice.repository.PatientRepository;
import org.springframework.stereotype.Service;


import java.util.List;

@Service
public class PatientService {
    private PatientRepository patientRepository;

    public PatientService(PatientRepository patientRepository) {
        this.patientRepository = patientRepository;
    }

    public List<PatientResponseDTO> getPatients(){
        List<Patient> patients = patientRepository.findAll();

        return patients.stream()
                .map(PatientMapper::toDTO).toList();
    }

    public PatientResponseDTO createPatient(PatientRequestDTO patientRequestDTO){
        Patient newPatient = patientRepository.save(PatientMapper.toModel(patientRequestDTO));
        return PatientMapper.toDTO(newPatient);
    }
}
