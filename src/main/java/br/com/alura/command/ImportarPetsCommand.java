package br.com.alura.command;

import java.io.IOException;

import br.com.alura.client.ClientHttpConfiguration;
import br.com.alura.service.AbrigoService;
import br.com.alura.service.PetService;

public class ImportarPetsCommand implements Command{
	private String idOuNome;
	private String nomeArquivo;
	
	public ImportarPetsCommand(String idOuNome, String nomeArquivo) {
		this.idOuNome = idOuNome;
		this.nomeArquivo = nomeArquivo;
	}

	@Override
	public void execute() {
		ClientHttpConfiguration client = new ClientHttpConfiguration();
		PetService petService = new PetService(client);
		try {
			petService.importarPets(idOuNome, nomeArquivo);
		} catch (IOException | InterruptedException e) {
			System.out.println(e.getMessage());
		}
	}
}
