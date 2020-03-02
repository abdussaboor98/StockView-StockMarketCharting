package com.cts.training.stockview.initialpublicofferingservice.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.cts.training.stockview.initialpublicofferingservice.entity.IPOEntity;

public interface IPORepository extends JpaRepository<IPOEntity, Integer> {

}
