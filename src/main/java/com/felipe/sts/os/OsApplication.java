package com.felipe.sts.os;

import com.felipe.sts.os.Repository.ClienteRepository;
import com.felipe.sts.os.Repository.OSRepository;
import com.felipe.sts.os.Repository.TecnicoRepository;
import com.felipe.sts.os.domain.Cliente;
import com.felipe.sts.os.domain.OS;
import com.felipe.sts.os.domain.Tecnico;
import com.felipe.sts.os.domain.enuns.Prioridade;
import com.felipe.sts.os.domain.enuns.Status;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.Arrays;

@SpringBootApplication
public class OsApplication implements CommandLineRunner {
	@Autowired
	private TecnicoRepository tecnicoRepository;
	@Autowired
	private ClienteRepository clienteRepository;
	@Autowired
	private OSRepository osRepository;

	public static void main(String[] args) {

		SpringApplication.run(OsApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		Tecnico t1 = new Tecnico(null,"Felipe Ribeiro",
				"709.074.931-22","(62) 9944-7291");

		Cliente c1= new Cliente(null, "Betina Campos",
				"598.508.200-80","(62) 9944-7561");

		OS os1 = new OS(null, Prioridade.ALTA,
				"Teste create OS", Status.ANDAMENTO,t1,c1);

		t1.getList().add(os1);
		c1.getList().add(os1);

		tecnicoRepository.saveAll(Arrays.asList(t1));
		clienteRepository.saveAll(Arrays.asList(c1));
		osRepository.saveAll(Arrays.asList(os1));



	}
}
