package com.example.medrest.repository;

import com.example.medrest.entity.Doctor;
import com.example.medrest.entity.Patient;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.TypedQuery;
import javax.transaction.Transactional;
import java.util.List;

@Repository
@Transactional
public class AdminRepository {

    private final SessionFactory sessionFactory;
    private final DoctorRepository doctorRepository;
    private final PatientRepository patientRepository;

    @Autowired
    public AdminRepository(SessionFactory sessionFactory, DoctorRepository doctorRepository, PatientRepository patientRepository) {
        this.sessionFactory = sessionFactory;
        this.doctorRepository = doctorRepository;
        this.patientRepository = patientRepository;
    }

    public boolean addNewDoctor(Doctor doctor) {
        Session session = sessionFactory.getCurrentSession();
        session.save(doctor);
        return true;
    }

    public boolean addNewPatient(Patient patient) {
        Session session = sessionFactory.getCurrentSession();
        session.save(patient);
        return true;
    }

    public boolean deleteDoctor(long id) {
        Session session = sessionFactory.getCurrentSession();
        Doctor doctor = session.find(Doctor.class, id);
        if (doctor == null) {
            return false;
        }
        session.delete(doctor);
        return true;
    }


    public boolean deletePatient(long id) {
        Session session = sessionFactory.getCurrentSession();
        Patient patient = session.find(Patient.class, id);
        if (patient == null) {
            return false;
        }
        session.delete(patient);
        return true;
    }

    public boolean changeDoctorFio(long id, Doctor doctor) {
        Doctor changeDoctor = doctorRepository.getOne(id);
        if(changeDoctor == null){
            return false;
        }
        changeDoctor.setFio(doctor.getFio());
        Session session = sessionFactory.getCurrentSession();
        session.update(changeDoctor);
        return true;
    }

    public boolean changeDoctorSpecialization(long id, Doctor doctor) {
        Doctor changeDoctor = doctorRepository.getOne(id);
        if(changeDoctor == null){
            return false;
        }
        changeDoctor.setSpecialization(doctor.getSpecialization());
        Session session = sessionFactory.getCurrentSession();
        session.update(changeDoctor);
        return  true;
    }

    public boolean changePatientFio(long id, Patient patient) {
        Patient changePatient = patientRepository.getOne(id);
        if(changePatient == null){
            return false;
        }
        changePatient.setFio(patient.getFio());
        Session session = sessionFactory.getCurrentSession();
        session.update(changePatient);
        return  true;
    }


    public boolean changePatientDateOfBirth(long id, Patient patient) {
        Patient changePatient = patientRepository.getOne(id);
        if(changePatient == null){
            return false;
        }
        changePatient.setDateofbirth(patient.getDateofbirth());
        Session session = sessionFactory.getCurrentSession();
        session.update(changePatient);
        return true;
    }

    public boolean changePatientAddress(long id, Patient patient) {
        Patient changePatient = patientRepository.getOne(id);
        if(changePatient == null){
            return false;
        }
        changePatient.setAddress(patient.getAddress());
        Session session = sessionFactory.getCurrentSession();
        session.update(changePatient);
        return true;
    }

    public boolean changePatientIdNumber(long id, Patient patient) {
        Patient changePatient = patientRepository.getOne(id);
        if(changePatient == null){
            return false;
        }
        changePatient.setIdentificationNumber(patient.getIdentificationNumber());
        Session session = sessionFactory.getCurrentSession();
        session.update(changePatient);
        return true;
    }


    public List<Patient> getAllPatients(){
        String sql = "select * from patient";
        Session session = sessionFactory.getCurrentSession();   // открываем сессию
        TypedQuery<Patient> query = session.createNativeQuery(sql);
        return query.getResultList();
    }

    public List<Doctor> getAllDoctors(){
        String sql = "select * from doctor";
        Session session = sessionFactory.getCurrentSession();   // открываем сессию
        TypedQuery<Doctor> query = session.createNativeQuery(sql);
        return query.getResultList();
    }
}
