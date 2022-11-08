package com.backend.GS.controller;

import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.backend.GS.model.*;
import com.backend.GS.service.CarroService;

@RestController
@RequestMapping("/carro")
public class CarroController {

	@Autowired
    private CarroService service;
	
    
    @GetMapping()
    @Cacheable("carro")
    public Page<Carro> index(@PageableDefault(size = 5) Pageable paginacao){
        return service.listAll(paginacao);
    }

    @PostMapping()
    public ResponseEntity<Carro> create(@RequestBody @Valid Carro carro){
        service.save(carro);
        
        return ResponseEntity
            .status(HttpStatus.CREATED)
            .body(carro);
    }

    @GetMapping("{id}")
    public ResponseEntity<Carro> show(@PathVariable Long id){
        return ResponseEntity.of(service.getById(id));
    }

    @DeleteMapping("{id}")
    @CacheEvict(value = "carro", allEntries = true)
    public ResponseEntity<Object> destroy(@PathVariable Long id){
        Optional<Carro> optional = service.getById(id);

        if(optional.isEmpty())
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();

        service.deleteById(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();

    }

    @PutMapping("{id}")
    public ResponseEntity<Carro> update (@PathVariable Long id, @RequestBody @Valid Carro newCarro){
        Optional<Carro> optional = service.getById(id);
        if(optional.isEmpty())
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();

			Carro carro = optional.get();
		newCarro.setId(id);
        BeanUtils.copyProperties(newCarro, carro);

        service.save(carro);

        return ResponseEntity.ok(carro);
    }
}