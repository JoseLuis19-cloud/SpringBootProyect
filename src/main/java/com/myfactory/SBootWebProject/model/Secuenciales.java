package com.myfactory.SBootWebProject.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="SECUENCIALES")
public class Secuenciales implements Serializable {
		
	private static final long serialVersionUID = 1L;
		
		@Id
		@GeneratedValue(strategy = GenerationType.IDENTITY)
	    @Column(name="ID_SECUENCIAL",  nullable=false, unique=false)
	    private Integer idSecuencial;
	    
	    @Column(name="NOM_TABLA",  nullable=false, unique=false)
	    private String nomSecuecial;
	    
	    @Column(name="ANYO_CURSO",  nullable=false, unique=false)
	    private String anyoCurso;

	    @Column(name="IDENTIFICADOR_UNICO",  nullable=false, unique=false)
	    private String identificadorUnico;

		public Integer getIdSecuencial() {
			return idSecuencial;
		}

		public void setIdSecuencial(Integer idSecuencial) {
			this.idSecuencial = idSecuencial;
		}

		public String getNomSecuecial() {
			return nomSecuecial;
		}

		public void setNomSecuecial(String nomSecuecial) {
			this.nomSecuecial = nomSecuecial;
		}

		public String getAnyoCurso() {
			return anyoCurso;
		}

		public void setAnyoCurso(String anyoCurso) {
			this.anyoCurso = anyoCurso;
		}

		public String getIdentificadorUnico() {
			return identificadorUnico;
		}

		public void setIdentificadorUnico(String identificadorUnico) {
			this.identificadorUnico = identificadorUnico;
		}
	    
}
