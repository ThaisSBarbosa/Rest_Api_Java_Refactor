package br.com.alura.command;

import java.io.IOException;

import br.com.alura.client.ClientHttpConfiguration;
import br.com.alura.service.AbrigoService;
import br.com.alura.service.PetService;

public class ListarPetsCommand implements Command{
	@Override
	public void execute() {
		ClientHttpConfiguration client = new ClientHttpConfiguration();
		PetService petService = new PetService(client);
		try {
			petService.listarPets();
		} catch (IOException | InterruptedException e) {
			System.out.println(e.getMessage());
		}
	}
}
