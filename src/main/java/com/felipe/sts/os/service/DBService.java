package com.felipe.sts.os.service;

import com.felipe.sts.os.Repository.ClienteRepository;
import com.felipe.sts.os.Repository.OSRepository;
import com.felipe.sts.os.Repository.TecnicoRepository;
import com.felipe.sts.os.domain.Cliente;
import com.felipe.sts.os.domain.OS;
import com.felipe.sts.os.domain.Tecnico;
import com.felipe.sts.os.domain.enuns.Prioridade;
import com.felipe.sts.os.domain.enuns.Status;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Arrays;

@Service
public class DBService {
    @Autowired
    private TecnicoRepository tecnicoRepository;
    @Autowired
    private ClienteRepository clienteRepository;
    @Autowired
    private OSRepository osRepository;


    public void instanciaDB() {
        Tecnico t1 = new Tecnico(null, "Felipe Ribeiro",
                "709.074.931-22", "(62) 9944-7291");

        Cliente c1 = new Cliente(null, "Betina Campos",
                "598.508.200-80", "(62) 9944-7561");
        Cliente c2 = new Cliente(null, "Cleitin Campos",
                "085.676.120-63", "(62) 9932-7561");

        OS os1 = new OS(null, Prioridade.ALTA,
                "Teste create OS", Status.ANDAMENTO, t1, c1);

        t1.getList().add(os1);
        c1.getList().add(os1);

        tecnicoRepository.saveAll(Arrays.asList(t1));
        clienteRepository.saveAll(Arrays.asList(c1,c2));
        osRepository.saveAll(Arrays.asList(os1));
    }
}
