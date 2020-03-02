package com.cts.training.stockview.companyservice.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.cts.training.stockview.companyservice.entity.CompanyEntity;

public interface CompanyRepository extends JpaRepository<CompanyEntity, Integer> {

}
