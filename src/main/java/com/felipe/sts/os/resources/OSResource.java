package com.felipe.sts.os.resources;

import com.felipe.sts.os.dtos.OSDTO;
import com.felipe.sts.os.service.OsService;
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
@RequestMapping("/os")
public class OSResource {

    @Autowired
    private OsService service;

    @GetMapping("/{id}")
    public ResponseEntity<OSDTO> findById(@PathVariable Integer id){
        OSDTO obj = new OSDTO(service.findById(id));
        return ResponseEntity.ok().body(obj);
    }
    @GetMapping
    public ResponseEntity<List<OSDTO>> findAll(){
        List<OSDTO> list = service.findAll()
                .stream().map(obj -> new OSDTO(obj)).collect(Collectors.toList());
        return ResponseEntity.ok().body(list);
    }
    @PostMapping
    public ResponseEntity<OSDTO> create(@Valid @RequestBody OSDTO obj){
        obj = new OSDTO(service.create(obj));
        URI URI = ServletUriComponentsBuilder.
                fromCurrentRequest().path("/id").buildAndExpand(obj.getId()).toUri();
        return ResponseEntity.created(URI).build();
    }

    @PutMapping()
    public ResponseEntity<OSDTO> update(@Valid @RequestBody OSDTO obj){
        obj = new OSDTO(service.update(obj));
        return ResponseEntity.ok().body(obj);
    }
}
