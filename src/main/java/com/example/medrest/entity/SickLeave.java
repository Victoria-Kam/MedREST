package com.example.medrest.entity;


import javax.persistence.*;

@Entity
@Table(name = "sick_leave")
public class SickLeave {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idleave")
    private long idleave;

    @Column(name = "diagnosis")
    private String diagnosis;

    @Column(name = "treatment")
    //@Lob
    private String treatment;

    @Column(name = "dateopen")
    private String dateOpen;


    @ManyToOne
    @JoinColumn(name = "iddocopen")
    private Doctor doctorOpen;


    @Column(name = "dateclose", nullable = true)
    private String dateClose;

    @ManyToOne
    @JoinColumn(name = "iddocclose", nullable = true)
    private Doctor doctorClose;


    @ManyToOne
    @JoinColumn(name = "idpatient")
    private Patient patient;


    public long getIdleave() {
        return idleave;
    }

    public void setIdleave(long idleave) {
        this.idleave = idleave;
    }

    public String getDiagnosis() {
        return diagnosis;
    }

    public void setDiagnosis(String diagnosis) {
        this.diagnosis = diagnosis;
    }

    public String getTreatment() {
        return treatment;
    }

    public void setTreatment(String treatment) {
        this.treatment = treatment;
    }

    public String getDateOpen() {
        return dateOpen;
    }

    public void setDateOpen(String dateOpen) {
        this.dateOpen = dateOpen;
    }

    public Doctor getDoctorOpen() {
        return doctorOpen;
    }

    public void setDoctorOpen(Doctor doctorOpen) {
        this.doctorOpen = doctorOpen;
    }

    public String getDateClose() {
        return dateClose;
    }

    public void setDateClose(String dateClose) {
        this.dateClose = dateClose;
    }

    public Doctor getDoctorClose() {
        return doctorClose;
    }

    public void setDoctorClose(Doctor doctorClose) {
        this.doctorClose = doctorClose;
    }

    public Patient getPatient() {
        return patient;
    }

    public void setPatient(Patient patient) {
        this.patient = patient;
    }
}
