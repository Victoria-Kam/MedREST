package com.example.medrest.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "patient")
public class Patient {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idpatient")
    private long idPatient;

    @Column(name = "fio")
    private String fio;

    @Column(name = "dateofbith")
    private String dateofbirth;

    @Column(name = "address")
    private String address;

    @JsonIgnore         //https://stackoverflow.com/questions/42089966/could-not-write-content-failed-to-lazily-initialize-a-collection-of-role
    @OneToMany(mappedBy = "patient",fetch=FetchType.LAZY)        // неправильный маппинг, так как больничные листы (test) отображаются
    private Set<SickLeave> sickLeaveSet ;

    @Column(name = "identification_number")
    private String identificationNumber;

    public long getIdPatient() {
        return idPatient;
    }

    public void setIdPatient(long idPatient) {
        this.idPatient = idPatient;
    }

    public String getFio() {
        return fio;
    }

    public void setFio(String fio) {
        this.fio = fio;
    }

    public String getDateofbirth() {
        return dateofbirth;
    }

    public void setDateofbirth(String dateofbith) {
        this.dateofbirth = dateofbith;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Set<SickLeave> getSickLeaveSet() {
        return sickLeaveSet;
    }

    public void setSickLeaveSet(Set<SickLeave> sickLeaveSet) {
        this.sickLeaveSet = sickLeaveSet;
    }

    public String getIdentificationNumber() {
        return identificationNumber;
    }

    public void setIdentificationNumber(String identificationNumber) {
        this.identificationNumber = identificationNumber;
    }
}
