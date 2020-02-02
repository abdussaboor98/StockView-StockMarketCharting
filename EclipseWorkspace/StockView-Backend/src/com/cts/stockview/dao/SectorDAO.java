package com.cts.stockview.dao;

import java.util.List;

import com.cts.stockview.model.Sector;

public interface SectorDAO {

	public boolean addSector(Sector sector);

	public boolean updateSector(Sector sector);

	public boolean deleteSector(Sector sector);

	public Sector getSectorById(int id);

	public List<Sector> getAllSectors();
}
