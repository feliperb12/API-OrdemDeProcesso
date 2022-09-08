package com.felipe.sts.os.service;

import com.felipe.sts.os.Repository.ClienteRepository;
import com.felipe.sts.os.domain.Cliente;
import com.felipe.sts.os.dtos.ClienteDTO;
import com.felipe.sts.os.service.exception.DataIntegratyViolationException;
import com.felipe.sts.os.service.exception.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ClienteService {


    @Autowired
    private ClienteRepository repository;

    public Cliente findById(Integer id){
        Optional<Cliente> obj = repository.findById(id);
        return  obj.orElseThrow(() -> new ObjectNotFoundException("Objeto não encontrado! ID: " +
                id + " Tipo: " + Cliente.class.getName()));

    }

    public List<Cliente> findAll() {
        return repository.findAll();
    }

    public Cliente create(ClienteDTO objDTO){
        if(findByCPF(objDTO) != null){
            throw new DataIntegratyViolationException("CPF já cadastrado na base de dados!");
        }
        return repository.save(new Cliente(null,
                objDTO.getNome(),
                objDTO.getCpf(),
                objDTO.getTelefone()));
    }
    public Cliente update(Integer id, ClienteDTO objDTO) {
        Cliente oldObj = findById(id);
        if(findByCPF(objDTO) !=null && findByCPF(objDTO).getId() != id){
            throw new DataIntegratyViolationException("CPF já cadastrado na base de dados!");
        }
        oldObj.setNome(objDTO.getNome());
        oldObj.setCpf(objDTO.getCpf());
        oldObj.setTelefone(objDTO.getTelefone());
        return repository.save(oldObj);
    }
    public void delete(Integer id) {
        Cliente obj = findById(id);
        if(obj.getList().size()>0){
            throw  new DataIntegratyViolationException("Pessoa possui ordem de serviço, não pode ser deletado!");
        }
        repository.deleteById(id);
    }
    private Cliente findByCPF(ClienteDTO objDTO){
        Cliente obj = repository.findByCPF(objDTO.getCpf());
        if(obj!=null){
            return obj;
        }
        return null;
    }

}
