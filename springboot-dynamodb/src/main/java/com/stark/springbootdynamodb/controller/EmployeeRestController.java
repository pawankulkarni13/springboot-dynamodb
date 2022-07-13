package com.stark.springbootdynamodb.controller;

import com.stark.springbootdynamodb.model.EmployeeInfo;
import com.stark.springbootdynamodb.repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/employees")
public class EmployeeRestController {

    @Autowired
    private EmployeeRepository employeeRepository;

    @GetMapping
    public Iterable<EmployeeInfo> getEmployeesInfo(){
        return employeeRepository.findAll();
    }

    @GetMapping("/{id}")
    public EmployeeInfo getEmployeeInfo(@PathVariable String id){
        return employeeRepository.findById(id).orElse(null);
    }

    @PostMapping
    public void getEmployeesInfo(@RequestBody EmployeeInfo employeeInfo){
        employeeRepository.save(employeeInfo);
    }


}
