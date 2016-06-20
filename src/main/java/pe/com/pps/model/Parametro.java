package pe.com.pps.model;

import javax.persistence.*;

@Entity
@IdClass(ParametroPK.class)
@Table(name = "lval")
public class Parametro {

	@Id
	@Column(name = "codlval")
	Integer codigo;
	@Id
	@Column(name = "tipolval")
	String tipo;
	@Column(name = "valor")
	String valor;

	public Integer getCodigo() {
		return codigo;
	}

	public String getTipo() {
		return tipo;
	}

	public String getValor() {
		return valor;
	}

	public Double getValorDouble() {
		return Double.valueOf(getValor());
	}

	public void setCodigo(Integer codigo) {
		this.codigo = codigo;
	}

	public void setTipo(String tipo) {
		this.tipo = tipo;
	}

	public void setValor(String valor) {
		this.valor = valor;
	}

}
