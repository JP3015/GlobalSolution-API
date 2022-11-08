package com.backend.GS.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.backend.GS.model.Estacionamento;
import com.backend.GS.repository.EstacionamentoRepository;



@Service
public class EstacionamentoService {
    
    @Autowired
    EstacionamentoRepository repository;
    
    public Page<Estacionamento> listAll(Pageable paginacao){
        return repository.findAll(paginacao);
    }

    public List<Estacionamento> listAll() {
        return repository.findAll();
    }

    public void save(Estacionamento estacionamento) {
        repository.save(estacionamento);
    }

    public Optional<Estacionamento> getByCD(Long cd) {
        return repository.findById(cd);
    }

    public void deleteById(Long cd) {
        repository.deleteById(cd);
    }



}
