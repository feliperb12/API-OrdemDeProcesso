package com.felipe.sts.os.service;

import com.felipe.sts.os.Repository.OSRepository;
import com.felipe.sts.os.domain.OS;
import com.felipe.sts.os.service.exception.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class OsService {

    @Autowired
    private OSRepository repository;

    public OS findById(Integer id){
        Optional<OS> obj = repository.findById(id);
        return obj.orElseThrow(() ->
                new ObjectNotFoundException("Objeto não encontrado! ID: " + id + ".  Tipo: "+ OS.class.getName()));
    }
}
