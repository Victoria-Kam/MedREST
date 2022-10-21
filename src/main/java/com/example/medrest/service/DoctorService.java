package com.example.medrest.service;

import com.example.medrest.entity.Doctor;
import com.example.medrest.entity.Patient;
import com.example.medrest.entity.SickLeave;
import com.example.medrest.repository.DoctorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@Service
public class DoctorService {

    public final DoctorRepository repository;

    @Autowired
    public DoctorService(DoctorRepository repository) {
        this.repository = repository;
    }


    public Doctor getOne(long id) {
        return repository.getOne(id);
    }


    public List<SickLeave> findOpenSickLeave(long id) {
        return repository.findOpenSickLeave(id);
    }

    public List<SickLeave> findCloseSickLeave(long id) {
        return repository.findCloseSickLeave(id);
    }

    public List<Patient> findByNumber(String idNum) {
        return repository.findByNumber(idNum);
    }

    public List<SickLeave> findPatientSickLeaves(@PathVariable String idNum) {
        return repository.findPatientSickLeaves(idNum);
    }

    public boolean changeTreatment(long id, SickLeave sickLeave) {
        return (repository.changeTreatment(id, sickLeave)) ? true : false;
    }

    public boolean closeSickLeave(long id, SickLeave sickLeave) {
        return (repository.closeSickLeave(id, sickLeave))?true:false;
    }

    public boolean openSickLeave(long idNum, long idDoc, SickLeave sickLeave) {
        return (repository.openSickLeave(idNum, idDoc, sickLeave)) ? true : false;

    }
}
