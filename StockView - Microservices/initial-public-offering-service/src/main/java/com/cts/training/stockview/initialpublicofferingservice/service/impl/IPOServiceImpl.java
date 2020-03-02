package com.cts.training.stockview.initialpublicofferingservice.service.impl;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cts.training.stockview.initialpublicofferingservice.entity.IPOEntity;
import com.cts.training.stockview.initialpublicofferingservice.repo.IPORepository;
import com.cts.training.stockview.initialpublicofferingservice.service.IPOService;

@Service
public class IPOServiceImpl implements IPOService {
	@Autowired
	private IPORepository ipoRepo;
	
	@Override
	public List<IPOEntity> getAllIPOs(){
		return ipoRepo.findAll();
	}

	@Override
	public IPOEntity getIPOById(int id) throws NoSuchElementException{
		Optional<IPOEntity> ipos = ipoRepo.findById(id);
		return ipos.get();
	}

	@Override
	public IPOEntity addIPO(IPOEntity ipo) {
		return ipoRepo.save(ipo);
	}

	@Override
	public IPOEntity updateIPO(IPOEntity ipo) {
		return ipoRepo.save(ipo);
	}

	@Override
	public void deleteIPO(int id) {
		ipoRepo.deleteById(id);
	}
	
}
