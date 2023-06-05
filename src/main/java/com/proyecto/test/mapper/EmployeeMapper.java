package com.proyecto.test.mapper;

import com.proyecto.test.dto.EmployeeDto;
import com.proyecto.test.dto.EmployeeReceived;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class EmployeeMapper {

    public static List<EmployeeDto> mapperEmployee(EmployeeReceived[] employeeReceiveds){
        List<EmployeeDto> employeeDtoList= new ArrayList<>();
        for(EmployeeReceived employee : employeeReceiveds){
            EmployeeDto employeeDto = new EmployeeDto();
            employeeDto.setId(employee.getId());
            employeeDto.setEmployee_name(employee.getEmployee_name());
            employeeDto.setEmployee_age(employee.getEmployee_age());
            employeeDto.setEmployee_salary(employee.getEmployee_salary());
            employeeDto.setProfile_image(employee.getProfile_image());
            employeeDto.setEmployee_anual_salary(BigDecimal.valueOf(employee.getEmployee_salary().doubleValue()*12));
            employeeDtoList.add(employeeDto);
        }
        return employeeDtoList;
    }
}
