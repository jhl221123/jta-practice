package com.jtastudy.domain.repository.jpa.basic.employee;

import org.springframework.data.jpa.repository.JpaRepository;

import com.jtastudy.domain.entity.jpa.employee.JEmployee;

public interface JEmployeeRepository extends JpaRepository<JEmployee, Long> {
}
