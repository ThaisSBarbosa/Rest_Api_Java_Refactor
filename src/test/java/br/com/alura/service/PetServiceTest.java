package br.com.alura.service;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.atLeast;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.net.http.HttpResponse;

import org.junit.jupiter.api.Test;

import br.com.alura.client.ClientHttpConfiguration;

class PetServiceTest {

	private ClientHttpConfiguration client = mock(ClientHttpConfiguration.class);
	private PetService petService = new PetService(client);
	private HttpResponse<String> response = mock(HttpResponse.class);

	@Test
	public void deveVerificarSeDispararRequisicaoPostSeraChamado() throws IOException, InterruptedException {
		when(client.dispararRequisicaoPost(anyString(), any())).thenReturn(response);

		petService.importarPets("1", "pets.csv");

		verify(client.dispararRequisicaoPost(anyString(), anyString()), atLeast(1));
	}
}
