package com.jtastudy.service.jta;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Commit;
import org.springframework.transaction.annotation.Transactional;

import com.jtastudy.domain.annotation.jta.JtaTransactional;
import com.jtastudy.domain.entity.jpa.company.JCompany;
import com.jtastudy.domain.entity.jpa.contract.JContract;
import com.jtastudy.domain.repository.jpa.jta.company.JCompanyJtaRepository;
import com.jtastudy.domain.repository.jpa.jta.contract.JContractJtaRepository;

@SpringBootTest
class JpaJtaServiceTest {

	@Autowired
	private JpaJtaService jpaJtaService;
	@Autowired
	private JContractJtaRepository jContractJtaRepository;
	@Autowired
	private JCompanyJtaRepository jCompanyJtaRepository;

	@DisplayName("[JTA] 식당과 기업 간 계약을 생성한다.")
	@Test
	void save_success() {
	    // given when
		Long contractId = jpaJtaService.saveContract();

		//then
		JContract jContract = jContractJtaRepository.findById(contractId).get();
		Assertions.assertThat(jContract).isNotNull();
		System.out.println(jContract);

		JCompany jCompany = jCompanyJtaRepository.findById(jContract.getCompanyId()).get();
		Assertions.assertThat(jCompany.getName()).isEqualTo("company_success");

	}
}