package com.project.employeemanagement.repository;

import com.project.employeemanagement.model.Employee;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Long> {
        List<Employee> findByFullName(String name, Pageable pageable);

        Page<Employee> findBySalaryGreaterThanEqualAndAgeGreaterThanEqual(double minSalary, int minAge, Pageable pageable);
}
