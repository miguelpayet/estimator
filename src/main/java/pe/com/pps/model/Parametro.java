package pe.com.pps.model;

import pe.trazos.dao.entidad.EntidadPK;

import javax.persistence.*;

@Entity
@IdClass(ParametroPK.class)
@Table(name = "lval")
public class Parametro extends EntidadPK<ParametroPK> {

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

	@Override
	public ParametroPK getId() {
		return new ParametroPK(tipo, codigo);
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
