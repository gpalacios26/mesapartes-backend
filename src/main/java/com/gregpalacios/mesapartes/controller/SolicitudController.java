package com.gregpalacios.mesapartes.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.gregpalacios.mesapartes.dto.MailDTO;
import com.gregpalacios.mesapartes.model.Solicitud;
import com.gregpalacios.mesapartes.service.ISolicitudService;
import com.gregpalacios.mesapartes.util.EmailUtil;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@RequestMapping("/api/solicitudes")
@Tag(name = "Solicitud Controller", description = "Operaciones para el manejo de solicitudes")
public class SolicitudController {

	@Autowired
	private ISolicitudService service;

	@Autowired
	private EmailUtil emailUtil;

	@Operation(summary = "Listar todas las solicitudes")
	@ApiResponses(value = {
			@ApiResponse(responseCode = "200", description = "Lista recuperada exitosamente", content = @Content()),
			@ApiResponse(responseCode = "401", description = "No estas autorizado para ver este recurso", content = @Content()),
			@ApiResponse(responseCode = "403", description = "Está prohibido acceder al recurso que estaba tratando de alcanzar", content = @Content()),
			@ApiResponse(responseCode = "404", description = "No se encuentra el recurso que intentabas alcanzar", content = @Content()) })
	@GetMapping
	public ResponseEntity<List<Solicitud>> toList() throws Exception {
		List<Solicitud> lista = service.listar();
		return new ResponseEntity<List<Solicitud>>(lista, HttpStatus.OK);
	}

	@Operation(summary = "Registrar solicitud")
	@ApiResponses(value = {
			@ApiResponse(responseCode = "201", description = "Registrado con éxito.", content = @Content()),
			@ApiResponse(responseCode = "401", description = "No estas autorizado para acceder a este recurso", content = @Content()),
			@ApiResponse(responseCode = "403", description = "Está prohibido acceder al recurso", content = @Content()),
			@ApiResponse(responseCode = "404", description = "Recurso no encontrado", content = @Content()) })
	@PostMapping
	public ResponseEntity<Solicitud> toRegister(@Valid @RequestBody Solicitud data) throws Exception {
		UUID uuid = UUID.randomUUID();
		data.setUuid(uuid);
		Solicitud obj = service.registrar(data);
		return new ResponseEntity<Solicitud>(obj, HttpStatus.CREATED);
	}

	@Operation(summary = "Enviar correo de notificación")
	@PostMapping("/notificar")
	public ResponseEntity<Boolean> sendMail(@RequestParam("idSolicitud") String idSolicitud,
			@RequestParam("webUrl") String webUrl) throws Exception {

		Solicitud solicitud = service.listarPorId(Integer.parseInt(idSolicitud));

		Boolean rpta = false;
		if (solicitud != null) {
			MailDTO mailDTO = new MailDTO();
			mailDTO.setFrom("sistemas.pruebas.26@gmail.com");
			mailDTO.setTo(solicitud.getCorreo());
			mailDTO.setSubject("Mesa de Partes Virtual - Registro de Solicitud");

			Map<String, Object> model = new HashMap<>();
			String usuario = solicitud.getNombres() + " " + solicitud.getApePaterno() + " " + solicitud.getApeMaterno();
			model.put("usuario", usuario);
			model.put("codigo", solicitud.getUuid());
			model.put("webUrl", webUrl);
			mailDTO.setModel(model);

			emailUtil.enviarMail(mailDTO, "email/template-solicitud");

			rpta = true;
		}

		return new ResponseEntity<Boolean>(rpta, HttpStatus.OK);
	}

}
