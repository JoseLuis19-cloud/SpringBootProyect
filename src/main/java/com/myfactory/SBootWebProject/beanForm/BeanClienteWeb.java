package com.myfactory.SBootWebProject.beanForm;

import java.io.Serializable;
import java.util.Calendar;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Component;
import org.springframework.web.context.annotation.SessionScope;

@Component
@SessionScope
public class BeanClienteWeb  implements Serializable, Cloneable {
	
	private static final long serialVersionUID = 1L;

	private Integer idClienteWeb;
	
	@NotNull(message = "Debe introducir el nombre del cliente")
	private String nombreWeb;
	
	@NotNull(message = "Debe introducir el nombre del cliente")
	private String apellidosWeb;
	
    @DateTimeFormat(pattern = "dd/MM/yyyy")
    private Calendar fecNacimientoWeb;
    
    @DateTimeFormat(pattern = "dd/MM/yyyy")
    private Calendar fecAltaClienteWeb;

	private String DNIWeb;
	
	private String direccionWeb;
	
	private String paisWeb;
	
	@Email (message = "El formato de la dirección email no es correcto")
	private String dirEmailWeb;
	
	@Pattern(regexp="(^$|[0-9]{10})", message = "El teléfono tiene algún carácter no numérico y no es correcto" ) 
	private String telefonoWeb;
	
	private Integer idTpoCliente;
	
	// @Pattern(regexp="(^$|[0-9]{10})", message = "El formato del importe cliente no es correcto" ) 
	@Size(min = 1, max = 2, message = "El importe Cliente tiene mas de 2 numeros")
	private String impCliente;

	public BeanClienteWeb() {
	}
		
	public BeanClienteWeb(String nombre, String apellidos, Calendar fecNacimiento, Calendar fecAltaCliente, String dNI,
				String direccion, String pais, String dirEmail, String telefono, Integer idTpoCliente) {
		super();
		this.apellidosWeb = apellidos;
		this.fecNacimientoWeb = fecNacimiento;
		this.fecAltaClienteWeb = fecAltaCliente;
		this.DNIWeb = dNI;
		this.direccionWeb = direccion;
		this.paisWeb = pais;
		this.dirEmailWeb = dirEmail;
		this.telefonoWeb = telefono;
		this.idTpoCliente = idTpoCliente;
	}

		public Calendar getFecNacimientoWeb() {
		return fecNacimientoWeb;
		}

		public void setFecNacimientoWeb(Calendar fecNacimientoWeb) {
		this.fecNacimientoWeb = fecNacimientoWeb;
		}

		public Integer getIdClienteWeb() {
			return idClienteWeb;
		}

		public void setIdClienteWeb(Integer idClienteWeb) {
			this.idClienteWeb = idClienteWeb;
		}

		public String getNombreWeb() {
			return nombreWeb;
		}

		public void setNombreWeb(String nombreWeb) {
			this.nombreWeb = nombreWeb;
		}

		public String getApellidosWeb() {
			return apellidosWeb;
		}

		public void setApellidosWeb(String apellidosWeb) {
			this.apellidosWeb = apellidosWeb;
		}

		public String getDNIWeb() {
			return DNIWeb;
		}

		public void setDNIWeb(String dNIWeb) {
			DNIWeb = dNIWeb;
		}

		public String getDireccionWeb() {
			return direccionWeb;
		}

		public void setDireccionWeb(String direccionWeb) {
			this.direccionWeb = direccionWeb;
		}

		public String getPaisWeb() {
			return paisWeb;
		}

		public void setPaisWeb(String paisWeb) {
			this.paisWeb = paisWeb;
		}

		public String getDirEmailWeb() {
			return dirEmailWeb;
		}

		public void setDirEmailWeb(String dirEmailWeb) {
			this.dirEmailWeb = dirEmailWeb;
		}

		public String getTelefonoWeb() {
			return telefonoWeb;
		}

		public void setTelefonoWeb(String telefonoWeb) {
			this.telefonoWeb = telefonoWeb;
		}
		
		public Integer getIdTpoCliente() {
			return idTpoCliente;
		}

		public void setIdTpoCliente(Integer idTpoCliente) {
			this.idTpoCliente = idTpoCliente;
		}
		
		public String getImpCliente() {
			return impCliente;
		}

		public void setImpCliente(String impCliente) {
			this.impCliente = impCliente;
		}
		
		public Calendar getFecAltaClienteWeb() {
			return fecAltaClienteWeb;
		}

		public void setFecAltaClienteWeb(Calendar fecAltaClienteWeb) {
			this.fecAltaClienteWeb = fecAltaClienteWeb;
		}

		@Override
		protected Object clone() throws CloneNotSupportedException {
			BeanClienteWeb nuevoBeanClienteWeb = new BeanClienteWeb ();
		   return nuevoBeanClienteWeb;
		 }   
		
}
