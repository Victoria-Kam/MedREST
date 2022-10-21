package com.example.medrest.controller;

import com.example.medrest.ckeck.CheckData;
import com.example.medrest.entity.Patient;
import com.example.medrest.entity.SickLeave;
import com.example.medrest.service.PatientService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/patient")
public class PatientController {


    private final PatientService service;

    @Autowired
    public PatientController(PatientService service) {
        this.service = service;
    }

    /******************************************************************************************************************/

    @Operation(method = "GET", description = "Get patient information", tags = "patients")
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "404",
                    description = "Patient not found"
            ),


            @ApiResponse(
                    responseCode = "200",
                    description = "Patient is found"
            )
    })

    @GetMapping("/info/{id}")
    public ResponseEntity<Patient> infoPatient(@PathVariable long id){
       if(service.getOne(id) == null){
           return new ResponseEntity<>(service.getOne(id),HttpStatus.NOT_FOUND);
       }
        return new ResponseEntity<>(service.getOne(id), HttpStatus.OK);
    }

    /******************************************************************************************************************/

    @Operation(method = "GET", description = "Get all sick leaves by identification number", tags = "patients")
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
                    description = "Get list sick leaves"
            )
    })

    @GetMapping("/sick-leave/{idNum}")
    public ResponseEntity<List<SickLeave>> allSickLeaves(@PathVariable String idNum){
        if(CheckData.checkIdentificationNumber(idNum)){
            return (service.getAllSickLeaves(idNum).size() != 0)? new ResponseEntity<>(service.getAllSickLeaves(idNum),HttpStatus.OK)
                    : new ResponseEntity<>(service.getAllSickLeaves(idNum), HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(service.getAllSickLeaves(idNum), HttpStatus.BAD_REQUEST);
    }
}
