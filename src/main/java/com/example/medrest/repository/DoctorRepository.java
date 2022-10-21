package com.example.medrest.repository;

import com.example.medrest.entity.Doctor;
import com.example.medrest.entity.Patient;
import com.example.medrest.entity.SickLeave;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.PathVariable;

import javax.persistence.TypedQuery;
import javax.transaction.Transactional;
import java.util.List;

@Repository
@Transactional
public class DoctorRepository {



    private final SessionFactory sessionFactory;

    private final SickLeaveRepository sickLeaveRepositoory;

    public DoctorRepository(SessionFactory sessionFactory, SickLeaveRepository sickLeaveRepositoory) {
        this.sessionFactory = sessionFactory;
        this.sickLeaveRepositoory = sickLeaveRepositoory;
    }

    public Doctor getOne(long id) {
        Session session = sessionFactory.getCurrentSession();
        return session.get(Doctor.class, id);
    }

    public List<SickLeave> findOpenSickLeave(long id) {

        String sql = "select sickLeave.idleave, sickLeave.diagnosis, sickLeave.treatment, sickLeave.dateopen, " +
                "doc.fio as \"Врач, открывший больничный лист\", " +
                " pa.fio as \"Пациент\" from sick_leave sickLeave inner join doctor doc on sickLeave.iddocopen = doc.iddoctor inner join patient pa on sickLeave.idpatient = pa.idpatient where sickLeave.iddocopen =" + id + ";";
        Session session = sessionFactory.getCurrentSession();
        TypedQuery<SickLeave> query = session.createNativeQuery(sql);
        return query.getResultList();
    }


    public List<SickLeave> findCloseSickLeave(long id) {
        String sql = "select sickLeave.idleave, sickLeave.diagnosis, sickLeave.treatment, " +
                     "sickLeave.dateclose, " +
                     "doc.fio as \"Врач, закрывший больничный лист\", pa.fio as \"Пациент\" " +
                     "from sick_leave sickLeave inner join doctor doc on sickLeave.iddocclose = doc.iddoctor inner join patient pa on sickLeave.idpatient = pa.idpatient where sickLeave.iddocclose =" + id + ";";

        Session session = sessionFactory.getCurrentSession();
        TypedQuery<com.example.medrest.entity.SickLeave> query = session.createNativeQuery(sql);
        return query.getResultList();
    }

    public List<Patient> findByNumber(String idNum){

        String sql = "select * from patient where identification_number = '" + idNum + "';";
        Session session = sessionFactory.getCurrentSession();
        TypedQuery<Patient> query = session.createNativeQuery(sql);
        return query.getResultList();
    }

    public List<SickLeave> findPatientSickLeaves(@PathVariable String idNum){
        String sql ="select sickLeave.idleave,\n" +
                "       sickLeave.diagnosis,\n" +
                "       sickLeave.treatment,\n" +
                "       sickLeave.dateopen,\n" +
                "       docOpen.fio as \"Врач, открывший больничный лист\",\n" +
                "       sickLeave.dateclose,\n" +
                "       docClose.fio as \"Врач, закрывший больничный лист\"\n" +
                "from sick_leave sickLeave\n" +
                "         inner join patient pa on sickLeave.idpatient = pa.idpatient inner join doctor docOpen on sickLeave.iddocopen = docOpen.iddoctor left join doctor docClose on sickLeave.iddocclose = docClose.iddoctor\n" +
                "     where pa.identification_number = '"+idNum+"';";        Session session = sessionFactory.getCurrentSession();   // открываем сессию
        TypedQuery<SickLeave> query = session.createNativeQuery(sql);
        return query.getResultList();
    }

    public boolean changeTreatment(long id, SickLeave sickLeave){
        SickLeave changeSickLeave = sickLeaveRepositoory.getOne(id);
        if(changeSickLeave == null){
            return false;
        }
        changeSickLeave.setTreatment(sickLeave.getTreatment());
        Session session = sessionFactory.getCurrentSession();
        session.update(changeSickLeave);
        return true;
    }


    public boolean closeSickLeave(long id, SickLeave sickLeave){
        SickLeave closeSickLeave = sickLeaveRepositoory.getOne(sickLeave.getIdleave());
        Doctor doctor = this.getOne(id);
        if(closeSickLeave == null || doctor == null){
            return false;
        }
        closeSickLeave.setDateClose(sickLeave.getDateClose());
        closeSickLeave.setDoctorClose(doctor);
        Session session = sessionFactory.getCurrentSession();
        session.update(closeSickLeave);
        return true;
    }

    public Patient findPatientById(long id){

        Session session = sessionFactory.getCurrentSession();
        return session.get(Patient.class, id);
    }

    public boolean openSickLeave(long idNum, long idDoc, SickLeave sickLeave){
        Patient patient = (Patient) this.findPatientById(idNum);
        Doctor doctor = this.getOne(idDoc);
        if(doctor == null || patient == null){
            return false;
        }
        sickLeave.setDoctorOpen(doctor);
        sickLeave.setPatient(patient);
        Session session = sessionFactory.getCurrentSession();
        session.save(sickLeave);
        return  true;
    }
}
