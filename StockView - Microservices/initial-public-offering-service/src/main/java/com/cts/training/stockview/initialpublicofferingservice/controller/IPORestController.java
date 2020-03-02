package com.cts.training.stockview.initialpublicofferingservice.controller;

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

import com.cts.training.stockview.initialpublicofferingservice.entity.IPOEntity;
import com.cts.training.stockview.initialpublicofferingservice.service.IPOService;

@CrossOrigin(origins = "*")
@RestController
public class IPORestController {

	@Autowired
	private IPOService ipoService;

	@GetMapping(value = "/ipos", produces = "application/json")
	public ResponseEntity<?> getAllIPOs() {
		List<IPOEntity> ipos = ipoService.getAllIPOs();
		if (ipos.size() > 0) {
			return new ResponseEntity<List<IPOEntity>>(ipos, HttpStatus.OK);
		} else
			return new ResponseEntity<String>("no IPOs found", HttpStatus.NOT_FOUND);
	}

	@GetMapping(value = "/ipos/{id}", produces = "application/json")
	public IPOEntity getIPOById(@PathVariable("id") int id) {
		IPOEntity ipo = ipoService.getIPOById(id);
		return ipo;
	}

	@PostMapping(value = "/ipos", consumes = "application/json")
	public IPOEntity addIPO(@RequestBody IPOEntity ipo) {
		return ipoService.addIPO(ipo);
	}

	@PutMapping(value = "/ipos", consumes = "application/json")
	public IPOEntity updateIPO(@RequestBody IPOEntity ipo) {
		return ipoService.updateIPO(ipo);
	}

	@DeleteMapping(value = "/ipos/{id}")
	public void deleteIPO(@PathVariable int id) {
		ipoService.deleteIPO(id);
	}

}
