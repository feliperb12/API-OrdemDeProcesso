package com.felipe.sts.os.resources;

import com.felipe.sts.os.domain.Tecnico;
import com.felipe.sts.os.dtos.TecnicosDTO;
import com.felipe.sts.os.service.TecnicoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping(value = "/tecnicos")
public class TecnicoResource {

    @Autowired
    private TecnicoService service;

    @GetMapping(value = "/{id}")
    public ResponseEntity<TecnicosDTO> findById(@PathVariable Integer id) {
        TecnicosDTO objDTO = new TecnicosDTO(service.findById(id));
        return ResponseEntity.ok().body(objDTO);
    }

    @GetMapping
    public ResponseEntity<List<TecnicosDTO>> findAll() {
        List<TecnicosDTO> listDTO = service.findAll().
                stream().map(obj -> new TecnicosDTO(obj)).collect(Collectors.toList());
//        List<Tecnico> list = service.findAll();
//        List<TecnicosDTO> listDTO = new ArrayList<>();
//        list.forEach(obj -> listDTO.add(new TecnicosDTO(obj)));
        return ResponseEntity.ok().body(listDTO);
     }

     @PostMapping
     public ResponseEntity<TecnicosDTO> create(@Valid @RequestBody TecnicosDTO objDTO){
        Tecnico newObj= service.create(objDTO);
         URI uri = ServletUriComponentsBuilder
                 .fromCurrentRequest().path("/{id}").buildAndExpand(newObj.getId()).toUri();
         return ResponseEntity.created(uri).build();
     }
     @PutMapping("/{id}")
     public ResponseEntity<TecnicosDTO> update(@PathVariable Integer id,@Valid @RequestBody TecnicosDTO objDTO){
        TecnicosDTO newObj = new TecnicosDTO(service.update(id, objDTO));
        return ResponseEntity.ok().body(newObj);
     }
     @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Integer id){
        service.delete(id);
        return ResponseEntity.noContent().build();

     }
}
