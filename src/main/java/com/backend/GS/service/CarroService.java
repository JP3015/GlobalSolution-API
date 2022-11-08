package com.backend.GS.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.backend.GS.model.Carro;
import com.backend.GS.repository.CarroRepository;



@Service
public class CarroService {
    
    @Autowired
    CarroRepository repository;
    
    public Page<Carro> listAll(Pageable paginacao){
        return repository.findAll(paginacao);
    }

    public List<Carro> listAll() {
        return repository.findAll();
    }

    public void save(Carro carro) {
        repository.save(carro);
    }

    public Optional<Carro> getById(Long id) {
        return repository.findById(id);
    }

    public void deleteById(Long id) {
        repository.deleteById(id);
    }



}
