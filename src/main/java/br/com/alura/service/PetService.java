package br.com.alura.service;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;

import com.fasterxml.jackson.databind.ObjectMapper;
import br.com.alura.client.ClientHttpConfiguration;
import br.com.alura.domain.Pet;

public class PetService {

	private ClientHttpConfiguration client;
	
	public PetService(ClientHttpConfiguration client) {
		this.client = client;
	}

	public void listarPets() throws IOException, InterruptedException {
		System.out.println("Digite o id ou nome do abrigo:");
		String idOuNome = new Scanner(System.in).nextLine();

		String uri = "http://localhost:8080/abrigos/" + idOuNome + "/pets";
		
		var response =  client.dispararRequisicaoGet(uri);
		
		int statusCode = response.statusCode();
		if (statusCode == 404 || statusCode == 500) {
			System.out.println("ID ou nome não cadastrado!");
			//continue;
		}
		String responseBody = response.body();
		Pet[] pets = new ObjectMapper().readValue(responseBody, Pet[].class);
		System.out.println("Pets cadastrados:");
		for (Pet pet : pets) {
			System.out.println(pet.getId() + " - " + pet.getTipo() + " - " + pet.getNome() + 
					" - " + pet.getRaca() + " - " + pet.getIdade() + " ano(s)");
		}
	}

	public void importarPets(String idOuNome, String nomeArquivo) throws NumberFormatException, IOException, InterruptedException {
		BufferedReader reader = null;
		try {
			reader = new BufferedReader(new FileReader(nomeArquivo));
		} catch (IOException e) {
			System.out.println("Erro ao carregar o arquivo: " + nomeArquivo);
			//continue;
		}
		String line;
		while ((line = reader.readLine()) != null) {
			String[] campos = line.split(",");
			String tipo = campos[0].toUpperCase();
			String nome = campos[1];
			String raca = campos[2];
			int idade = Integer.parseInt(campos[3]);
			String cor = campos[4];
			Float peso = Float.parseFloat(campos[5]);

			Pet pet = new Pet(tipo, nome, raca, idade, cor, peso);

			String uri = "http://localhost:8080/abrigos/" + idOuNome + "/pets";

			var response = client.dispararRequisicaoPost(uri, pet);
			
			int statusCode = response.statusCode();
			String responseBody = response.body();
			if (statusCode == 200) {
				System.out.println("Pet cadastrado com sucesso: " + nome);
			} else if (statusCode == 404) {
				System.out.println("Id ou nome do abrigo não encontado!");
				break;
			} else if (statusCode == 400 || statusCode == 500) {
				System.out.println("Erro ao cadastrar o pet: " + nome);
				System.out.println(responseBody);
				break;
			}
		}
		reader.close();
	}
}
