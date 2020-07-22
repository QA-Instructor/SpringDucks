package com.qa.duck.rest;

import java.util.List;

import javax.websocket.server.PathParam;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.qa.duck.dto.PondDTO;
import com.qa.duck.persistence.domain.Duck;
import com.qa.duck.persistence.domain.Pond;
import com.qa.duck.service.PondService;

@RestController
@RequestMapping("/pond")
public class PondController {

	private PondService service;

	@Autowired
	public PondController(PondService service) {
		super();
		this.service = service;
	}

	@PostMapping("/createPond")
	public ResponseEntity<PondDTO> createPond(@RequestBody Pond pond) {
		return new ResponseEntity<PondDTO>(this.service.createPond(pond), HttpStatus.CREATED);
	}

	@DeleteMapping("/deletePond/{id}")
	public ResponseEntity<PondDTO> deletePond(@PathVariable Long id) {
		return this.service.deletePond(id) ? new ResponseEntity<PondDTO>(HttpStatus.NO_CONTENT)
				: new ResponseEntity<PondDTO>(HttpStatus.INTERNAL_SERVER_ERROR);
	}

	@GetMapping("/get/{id}")
	public ResponseEntity<PondDTO> getPond(@PathVariable Long id) {
		return ResponseEntity.ok(this.service.findPondByID(id));
	}

	@GetMapping("/getAll")
	public ResponseEntity<List<PondDTO>> getAllPonds() {
		return ResponseEntity.ok(this.service.readPonds());
	}

	@PutMapping("/updatePond")
	public ResponseEntity<PondDTO> updatePond(@PathParam("id") Long id, @RequestBody Pond pond) {
		return new ResponseEntity<PondDTO>(this.service.updatePond(pond, id), HttpStatus.ACCEPTED);
	}

	@PatchMapping("/update/{id}")
	public ResponseEntity<PondDTO> addPondToPond(@PathVariable Long id, @RequestBody Duck duck) {
		return new ResponseEntity<PondDTO>(this.service.addDuckToPond(id, duck), HttpStatus.ACCEPTED);
	}

}
