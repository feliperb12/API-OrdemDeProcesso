package com.felipe.sts.os.service;

import com.felipe.sts.os.Repository.TecnicoRepository;
import com.felipe.sts.os.domain.Tecnico;
import com.felipe.sts.os.dtos.TecnicosDTO;
import com.felipe.sts.os.service.exception.DataIntegratyViolationException;
import com.felipe.sts.os.service.exception.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
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

    public Tecnico create(TecnicosDTO objDTO){
        if(findByCPF(objDTO) != null){
            throw new DataIntegratyViolationException("CPF já cadastrado na base de dados!");
        }
        return repository.save(new Tecnico(null,
                objDTO.getNome(),
                objDTO.getCpf(),
                objDTO.getTelefone()));
    }
    public Tecnico update(Integer id, TecnicosDTO objDTO) {
        Tecnico oldObj = findById(id);

        if(findByCPF(objDTO) !=null && findByCPF(objDTO).getId() != id){
            throw new DataIntegratyViolationException("CPF já cadastrado na base de dados!");
        }
        oldObj.setNome(objDTO.getNome());
        oldObj.setCpf(objDTO.getCpf());
        oldObj.setTelefone(objDTO.getTelefone());
        return repository.save(oldObj);

    }
    private Tecnico findByCPF(TecnicosDTO objDTO){
        Tecnico obj = repository.findByCPF(objDTO.getCpf());
        if(obj!=null){
            return obj;
        }
        return null;
    }

}
