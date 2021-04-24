package com.myfactory.SBootWebProject.beanForm;

import java.io.Serializable;
import java.util.Calendar;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Component;
import org.springframework.web.context.annotation.SessionScope;

@Component
@SessionScope
public class BeanEmpresaWeb  implements Serializable , Cloneable
 {
	private static final long serialVersionUID = 1L;

	private Integer idEmpresaWeb;
	private String nomEmpresaWeb;
	private String direcionWeb;
	private String CIFWeb;
	private Integer codProvinciaWeb;
	private String codPostalWeb;

	private String telefonoWeb;
	private String nomContacto1Web;
	private String telefContacto1Web;
	private String emailContacto1Web;
	private String nomContacto2Web;
	private String telefContacto2Web;
	private String emailContacto2Web; 
	
	@DateTimeFormat(pattern = "dd/MM/yyyy")
	private Calendar fecAltaEmpresaWeb;
	
	private String emailEmpresaWeb;

	public BeanEmpresaWeb()
	{}
 
	public BeanEmpresaWeb(Integer idEmpresaWeb, String nomEmpresaWeb, 
			String direcionWeb, 
			Integer codProvinciaWeb,
			String codPostalWeb,
			String telefonoWeb,
			String nomContacto1Web,
			String telefContacto1Web,
			String emailContacto1Web,
			String nomContacto2Web,
			String telefContacto2Web,
			String emailContacto2Web,
			Calendar fecAltaEmpresaWeb,
			String emailEmpresaWeb)
		{
		 this.idEmpresaWeb = idEmpresaWeb;
		 this.nomEmpresaWeb = nomEmpresaWeb;
		 this.direcionWeb = direcionWeb;
		 this.codProvinciaWeb = codProvinciaWeb;
		 this.codPostalWeb = codPostalWeb;
		 this.telefonoWeb = telefonoWeb;
		 this.nomContacto1Web = nomContacto1Web;
		 this.telefContacto1Web = telefContacto1Web;
		 this.emailContacto1Web = emailContacto1Web;
		 this.nomContacto2Web =  nomContacto2Web;
		 this.telefContacto2Web = telefContacto2Web;
		 this.emailContacto2Web = emailContacto2Web;
		 this.fecAltaEmpresaWeb = fecAltaEmpresaWeb;
		 this.emailEmpresaWeb = emailEmpresaWeb; 
		}

	public Integer getIdEmpresaWeb() {
		return idEmpresaWeb;
	}

	public void setIdEmpresaWeb(Integer idEmpresaWeb) {
		this.idEmpresaWeb = idEmpresaWeb;
	}

	public String getNomEmpresaWeb() {
		return nomEmpresaWeb;
	}

	public void setNomEmpresaWeb(String nomEmpresaWeb) {
		this.nomEmpresaWeb = nomEmpresaWeb;
	}

	public String getDirecionWeb() {
		return direcionWeb;
	}

	public void setDirecionWeb(String direcionWeb) {
		this.direcionWeb = direcionWeb;
	}

	public Integer getCodProvinciaWeb() {
		return codProvinciaWeb;
	}

	public void setCodProvinciaWeb(Integer codProvinciaWeb) {
		this.codProvinciaWeb = codProvinciaWeb;
	}

	public String getCodPostalWeb() {
		return codPostalWeb;
	}

	public void setCodPostalWeb(String codPostalWeb) {
		this.codPostalWeb = codPostalWeb;
	}

	public String getTelefonoWeb() {
		return telefonoWeb;
	}

	public void setTelefonoWeb(String telefonoWeb) {
		this.telefonoWeb = telefonoWeb;
	}

	public String getNomContacto1Web() {
		return nomContacto1Web;
	}

	public void setNomContacto1Web(String nomContacto1Web) {
		this.nomContacto1Web = nomContacto1Web;
	}

	public String getTelefContacto1Web() {
		return telefContacto1Web;
	}

	public void setTelefContacto1Web(String telefContacto1Web) {
		this.telefContacto1Web = telefContacto1Web;
	}

	public String getEmailContacto1Web() {
		return emailContacto1Web;
	}

	public void setEmailContacto1Web(String emailContacto1Web) {
		this.emailContacto1Web = emailContacto1Web;
	}

	public String getNomContacto2Web() {
		return nomContacto2Web;
	}

	public void setNomContacto2Web(String nomContacto2Web) {
		this.nomContacto2Web = nomContacto2Web;
	}

	public String getTelefContacto2Web() {
		return telefContacto2Web;
	}

	public void setTelefContacto2Web(String telefContacto2Web) {
		this.telefContacto2Web = telefContacto2Web;
	}

	public String getEmailContacto2Web() {
		return emailContacto2Web;
	}

	public void setEmailContacto2Web(String emailContacto2Web) {
		this.emailContacto2Web = emailContacto2Web;
	}

	public Calendar getFecAltaEmpresaWeb() {
		return fecAltaEmpresaWeb;
	}

	public void setFecAltaEmpresaWeb(Calendar fecAltaEmpresaWeb) {
		this.fecAltaEmpresaWeb = fecAltaEmpresaWeb;
	}
	
	public String getCIFWeb() {
		return CIFWeb;
	}

	public void setCIFWeb(String cIFWeb) {
		CIFWeb = cIFWeb;
	}
	
	public String getEmailEmpresaWeb() {
		return emailEmpresaWeb;
	}

	public void setEmailEmpresaWeb(String emailEmpresaWeb) {
		this.emailEmpresaWeb = emailEmpresaWeb;
	}
	}
