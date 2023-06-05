package com.proyecto.test.controllers;

import com.proyecto.test.business.TestBusiness;
import com.proyecto.test.dto.EmployeeDto;
import com.proyecto.test.dto.ResponseDto;
import com.proyecto.test.dto.ResponseReceived;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;


@RestController
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping("/api")
public class TestController {

    @Autowired
    private TestBusiness testBusiness;


    /* Pantalla principal de resumen el home
     * Autor: Nora Martinez
     * Fecha: septiembre/21*/
    @ApiOperation(value = "Abstract all app",
            responseContainer = "Object"
    )
    @GetMapping(
            path = "/employees",
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public ResponseEntity<List<EmployeeDto>> employeesList() {
        List<EmployeeDto> employeList = testBusiness.employeeList();
        if (null != employeList) {
            return ResponseEntity.ok(employeList);
        } else {
            return ResponseEntity.badRequest().build();
        }
    }

    /* Pantalla principal de resumen el home
     * Autor: Nora Martinez
     * Fecha: septiembre/21*/
    @ApiOperation(value = "Abstract all app",
            responseContainer = "Object"
    )
    @GetMapping(
            path = "/employee/{id}",
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public ResponseEntity<List<EmployeeDto>> employeeById(
            @PathVariable("id") Integer id) {
        List<EmployeeDto> employeList = testBusiness.employeeById(id);
        if (null != employeList) {
            return ResponseEntity.ok(employeList);
        } else {
            return ResponseEntity.badRequest().build();
        }
    }
}
