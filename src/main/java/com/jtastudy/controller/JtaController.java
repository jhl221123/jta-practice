package com.jtastudy.controller;

import org.springframework.data.repository.query.Param;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.jtastudy.service.jta.JpaJtaService;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
public class JtaController {

	private final JpaJtaService jpaJtaService;

	@GetMapping("/success")
	public String success() {
		Long id = jpaJtaService.saveContract();
		return String.valueOf(id);
	}

	@GetMapping("/failure")
	public String failure(@Param("type") String type) {
		jpaJtaService.saveContractFailure(type);
		return "failure";
	}
}
