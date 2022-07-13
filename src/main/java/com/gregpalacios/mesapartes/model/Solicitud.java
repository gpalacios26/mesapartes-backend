package com.gregpalacios.mesapartes.model;

import java.util.Date;
import java.util.UUID;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonFormat;

@Entity
@Table(name = "solicitud")
public class Solicitud {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer idSolicitud;
	
	@Column(name ="uuid", updatable = false, nullable = false)
	private UUID uuid;
	
	@Column(name = "nombres", nullable = false)
	private String nombres;
	
	@Column(name = "ape_paterno", nullable = false)
	private String apePaterno;
	
	@Column(name = "ape_materno", nullable = false)
	private String apeMaterno;
	
	@Column(name = "direccion", nullable = false)
	private String direccion;
	
	@Column(name = "correo", nullable = false)
	private String correo;
	
	@Column(name = "tel_movil", nullable = false)
	private String telMovil;
	
	@Column(name = "tel_fijo", nullable = true)
	private String telFijo;
	
	@Column(name = "id_tipo_doc", nullable = false)
	private Integer idTipoDoc;
	
	@Column(name = "tipo_doc", nullable = false)
	private String tipoDoc;
	
	@Column(name = "num_doc", nullable = false)
	private String numDoc;
	
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy", timezone = "America/Lima")
	@Column(name = "fecha_doc", nullable = false)
	private Date fechaDoc;
	
	@Column(name = "asunto_doc", nullable = false)
	private String asuntoDoc;
	
	@Column(name = "doc_adjunto", nullable = false)
	private String docAdjunto;
	
	@Column(name = "dec_jurada", nullable = false)
	private String decJurada;
	
	@Column(name = "estado_reg", nullable = false)
	private Integer estadoReg;
	
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy", timezone = "America/Lima")
	@Column(name = "fecha_reg", nullable = false)
	private Date fechaReg;

	public Integer getIdSolicitud() {
		return idSolicitud;
	}

	public void setIdSolicitud(Integer idSolicitud) {
		this.idSolicitud = idSolicitud;
	}

	public UUID getUuid() {
		return uuid;
	}

	public void setUuid(UUID uuid) {
		this.uuid = uuid;
	}

	public String getNombres() {
		return nombres;
	}

	public void setNombres(String nombres) {
		this.nombres = nombres;
	}

	public String getApePaterno() {
		return apePaterno;
	}

	public void setApePaterno(String apePaterno) {
		this.apePaterno = apePaterno;
	}

	public String getApeMaterno() {
		return apeMaterno;
	}

	public void setApeMaterno(String apeMaterno) {
		this.apeMaterno = apeMaterno;
	}

	public String getDireccion() {
		return direccion;
	}

	public void setDireccion(String direccion) {
		this.direccion = direccion;
	}

	public String getCorreo() {
		return correo;
	}

	public void setCorreo(String correo) {
		this.correo = correo;
	}

	public String getTelMovil() {
		return telMovil;
	}

	public void setTelMovil(String telMovil) {
		this.telMovil = telMovil;
	}

	public String getTelFijo() {
		return telFijo;
	}

	public void setTelFijo(String telFijo) {
		this.telFijo = telFijo;
	}

	public Integer getIdTipoDoc() {
		return idTipoDoc;
	}

	public void setIdTipoDoc(Integer idTipoDoc) {
		this.idTipoDoc = idTipoDoc;
	}

	public String getTipoDoc() {
		return tipoDoc;
	}

	public void setTipoDoc(String tipoDoc) {
		this.tipoDoc = tipoDoc;
	}

	public String getNumDoc() {
		return numDoc;
	}

	public void setNumDoc(String numDoc) {
		this.numDoc = numDoc;
	}

	public Date getFechaDoc() {
		return fechaDoc;
	}

	public void setFechaDoc(Date fechaDoc) {
		this.fechaDoc = fechaDoc;
	}

	public String getAsuntoDoc() {
		return asuntoDoc;
	}

	public void setAsuntoDoc(String asuntoDoc) {
		this.asuntoDoc = asuntoDoc;
	}

	public String getDocAdjunto() {
		return docAdjunto;
	}

	public void setDocAdjunto(String docAdjunto) {
		this.docAdjunto = docAdjunto;
	}

	public String getDecJurada() {
		return decJurada;
	}

	public void setDecJurada(String decJurada) {
		this.decJurada = decJurada;
	}

	public Integer getEstadoReg() {
		return estadoReg;
	}

	public void setEstadoReg(Integer estadoReg) {
		this.estadoReg = estadoReg;
	}

	public Date getFechaReg() {
		return fechaReg;
	}

	public void setFechaReg(Date fechaReg) {
		this.fechaReg = fechaReg;
	}

	@Override
	public String toString() {
		return "Solicitud [idSolicitud=" + idSolicitud + ", uuid=" + uuid + ", nombres=" + nombres + ", apePaterno="
				+ apePaterno + ", apeMaterno=" + apeMaterno + ", direccion=" + direccion + ", correo=" + correo
				+ ", telMovil=" + telMovil + ", telFijo=" + telFijo + ", idTipoDoc=" + idTipoDoc + ", tipoDoc="
				+ tipoDoc + ", numDoc=" + numDoc + ", fechaDoc=" + fechaDoc + ", asuntoDoc=" + asuntoDoc
				+ ", docAdjunto=" + docAdjunto + ", decJurada=" + decJurada + ", estadoReg=" + estadoReg + ", fechaReg="
				+ fechaReg + "]";
	}
	
}
