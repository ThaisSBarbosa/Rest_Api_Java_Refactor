package br.com.alura.command;

import java.io.IOException;

import br.com.alura.client.ClientHttpConfiguration;
import br.com.alura.service.AbrigoService;

public class ListarAbrigosCommand implements Command {

	@Override
	public void execute() {
		ClientHttpConfiguration client = new ClientHttpConfiguration();
		AbrigoService abrigoService = new AbrigoService(client);
		try {
			abrigoService.listarAbrigos();
		} catch (IOException | InterruptedException e) {
			System.out.println(e.getMessage());
		}
	}
}
