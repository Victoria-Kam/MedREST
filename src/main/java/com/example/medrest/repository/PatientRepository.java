package com.example.medrest.repository;

import com.example.medrest.entity.Patient;
import com.example.medrest.entity.SickLeave;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;

import javax.persistence.TypedQuery;
import javax.transaction.Transactional;
import java.util.List;

@Repository
@Transactional
public class PatientRepository {

    private final SessionFactory sessionFactory;

    public PatientRepository(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }


    public Patient getOne(long id) {
        Session session = sessionFactory.getCurrentSession();
        return session.get(Patient.class, id);
    }

    public List<SickLeave> getAllSickLeaves(String idNum) {
        String sql ="select sickLeave.idleave,\n" +
                "       sickLeave.diagnosis,\n" +
                "       sickLeave.treatment,\n" +
                "       sickLeave.dateopen,\n" +
                "       docOpen.fio as \"Врач, открывший больничный лист\",\n" +
                "       sickLeave.dateclose,\n" +
                "       docClose.fio as \"Врач, закрывший больничный лист\"\n" +
                "from sick_leave sickLeave\n" +
                "         inner join patient pa on sickLeave.idpatient = pa.idpatient inner join doctor docOpen on sickLeave.iddocopen = docOpen.iddoctor left join doctor docClose on sickLeave.iddocclose = docClose.iddoctor\n" +
                "     where pa.identification_number = '"+idNum+"';";
        Session session = sessionFactory.getCurrentSession();
        TypedQuery<SickLeave> query = session.createNativeQuery(sql);
        return query.getResultList();
    }
}
