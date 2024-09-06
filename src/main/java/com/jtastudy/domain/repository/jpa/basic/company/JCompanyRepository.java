package com.jtastudy.domain.repository.jpa.basic.company;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.jtastudy.domain.entity.jpa.company.JCompany;

public interface JCompanyRepository extends JpaRepository<JCompany, Long> {
}
