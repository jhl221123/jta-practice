package com.jtastudy.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.jtastudy.domain.entity.jpa.company.JCompany;
import com.jtastudy.service.jpa.JCompanySingleService;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
public class CompanyController {

	private final JCompanySingleService jCompanySingleService;

	@GetMapping("/companies/success")
	public String success() {
		JCompany jCompany = jCompanySingleService.saveSuccess();
		return jCompany.toString();
	}
}
