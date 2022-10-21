package com.example.medrest.service;

import com.example.medrest.entity.Patient;
import com.example.medrest.entity.SickLeave;
import com.example.medrest.repository.PatientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PatientService {

    private final PatientRepository repository;

    @Autowired
    public PatientService(PatientRepository repository) {
        this.repository = repository;
    }

    public Patient getOne(long id) {
        return repository.getOne(id);
    }

    public List<SickLeave> getAllSickLeaves(String idNum){
        return repository.getAllSickLeaves(idNum);
    }
}
