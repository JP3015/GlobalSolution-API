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
import com.backend.GS.service.EstacionamentoService;

@RestController
@RequestMapping("/estacionamento")
public class EstacionamentoController {

	@Autowired
    private EstacionamentoService service;
	
    
    @GetMapping()
    @Cacheable("estacionamento")
    public Page<Estacionamento> index(@PageableDefault(size = 5) Pageable paginacao){
        return service.listAll(paginacao);
    }

    @PostMapping()
    public ResponseEntity<Estacionamento> create(@RequestBody @Valid Estacionamento estacionamento){
        service.save(estacionamento);
        
        return ResponseEntity
            .status(HttpStatus.CREATED)
            .body(estacionamento);
    }

    @GetMapping("{cd}")
    public ResponseEntity<Estacionamento> show(@PathVariable Long cd){
        return ResponseEntity.of(service.getByCD(cd));
    }

    @DeleteMapping("{cd}")
    @CacheEvict(value = "estacionamento", allEntries = true)
    public ResponseEntity<Object> destroy(@PathVariable Long cd){
        Optional<Estacionamento> optional = service.getByCD(cd);

        if(optional.isEmpty())
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();

        service.deleteById(cd);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();

    }

    @PutMapping("{cd}")
    public ResponseEntity<Estacionamento> update (@PathVariable Long cd, @RequestBody @Valid Estacionamento newEstacionamento){
        Optional<Estacionamento> optional = service.getByCD(cd);
        if(optional.isEmpty())
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();

			Estacionamento estacionamento = optional.get();
		newEstacionamento.setCodigo(cd);
        BeanUtils.copyProperties(newEstacionamento, estacionamento);

        service.save(estacionamento);

        return ResponseEntity.ok(estacionamento);
    }
}