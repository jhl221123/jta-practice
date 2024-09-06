package com.jtastudy.service.jta;

import org.springframework.stereotype.Service;

import com.jtastudy.domain.annotation.jpa.JCompanyTransactional;
import com.jtastudy.domain.annotation.jpa.JStoreTransactional;
import com.jtastudy.domain.annotation.jta.JtaTransactional;
import com.jtastudy.domain.entity.jpa.company.JCompany;
import com.jtastudy.domain.entity.jpa.contract.JContract;
import com.jtastudy.domain.entity.jpa.store.JStore;
import com.jtastudy.domain.repository.jpa.jta.company.JCompanyJtaRepository;
import com.jtastudy.domain.repository.jpa.jta.contract.JContractJtaRepository;
import com.jtastudy.domain.repository.jpa.jta.employee.JEmployeeJtaRepository;
import com.jtastudy.domain.repository.jpa.jta.store.JStoreJtaRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class JpaJtaService {

	private final JCompanyJtaRepository jCompanyJtaRepository;
	private final JStoreJtaRepository jStoreJtaRepository;
	private final JContractJtaRepository jContractJtaRepository;
	private final JEmployeeJtaRepository jEmployeeJtaRepository;
	
	@JtaTransactional(rollbackFor = {Exception.class})
	public Long saveContract() {
		// JCompany jCompany = jCompanyJtaRepository.findById(companyId)
		// 	.orElseThrow(() -> new RuntimeException("JCompany not found"));
		// JStore jStore = jStoreJtaRepository.findById(storeId)
		// 	.orElseThrow(() -> new RuntimeException("JStore not found"));
		JCompany jCompany = createJCompany("company_success");
		JStore jStore = createJStore("store_success");

		JCompany savedCompany = jCompanyJtaRepository.save(jCompany);
		JStore savedStore = jStoreJtaRepository.save(jStore);

		JContract jContract = JContract.builder()
			.companyId(savedCompany.getId())
			.storeId(savedStore.getId())
			.build();
		JContract savedContract = jContractJtaRepository.save(jContract);
		return savedContract.getId();
	}

	@JtaTransactional(rollbackFor = {Exception.class})
	public void saveContractFailure(String type) {
		JCompany jCompany = createJCompany("company_failure");
		JStore jStore = createJStore("store_failure");

		JCompany savedCompany = jCompanyJtaRepository.save(jCompany);
		JStore savedStore = jStoreJtaRepository.save(jStore);

		if(type.equals("fail")) throw new RuntimeException("식당 저장 중 예외 발생");

		JContract jContract = JContract.builder()
			.companyId(savedCompany.getId())
			.storeId(savedStore.getId())
			.build();
		jContractJtaRepository.save(jContract);
	}

	private static JCompany createJCompany(String name) {
		return JCompany.builder()
			.name(name)
			.build();
	}

	private static JStore createJStore(String name) {
		return JStore.builder()
			.name(name)
			.build();
	}
}
