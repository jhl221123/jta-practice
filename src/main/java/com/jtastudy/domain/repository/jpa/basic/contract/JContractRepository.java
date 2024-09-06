package com.jtastudy.domain.repository.jpa.basic.contract;

import org.springframework.data.jpa.repository.JpaRepository;

import com.jtastudy.domain.entity.jpa.contract.JContract;

public interface JContractRepository extends JpaRepository<JContract, Long> {
}
