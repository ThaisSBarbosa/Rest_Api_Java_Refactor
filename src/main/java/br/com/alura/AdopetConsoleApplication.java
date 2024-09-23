package br.com.alura;

import br.com.alura.command.CadastrarAbrigoCommand;
import br.com.alura.command.CommandExecutor;
import br.com.alura.command.ImportarPetsCommand;
import br.com.alura.command.ListarAbrigosCommand;
import br.com.alura.command.ListarPetsCommand;

import java.util.Scanner;

public class AdopetConsoleApplication {

	public static void main(String[] args) {
		CommandExecutor commandExecutor = new CommandExecutor();

		try {
			int opcaoEscolhida = 0;
			while (opcaoEscolhida != 5) {
				exibeMenu();

				String textoDigitado = new Scanner(System.in).nextLine();
				opcaoEscolhida = Integer.parseInt(textoDigitado);

				switch (opcaoEscolhida) {
				case 1 -> commandExecutor.executeCommand(new ListarAbrigosCommand());
				case 2 -> commandExecutor.executeCommand(new CadastrarAbrigoCommand());
				case 3 -> commandExecutor.executeCommand(new ListarPetsCommand());
				case 4 -> {
					System.out.println("Digite o id ou nome do abrigo:");
					String idOuNome = new Scanner(System.in).nextLine();
					System.out.println("Digite o nome do arquivo CSV:");
					String nomeArquivo = new Scanner(System.in).nextLine();

					commandExecutor.executeCommand(new ImportarPetsCommand(idOuNome, nomeArquivo));
				}
				case 5 -> System.exit(0);
				default -> throw new IllegalArgumentException("NÚMERO INVÁLIDO! " + opcaoEscolhida);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	private static void exibeMenu() {
		System.out.println("##### BOAS VINDAS AO SISTEMA ADOPET CONSOLE #####");
		System.out.println("\nDIGITE O NÚMERO DA OPERAÇÃO DESEJADA:");
		System.out.println("1 -> Listar abrigos cadastrados");
		System.out.println("2 -> Cadastrar novo abrigo");
		System.out.println("3 -> Listar pets do abrigo");
		System.out.println("4 -> Importar pets do abrigo");
		System.out.println("5 -> Sair");
	}
}
