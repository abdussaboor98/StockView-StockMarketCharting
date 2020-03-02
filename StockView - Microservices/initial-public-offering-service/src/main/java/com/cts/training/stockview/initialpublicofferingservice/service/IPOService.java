package com.cts.training.stockview.initialpublicofferingservice.service;

import java.util.List;

import com.cts.training.stockview.initialpublicofferingservice.entity.IPOEntity;


public interface IPOService {
	public List<IPOEntity> getAllIPOs();

	public IPOEntity getIPOById(int id);

	public IPOEntity addIPO(IPOEntity ipo);

	public IPOEntity updateIPO(IPOEntity ipo);

	public void deleteIPO(int id);
}
