package com.felipe.sts.os.resources;

import com.felipe.sts.os.domain.Tecnico;
import com.felipe.sts.os.dtos.TecnicosDTO;
import com.felipe.sts.os.service.TecnicoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/tecnicos")
public class TecnicoResource {

    @Autowired
    private TecnicoService service;

    @GetMapping(value = "/{id}")
    public ResponseEntity<TecnicosDTO> findById(@PathVariable Integer id){
        TecnicosDTO objDTO = new TecnicosDTO(service.findById(id));

        return ResponseEntity.ok().body(objDTO);

    }
}
