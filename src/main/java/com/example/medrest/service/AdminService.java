package com.example.medrest.service;

import com.example.medrest.entity.Doctor;
import com.example.medrest.entity.Patient;
import com.example.medrest.repository.AdminRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AdminService {

    private final AdminRepository repository;

    @Autowired
    public AdminService(AdminRepository repository) {
        this.repository = repository;
    }

    public boolean addNewDoctor(Doctor doctor) {
        return (repository.addNewDoctor(doctor)) ? true : false;
    }

    public boolean addNewPatient(Patient patient) {
        return (repository.addNewPatient(patient)) ? true : false;
    }

    public boolean deleteDoctor(long id) {
        return (repository.deleteDoctor(id)) ? true : false;
    }

    public boolean deletePatient(long id) {
        return (repository.deletePatient(id))? true:false;
    }

    public boolean changeDoctorFio(long id, Doctor doctor) {
        return (repository.changeDoctorFio(id, doctor))? true:false;
    }

    public boolean changeDoctorSpecialization(long id, Doctor doctor) {
        return (repository.changeDoctorSpecialization(id, doctor))? true:false;
    }

    public boolean changePatientFio(long id, Patient patient) {
        return (repository.changePatientFio(id, patient))? true:false;
    }


    public boolean changePatientDateOfBirth(long id, Patient patient) {
        return (repository.changePatientDateOfBirth(id, patient))? true:false;
    }

    public boolean changePatientAddress(long id, Patient patient) {
        return (repository.changePatientAddress(id, patient))? true:false;
    }

    public boolean changePatientIdNumber(long id, Patient patient) {
        return (repository.changePatientIdNumber(id, patient))? true: false;
    }


    public List<Patient> getAllPatients(){
        return repository.getAllPatients();
    }

    public List<Doctor> getAllDoctors(){
        return repository.getAllDoctors();
    }
}
