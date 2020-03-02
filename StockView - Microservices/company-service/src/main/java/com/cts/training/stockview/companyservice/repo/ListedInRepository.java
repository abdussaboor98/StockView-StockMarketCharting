package com.cts.training.stockview.companyservice.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.cts.training.stockview.companyservice.entity.ListedInEntity;

@Repository
public interface ListedInRepository extends JpaRepository<ListedInEntity, Integer> {

}
