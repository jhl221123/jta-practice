package com.jtastudy.domain.entity.jpa.contract;

import com.jtastudy.domain.entity.jpa.company.JCompany;
import com.jtastudy.domain.entity.jpa.store.JStore;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Entity
@Getter
@ToString
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class JContract {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	private Long companyId;

	private Long storeId;

	@Builder
	public JContract(Long id, Long companyId, Long storeId) {
		this.id = id;
		this.companyId = companyId;
		this.storeId = storeId;
	}
}
