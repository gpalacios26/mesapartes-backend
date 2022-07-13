package com.gregpalacios.mesapartes.controller;

import static org.junit.jupiter.api.Assertions.*;

import java.util.Arrays;
import java.util.Date;
import java.util.List;

import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;

import com.gregpalacios.mesapartes.model.Solicitud;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
class SolicitudControllerTest {

	@Autowired
	private TestRestTemplate client;

	@LocalServerPort
	private int puerto;

	@Test
	@Order(1)
	void testToList() {
		ResponseEntity<Solicitud[]> respuesta = client.getForEntity(crearUri("/api/solicitudes"), Solicitud[].class);
		List<Solicitud> solicitudes = Arrays.asList(respuesta.getBody());

		assertEquals(HttpStatus.OK, respuesta.getStatusCode());
		assertEquals(MediaType.APPLICATION_JSON, respuesta.getHeaders().getContentType());
		assertNotNull(solicitudes);
	}

	@Test
	@Order(2)
	void testToRegister() {
		Solicitud solicitud = new Solicitud();
		solicitud.setIdSolicitud(null);
		solicitud.setNombres("Pedro");
		solicitud.setApePaterno("Torres");
		solicitud.setApeMaterno("Maldonado");
		solicitud.setDireccion("Calle Berlín 140 - Ate");
		solicitud.setCorreo("ptorrres@gmail.com");
		solicitud.setTelMovil("985632143");
		solicitud.setTelFijo("3541256");
		solicitud.setIdTipoDoc(1);
		solicitud.setTipoDoc("SOLICITUD");
		solicitud.setNumDoc("1248");
		solicitud.setFechaDoc(new Date());
		solicitud.setAsuntoDoc("Esta es una solicitud");
		solicitud.setDocAdjunto("file-1656517335782.pdf");
		solicitud.setDecJurada("SI");
		solicitud.setEstadoReg(1);
		solicitud.setFechaReg(new Date());

		ResponseEntity<Solicitud> respuesta = client.postForEntity(crearUri("/api/solicitudes"), solicitud,
				Solicitud.class);
		Solicitud solicitudCreada = respuesta.getBody();

		assertEquals(HttpStatus.CREATED, respuesta.getStatusCode());
		assertEquals(MediaType.APPLICATION_JSON, respuesta.getHeaders().getContentType());
		assertNotNull(solicitudCreada);
	}

	@Test
	@Order(3)
	void testSendMail() {
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);

		MultiValueMap<String, String> params = new LinkedMultiValueMap<String, String>();
		params.add("idSolicitud", "1");
		params.add("webUrl", "http://localhost:4200");

		HttpEntity<MultiValueMap<String, String>> request = new HttpEntity<MultiValueMap<String, String>>(params,
				headers);

		ResponseEntity<Boolean> respuesta = client.postForEntity(crearUri("/api/solicitudes/notificar"), request,
				Boolean.class);

		assertEquals(HttpStatus.OK, respuesta.getStatusCode());
		assertTrue(respuesta.hasBody());
	}

	private String crearUri(String uri) {
		return "http://localhost:" + puerto + uri;
	}

}
