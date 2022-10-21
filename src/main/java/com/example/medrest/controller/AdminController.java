package com.example.medrest.controller;

import com.example.medrest.ckeck.CheckData;
import com.example.medrest.entity.Doctor;
import com.example.medrest.entity.Patient;
import com.example.medrest.service.AdminService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/admin")
public class AdminController {

    private final AdminService service;

    @Autowired
    public AdminController(AdminService service) {
        this.service = service;
    }

    /******************************************************************************************************************/

    @Operation(method = "POST", description = "Add doctor", tags = "doctors")
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "400",
                    description = "Bad request"
            ),
            @ApiResponse(
                    responseCode = "200",
                    description = "Add doctor"
            )
    })

    @PostMapping("add-doctor/")
    public ResponseEntity<String> addNewDoctor(@RequestBody Doctor doctor) {
        return (service.addNewDoctor(doctor)) ? new ResponseEntity<>("Doctor was added!", HttpStatus.OK)
                : new ResponseEntity<>("Bad request", HttpStatus.BAD_REQUEST);
    }

    /******************************************************************************************************************/

    @Operation(method = "POST", description = "Add patient", tags = "patients")
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "400",
                    description = "Bad request"
            ),
            @ApiResponse(
                    responseCode = "200",
                    description = "Add patient"
            )
    })

    @PostMapping("add-patient/")
    public ResponseEntity<String> addNewPatient(@RequestBody Patient patient) {
        return (service.addNewPatient(patient)) ? new ResponseEntity<>("Patient was added!", HttpStatus.OK)
                : new ResponseEntity<>("Bad request", HttpStatus.BAD_REQUEST);
    }

    /******************************************************************************************************************/

    @Operation(method = "DELETE", description = "Delete doctor", tags = "doctors")
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "404",
                    description = "Doctor not found"
            ),
            @ApiResponse(
                    responseCode = "200",
                    description = "Delete doctor"
            )
    })

    @DeleteMapping("/delete-doctor/{id}/")
    public ResponseEntity<String> deleteDoctor(@PathVariable long id) {
        return (service.deleteDoctor(id)) ? new ResponseEntity<>("Doctor was deleted", HttpStatus.OK)
                : new ResponseEntity<>("Doctor not found", HttpStatus.NOT_FOUND);
    }

    /******************************************************************************************************************/

    @Operation(method = "DELETE", description = "Delete patient", tags = "patients")
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "404",
                    description = "Patient not found"
            ),
            @ApiResponse(
                    responseCode = "200",
                    description = "Delete patient"
            )
    })

    @DeleteMapping("/delete-patient/{id}/")
    public ResponseEntity<String> deletePatient(@PathVariable long id) {
        return (service.deletePatient(id)) ? new ResponseEntity<>("Patient was deleted", HttpStatus.OK)
                : new ResponseEntity<>("Patient not found", HttpStatus.NOT_FOUND);
    }

    /******************************************************************************************************************/

    @Operation(method = "PUT", description = "Change patient fio", tags = "patients")
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "404",
                    description = "Patient not found"
            ),
            @ApiResponse(
                    responseCode = "400",
                    description = "Invalid data"
            ),
            @ApiResponse(
                    responseCode = "200",
                    description = "Change patient fio"
            )
    })

    @PutMapping("/patient-change-fio/{id}/")
    public ResponseEntity<String> changePatientFio(@PathVariable long id, @RequestBody Patient patient) {
        if (CheckData.checkNames(patient.getFio())) {
            return (service.changePatientFio(id, patient)) ? new ResponseEntity<>("Fio patient was changed", HttpStatus.OK)
                    : new ResponseEntity<>("Patient not found", HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>("Invalid data", HttpStatus.BAD_REQUEST);
    }

    /******************************************************************************************************************/

    @Operation(method = "PUT", description = "change doctor fio", tags = "patients")
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "404",
                    description = "Patient not found"
            ),
            @ApiResponse(
                    responseCode = "400",
                    description = "Invalid data"
            ),
            @ApiResponse(
                    responseCode = "200",
                    description = " Change doctor fio"
            )
    })

    @PutMapping("/doctor-change-fio/{id}/")
    public ResponseEntity<String> changeDoctorFio(@PathVariable long id, @RequestBody Doctor doctor) {
        if (CheckData.checkNames(doctor.getFio())) {
            return (service.changeDoctorFio(id, doctor)) ? new ResponseEntity<>("Fio doctor was changed", HttpStatus.OK)
                    : new ResponseEntity<>("Doctor not found", HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>("Invalid data", HttpStatus.BAD_REQUEST);
    }

    /******************************************************************************************************************/

    @Operation(method = "PUT", description = "Change doctor specialization", tags = "doctors")
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "404",
                    description = "Doctor not found"
            ),
            @ApiResponse(
                    responseCode = "400",
                    description = "Invalid data"
            ),
            @ApiResponse(
                    responseCode = "200",
                    description = "Change doctor specialization"
            )
    })

    @PutMapping("/doctor-change-spec/{id}/")
    public ResponseEntity<String> changeDoctorSpecialization(@PathVariable long id, @RequestBody Doctor doctor) {
        if (CheckData.checkText(doctor.getSpecialization())) {
            return (service.changeDoctorSpecialization(id, doctor)) ? new ResponseEntity<>("Doctor specialization was changed", HttpStatus.OK)
                    : new ResponseEntity<>("Doctor not found", HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>("Invalid data", HttpStatus.BAD_REQUEST);
    }

    /******************************************************************************************************************/

    @Operation(method = "PUT", description = "Change patient date of birth", tags = "patients")
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "404",
                    description = "Patient not found"
            ),
            @ApiResponse(
                    responseCode = "400",
                    description = "Invalid data"
            ),
            @ApiResponse(
                    responseCode = "200",
                    description = "Change patient date of birth"
            )
    })

    @PutMapping("/patient-change-date-of-birth/{id}/")
    public ResponseEntity<String> changePatientDateOfBirth(@PathVariable long id, @RequestBody Patient patient) {
        if(CheckData.checkDate(patient.getDateofbirth())){
            return (service.changePatientDateOfBirth(id, patient))? new ResponseEntity<>("Date of birth patient was changed",HttpStatus.OK)
                    : new ResponseEntity<>("Patient not found",HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>("Invalid data", HttpStatus.BAD_REQUEST);
    }


    /******************************************************************************************************************/

    @Operation(method = "PUT", description = "Change patient address", tags = "patients")
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "404",
                    description = "Patient not found"
            ),

            @ApiResponse(
                    responseCode = "200",
                    description = "Change patient address"
            )
    })

    @PutMapping("/patient-change-address/{id}/")
    public ResponseEntity<String> changePatientAddress(@PathVariable long id, @RequestBody Patient patient) {
        return (service.changePatientAddress(id, patient))? new ResponseEntity<>("Patient address was changed", HttpStatus.OK)
                : new ResponseEntity<>("Patient not found",HttpStatus.NOT_FOUND);
    }


    /******************************************************************************************************************/

    @Operation(method = "PUT", description = "Change patient identification number", tags = "patients")
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "404",
                    description = "Patient not found"
            ),

            @ApiResponse(
                    responseCode = "400",
                    description = "Invalid data"
            ),

            @ApiResponse(
                    responseCode = "200",
                    description = "Change patient identification number"
            )
    })

    @PutMapping("/patient-change-id-number/{id}/")
    public ResponseEntity<String> changePatientIdNumber(@PathVariable long id, @RequestBody Patient patient) {
        if(CheckData.checkIdentificationNumber(patient.getIdentificationNumber())){
            return (service.changePatientIdNumber(id, patient))? new ResponseEntity<>("Patient identification number was changed", HttpStatus.OK)
                    : new ResponseEntity<>("Patient not found", HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>("Invalid data", HttpStatus.BAD_REQUEST);
    }


    /******************************************************************************************************************/


    @Operation(method = "GET", description = "Get all patient", tags = "patients")
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "404",
                    description = "List is empty"
            ),


            @ApiResponse(
                    responseCode = "200",
                    description = "Get all patients"
            )
    })

    @GetMapping("/all-patients/")
    public ResponseEntity<List<Patient>> allPatients(){
        if (service.getAllPatients().size() == 0){
            return new ResponseEntity<>(service.getAllPatients(),HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(service.getAllPatients(),HttpStatus.OK);
    }

    /******************************************************************************************************************/

    @Operation(method = "GET", description = "Get all doctors", tags = "doctors")
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "404",
                    description = "List is empty"
            ),


            @ApiResponse(
                    responseCode = "200",
                    description = "Get all doctors"
            )
    })

    @GetMapping("/all-doctors/")
    public ResponseEntity<List<Doctor>> allDoctors(){
        if (service.getAllDoctors().size() == 0){
            return new ResponseEntity<>(service.getAllDoctors(),HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(service.getAllDoctors(),HttpStatus.OK);
    }

}
