package com.cts.training.stockview.test;

import static org.junit.Assert.*;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.junit.BeforeClass;
import org.junit.Ignore;
import org.junit.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.dao.DataIntegrityViolationException;

import com.cts.training.stockview.dao.SectorDAO;
import com.cts.training.stockview.daoimpl.SectorDAOImpl;
import com.cts.training.stockview.model.SectorEntity;

public class SectorEntityTest {

	private static AnnotationConfigApplicationContext context;
	private static SectorDAO sectorDAO;
	private static SectorEntity sector;

	@BeforeClass
	public static void init() {
		context = new AnnotationConfigApplicationContext();
		context.scan("com.cts.training.stockview");
		context.refresh();
		sector = (SectorEntity) context.getBean("sectorEntity");
		sectorDAO = (SectorDAO) context.getBean("sectorDAO");
	}

	@Test
	public void testGetAllSectors() {
		List<SectorEntity> sectors = sectorDAO.getAllSectors();
		assertEquals(1, sectors.size());
	}

	@Test
	public void testGetSectorByIdSuccess() {
		SectorEntity sector = new SectorEntity(1, "Technology", "ffefef");
		assertEquals(sector.getName(), sectorDAO.getSectorById(1).getName());
	}

	@Test
	public void testGetSectorByIdFail() {
		assertEquals(null, sectorDAO.getSectorById(50));
	}

	@Test
	public void testUpdateSectorSuccess() {
		SectorEntity sector = sectorDAO.getSectorById(1);
		sector.setBrief("alalalal");
		assertEquals(true, sectorDAO.updateSector(sector));
	}

	@Test(expected = NullPointerException.class)
	public void testUpdateSectorFail() {
		SectorEntity sector = sectorDAO.getSectorById(90);
		sector.setBrief("alalalal");
		assertEquals(true, sectorDAO.updateSector(sector));
	}

	@Test
//	@Ignore
	public void testAddSectorSuccess() {
		SectorEntity sector = new SectorEntity(1, "Technology", "ffefef");
		assertEquals(true, sectorDAO.addSector(sector));
	}

	@Test(expected = DataIntegrityViolationException.class)
	@Ignore
	public void testAddSectorFail() {
		SectorEntity sector = new SectorEntity(1, "Technology", "ffefef");
		assertEquals(true, sectorDAO.addSector(sector));
	}

	@Test
	@Ignore
	public void testDeleteSectorSuccess() {
		SectorEntity sector = sectorDAO.getSectorById(103);
		assertEquals(true, sectorDAO.deleteSector(sector));
	}

	@Test(expected = IllegalArgumentException.class)
	@Ignore
	public void testDeleteSectorFail() {
		SectorEntity sector = sectorDAO.getSectorById(23);
		assertEquals(true, sectorDAO.deleteSector(sector));
	}
}
