package com.cts.training.stockview.sectorservice.service;

import java.util.List;

import com.cts.training.stockview.sectorservice.entity.SectorEntity;

public interface SectorService {
	public List<SectorEntity> getAllSectors();

	public SectorEntity getSectorById(int id);

	public SectorEntity addSector(SectorEntity sector);

	public SectorEntity updateSector(SectorEntity sector);

	public void deleteSector(int id);
}
