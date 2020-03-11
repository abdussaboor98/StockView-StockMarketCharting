package com.cts.training.stockview.sectorservice.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
	public ResponseEntity<?> getAllSectors(){
		List<SectorEntity> sectors = sectorService.getAllSectors();
		if(sectors.size()>0) {
			return new ResponseEntity<List<SectorEntity>>(sectors,HttpStatus.OK);
		}
		else {
			return new ResponseEntity<String>("No sectors found", HttpStatus.BAD_REQUEST);
		}
	}
	
	@GetMapping(value = "/sectors/{id}", produces = "application/json")
	public ResponseEntity<?> getSectorById(@PathVariable("id") int id){
		try {
			SectorEntity sector = sectorService.getSectorById(id);
			return new ResponseEntity<SectorEntity>(sector,HttpStatus.OK);
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<String>("No sector found", HttpStatus.BAD_REQUEST);
		}
		
	}
	
	@PostMapping(value = "/sectors/admin",consumes = "application/json")
	public ResponseEntity<?> addSector(@RequestBody SectorEntity sector) {
		return new ResponseEntity<SectorEntity>(sectorService.addSector(sector),HttpStatus.OK);
	}
	
	@PutMapping(value = "/sectors/admin",consumes = "application/json")
	public ResponseEntity<?> updateSector(@RequestBody SectorEntity sector) {
		return new ResponseEntity<SectorEntity>(sectorService.updateSector(sector),HttpStatus.OK);
	}
	
	@DeleteMapping(value = "/sectors/admin/{id}")
	public ResponseEntity<?> deleteSector(@PathVariable int id) {
		sectorService.deleteSector(id);
		return new ResponseEntity<HttpStatus>(HttpStatus.NO_CONTENT);
	}
	
}
