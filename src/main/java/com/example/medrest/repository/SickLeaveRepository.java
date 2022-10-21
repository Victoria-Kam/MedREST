package com.example.medrest.repository;

import com.example.medrest.entity.SickLeave;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;

@Repository
public class SickLeaveRepository {

    private final SessionFactory sessionFactory;

    public SickLeaveRepository(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    public SickLeave getOne(long id) {
        Session session = sessionFactory.getCurrentSession();
        return session.get(SickLeave.class, id);
    }
}
