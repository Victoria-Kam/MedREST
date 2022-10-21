package com.example.medrest.entity;

import javax.persistence.*;

@Entity
@Table(name = "doctor")
public class Doctor {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "iddoctor")
    private long iddoctor;

    @Column(name = "fio")
    private String fio;

    @Column(name = "specialization")
    private String specialization;

    public long getIddoctor() {
        return iddoctor;
    }

    public void setIddoctor(long iddoctor) {
        this.iddoctor = iddoctor;
    }

    public String getFio() {
        return fio;
    }

    public void setFio(String fio) {
        this.fio = fio;
    }

    public String getSpecialization() {
        return specialization;
    }

    public void setSpecialization(String specialization) {
        this.specialization = specialization;
    }
}
