package br.com.alura.command;

import java.io.IOException;

import br.com.alura.client.ClientHttpConfiguration;
import br.com.alura.service.AbrigoService;

public class CadastrarAbrigoCommand implements Command{
	@Override
	public void execute() {
		ClientHttpConfiguration client = new ClientHttpConfiguration();
		AbrigoService abrigoService = new AbrigoService(client);
		try {
			abrigoService.cadastrarAbrigo();
		} catch (IOException | InterruptedException e) {
			System.out.println(e.getMessage());
		}
	}
}
