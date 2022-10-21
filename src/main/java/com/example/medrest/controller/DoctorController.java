package com.example.medrest.controller;

import com.example.medrest.ckeck.CheckData;
import com.example.medrest.entity.Doctor;
import com.example.medrest.entity.Patient;
import com.example.medrest.entity.SickLeave;
import com.example.medrest.service.DoctorService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/doctor")
public class DoctorController {

    private final DoctorService service;

    @Autowired
    public DoctorController(DoctorService service) {
        this.service = service;
    }

    @Operation(method = "GET", description = "Get doctor information", tags = "doctors")
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "404",
                    description = "Doctor not found"
            ),


            @ApiResponse(
                    responseCode = "200",
                    description = "Doctor is found"
            )
    })

    @GetMapping("/info/{id}")
    public ResponseEntity<Doctor> infoDoctor(@PathVariable long id) {
        if (service.getOne(id) == null) {
            return new ResponseEntity<>(service.getOne(id), HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(service.getOne(id), HttpStatus.OK);
    }

    /******************************************************************************************************************/

    @Operation(method = "POST", description = "Open a new sick leave", tags = "doctors")
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "404",
                    description = "Patient or doctor not found"
            ),

            @ApiResponse(
                    responseCode = "400",
                    description = "Invalid data"
            ),

            @ApiResponse(
                    responseCode = "200",
                    description = "New sick leave was opened"
            )
    })

    @PostMapping("/open-leave/{idNum}-{idDoc}")
    public ResponseEntity<String> openSickLeave(@PathVariable long idNum, @PathVariable long idDoc, @RequestBody SickLeave sickLeave) {
        if (CheckData.checkDate(sickLeave.getDateOpen())) {
            return (service.openSickLeave(idNum, idDoc, sickLeave)) ? new ResponseEntity<>("Sick leave was opened!", HttpStatus.OK)
                    : new ResponseEntity<>("Patient or doctor not found", HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>("Invalid data", HttpStatus.BAD_REQUEST);
    }

    /******************************************************************************************************************/

    @Operation(method = "GET", description = "Get opened sick leaves by doctor", tags = "doctors")
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "404",
                    description = "Doctor not found"
            ),


            @ApiResponse(
                    responseCode = "200",
                    description = "List sick leaves opened by doctor"
            )
    })

    @GetMapping("/opened-leave/{id}")
    public ResponseEntity<List<SickLeave>> findOpenSickLeave(@PathVariable long id) {
        if (service.findOpenSickLeave(id).size() == 0) {
            return new ResponseEntity<>(service.findOpenSickLeave(id), HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(service.findOpenSickLeave(id), HttpStatus.OK);
    }

    /******************************************************************************************************************/

    @Operation(method = "GET", description = "Get closed sick leaves by doctor", tags = "doctors")
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "404",
                    description = "Doctor not found"
            ),


            @ApiResponse(
                    responseCode = "200",
                    description = "List sick leaves closed by doctor"
            )
    })

    @GetMapping("/close-leave/{id}")
    public ResponseEntity<List<SickLeave>> findCloseSickLeave(@PathVariable long id) {

        if (service.findCloseSickLeave(id).size() == 0) {
            return new ResponseEntity<>(service.findCloseSickLeave(id), HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(service.findCloseSickLeave(id), HttpStatus.OK);
    }

    /******************************************************************************************************************/

    @Operation(method = "GET", description = "find patient by identification number", tags = "doctors")
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
                    description = "Patient is found"
            )
    })

    @GetMapping("/find-patient/{idNum}")
    public ResponseEntity<List<Patient>> findByNumber(@PathVariable String idNum) {
        if (CheckData.checkIdentificationNumber(idNum)) {
            return (service.findByNumber(idNum).size() != 0) ? new ResponseEntity<>(service.findByNumber(idNum), HttpStatus.OK)
                    : new ResponseEntity<>(service.findByNumber(idNum), HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(service.findByNumber(idNum), HttpStatus.BAD_REQUEST);
    }

    /******************************************************************************************************************/

    @Operation(method = "GET", description = "Get all sick leaves by patient identification number", tags = "doctors")
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
                    description = "Get list sick leaves by identification number"
            )
    })

    @GetMapping("/find-patient-sick-leave/{idNum}")
    public ResponseEntity<List<SickLeave>> findPatientSickLeaves(@PathVariable String idNum) {
        if (CheckData.checkIdentificationNumber(idNum)) {
            return (service.findPatientSickLeaves(idNum).size() != 0) ? new ResponseEntity<>(service.findPatientSickLeaves(idNum), HttpStatus.OK)
                    : new ResponseEntity<>(service.findPatientSickLeaves(idNum), HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(service.findPatientSickLeaves(idNum), HttpStatus.BAD_REQUEST);
    }

    /******************************************************************************************************************/


    @Operation(method = "PUT", description = "Change patient sick leave treatment", tags = "doctors")
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "404",
                    description = "Sick leave not found"
            ),

            @ApiResponse(
                    responseCode = "400",
                    description = "Invalid data"
            ),

            @ApiResponse(
                    responseCode = "200",
                    description = "Change patient sick leave treatment"
            )
    })

    @PutMapping("/change-patient-sick-leave/{id}")
    public ResponseEntity<String> changeTreatment(@PathVariable long id, @RequestBody SickLeave sickLeave) {
        return (service.changeTreatment(id, sickLeave)) ? new ResponseEntity<>("Treatment was changed!", HttpStatus.OK)
                : new ResponseEntity<>("Sick leave not found", HttpStatus.NOT_FOUND);
    }


    /******************************************************************************************************************/

    @Operation(method = "PUT", description = "Close sick leaves", tags = "doctors")
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "404",
                    description = "Doctor or sick leave not found"
            ),

            @ApiResponse(
                    responseCode = "400",
                    description = "Invalid data"
            ),

            @ApiResponse(
                    responseCode = "200",
                    description = "Treatment was closed"
            )
    })

    @PutMapping("/close-patient-sick-leave/{id}")
    public ResponseEntity<String> closeSickLeave(@PathVariable long id, @RequestBody SickLeave sickLeave) {
        if (CheckData.checkDate(sickLeave.getDateClose())) {
            return (service.closeSickLeave(id, sickLeave)) ? new ResponseEntity<>("Treatment was closed!", HttpStatus.OK)
                    : new ResponseEntity<>("Doctor or sick leave not found", HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>("Invalid data", HttpStatus.BAD_REQUEST);
    }
}
