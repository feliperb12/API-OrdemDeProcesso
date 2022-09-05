package com.felipe.sts.os.service;

import com.felipe.sts.os.Repository.TecnicoRepository;
import com.felipe.sts.os.domain.Tecnico;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class TecnicoService {


    @Autowired
    private TecnicoRepository repository;

    public Tecnico findById(Integer id){
        Optional<Tecnico> obj = repository.findById(id);
        return  obj.orElse(null);

    }
}
