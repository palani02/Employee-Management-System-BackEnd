package com.project.employeemanagement.controller;

import com.project.employeemanagement.model.Employee;
import com.project.employeemanagement.repository.EmployeeRepository;
import jakarta.persistence.UniqueConstraint;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.query.Param;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.util.List;

@RestController
@RequestMapping("api/v1")
@CrossOrigin(value = "*")
public class EmployeeController {

    @Autowired
    EmployeeRepository employeeRepository;

    @GetMapping("/employee")
    public Page<Employee> getAllEmployee(Pageable pageable){
        return employeeRepository.findAll(pageable);
    }
    @GetMapping("/employee/user")
    public List<Employee> searchEmployeeWithName(@Param("name") String name ,Pageable pageable){
        return employeeRepository.findByFullName(name,pageable);
    }

    @PutMapping("/employee")
    public String updateEmp(@RequestBody Employee employee){
        employeeRepository.save(employee);
        return "updated ";
    }

    @PostMapping("/employee")
    public String addEmployee(@RequestBody Employee employee){
        employeeRepository.save(employee);
        return "User added successfully";
    }

    @DeleteMapping("/employee")
    public String deleteEmp(@Param("id") long id){
        employeeRepository.deleteById(id);
        return "Deleted successfully";
    }

    @GetMapping("/employee/filter")
    public Page<Employee> filterEmp(   @RequestParam(defaultValue = "0") int page,
                                       @RequestParam(defaultValue = "10") int size,
                                       @RequestParam(required = false,defaultValue = "0") Double minSalary,
                                       @RequestParam(required = false , defaultValue = "0") Integer minAge
    ){
        Pageable pageable = PageRequest.of(page,size);
        return employeeRepository.findBySalaryGreaterThanEqualAndAgeGreaterThanEqual(minSalary,minAge,pageable);
    }
}
