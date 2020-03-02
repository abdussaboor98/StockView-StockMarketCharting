package com.cts.training.stockview.sectorservice.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.cts.training.stockview.sectorservice.entity.SectorEntity;
import com.cts.training.stockview.sectorservice.service.SectorService;

@CrossOrigin(origins = "*")
@RestController
public class SectorRestController {

	@Autowired
	private SectorService sectorService;
	
	@GetMapping(value = "/sectors",produces = "application/json")
	public List<SectorEntity> getAllSectors(){
		return sectorService.getAllSectors();
	}
	
	@GetMapping(value = "/sectors/{id}", produces = "application/json")
	public SectorEntity getSectorById(@PathVariable("id") int id){
		return sectorService.getSectorById(id);
	}
	
	@PostMapping(value = "/sectors",consumes = "application/json")
	public SectorEntity addSector(@RequestBody SectorEntity sector) {
		return sectorService.addSector(sector);
	}
	
	@PutMapping(value = "/sectors",consumes = "application/json")
	public SectorEntity updateSector(@RequestBody SectorEntity sector) {
		return sectorService.updateSector(sector);
	}
	
	@DeleteMapping(value = "/sectors/{id}")
	public void deleteSector(@PathVariable int id) {
		sectorService.deleteSector(id);
	}
	
}
