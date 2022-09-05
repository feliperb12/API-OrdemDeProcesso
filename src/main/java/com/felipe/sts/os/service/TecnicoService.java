package com.felipe.sts.os.service;

import com.felipe.sts.os.Repository.TecnicoRepository;
import com.felipe.sts.os.domain.Tecnico;
import com.felipe.sts.os.service.exception.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TecnicoService {


    @Autowired
    private TecnicoRepository repository;

    public Tecnico findById(Integer id){
        Optional<Tecnico> obj = repository.findById(id);
        return  obj.orElseThrow(() -> new ObjectNotFoundException("Objeto não encontrado! ID: " +
                id + " Tipo: " + Tecnico.class.getName()));

    }

    public List<Tecnico> findAll() {
        return repository.findAll();
    }
}
