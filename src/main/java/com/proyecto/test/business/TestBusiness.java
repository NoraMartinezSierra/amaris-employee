package com.proyecto.test.business;

import com.proyecto.test.dto.EmployeeDto;
import com.proyecto.test.dto.ResponseReceived;
import com.proyecto.test.mapper.EmployeeMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TestBusiness {

    private static final Logger LOGGER = LoggerFactory.getLogger(TestBusiness.class);

    @Autowired
    private ClientTest clientTest;

    public List<EmployeeDto> employeeList(){
        LOGGER.info("Inicia ");
        ResponseReceived responseReceived =clientTest.getEmployees();
        List<EmployeeDto> employeeDtoList=EmployeeMapper.mapperEmployee(responseReceived.getData());
        return employeeDtoList;
    }

    public List<EmployeeDto> employeeById(Integer id){
        LOGGER.info("Inicia by id");
        ResponseReceived responseReceived =clientTest.getEmployeesById(id);
        List<EmployeeDto> employeeDtoList=EmployeeMapper.mapperEmployee(responseReceived.getData());
        return employeeDtoList;
    }
}
