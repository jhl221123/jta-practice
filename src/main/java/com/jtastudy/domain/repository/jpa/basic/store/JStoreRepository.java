package com.jtastudy.domain.repository.jpa.basic.store;

import org.springframework.data.jpa.repository.JpaRepository;

import com.jtastudy.domain.entity.jpa.store.JStore;

public interface JStoreRepository extends JpaRepository<JStore, Long> {
}
