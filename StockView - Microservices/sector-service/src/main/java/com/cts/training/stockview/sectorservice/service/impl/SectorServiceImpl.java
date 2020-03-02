package com.cts.training.stockview.sectorservice.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;

import com.cts.training.stockview.sectorservice.entity.SectorEntity;
import com.cts.training.stockview.sectorservice.repo.SectorRepository;

public class SectorServiceImpl {

	@Autowired
	private SectorRepository sectorRepo;

	public List<SectorEntity> getAllSectors() {
		return sectorRepo.findAll();
	}

	public SectorEntity getSectorById(int id) {
		Optional<SectorEntity> sectors = sectorRepo.findById(id);
		return sectors.get();
	}

	public SectorEntity addSector(SectorEntity sector) {
		return sectorRepo.save(sector);
	}

	public SectorEntity updateSector(SectorEntity sector) {
		return sectorRepo.save(sector);
	}

	public void deleteSector(int id) {
		sectorRepo.deleteById(id);
	}
}
