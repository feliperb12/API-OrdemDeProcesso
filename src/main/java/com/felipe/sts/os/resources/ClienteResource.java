package com.felipe.sts.os.resources;

import com.felipe.sts.os.domain.Cliente;
import com.felipe.sts.os.dtos.ClienteDTO;
import com.felipe.sts.os.service.ClienteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;
import java.util.List;
import java.util.stream.Collectors;

@CrossOrigin("*")
@RestController
@RequestMapping(value = "/clientes")
public class ClienteResource {

    @Autowired
    private ClienteService service;

    @GetMapping(value = "/{id}")
    public ResponseEntity<ClienteDTO> findById(@PathVariable Integer id) {
        ClienteDTO objDTO = new ClienteDTO(service.findById(id));
        return ResponseEntity.ok().body(objDTO);
    }

    @GetMapping
    public ResponseEntity<List<ClienteDTO>> findAll() {
        List<ClienteDTO> listDTO = service.findAll().
                stream().map(obj -> new ClienteDTO(obj)).collect(Collectors.toList());
//        List<Cliente> list = service.findAll();
//        List<ClienteDTO> listDTO = new ArrayList<>();
//        list.forEach(obj -> listDTO.add(new ClienteDTO(obj)));
        return ResponseEntity.ok().body(listDTO);
     }

     @PostMapping
     public ResponseEntity<ClienteDTO> create(@Valid @RequestBody ClienteDTO objDTO){
        Cliente newObj= service.create(objDTO);
         URI uri = ServletUriComponentsBuilder
                 .fromCurrentRequest().path("/{id}").buildAndExpand(newObj.getId()).toUri();
         return ResponseEntity.created(uri).build();
     }
     @PutMapping("/{id}")
     public ResponseEntity<ClienteDTO> update(@PathVariable Integer id,@Valid @RequestBody ClienteDTO objDTO){
        ClienteDTO newObj = new ClienteDTO(service.update(id, objDTO));
        return ResponseEntity.ok().body(newObj);
     }
     @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Integer id){
        service.delete(id);
        return ResponseEntity.noContent().build();

     }
}
