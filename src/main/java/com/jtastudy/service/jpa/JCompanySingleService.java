package com.jtastudy.service.jpa;

import org.springframework.stereotype.Service;

import com.jtastudy.domain.annotation.jpa.JCompanyTransactional;
import com.jtastudy.domain.annotation.jta.JtaTransactional;
import com.jtastudy.domain.entity.jpa.company.JCompany;
import com.jtastudy.domain.repository.jpa.basic.company.JCompanyRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class JCompanySingleService {

	private final JCompanyRepository JCompanyRepository;

	@JtaTransactional(rollbackFor = {Exception.class})
	public JCompany saveSuccess() {
		JCompany jCompany = createJCompany("success");
		return JCompanyRepository.save(jCompany);
	}

	@JCompanyTransactional(rollbackFor = {Exception.class})
	public void saveFailure() {
		JCompany jCompany = createJCompany("failure");
		JCompanyRepository.save(jCompany);
		throw new RuntimeException("Single Transaction Failure");
	}

	@JCompanyTransactional(rollbackFor = {Exception.class})
	public JCompany findSuccess(Long id) {
		return JCompanyRepository.findById(id).orElseThrow(() -> new RuntimeException("Not Found JCompany"));
	}

	private static JCompany createJCompany(String name) {
		return JCompany.builder()
			.name(name)
			.build();
	}
}
