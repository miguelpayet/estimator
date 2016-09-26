package pe.com.pps.model;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "tarea")
@DiscriminatorColumn(name = "tipocosto", discriminatorType = DiscriminatorType.INTEGER)
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
public class Tarea implements Identificable<Integer>, Serializable {

	public static final int ROL_DISEÑO_TECNICO = 1;
	public static final int ROL_ACOMPAÑAMIENTO = 2;
	public static final int ROL_GESTION = 3;

	@Column(name = "rol")
	private Integer rol;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer idtarea;
	@Column(name = "incluir")
	private Boolean incluir;
	@Column(name = "nombre")
	private String nombre;
	@Column(name = "orden")
	private Integer orden;
	@Column(name = "porcentaje")
	private Double porcentaje;
	@ManyToOne
	@JoinColumn(name = "idproveedor")
	private Proveedor proveedor;
	@Column(name = "tipocosto", insertable = false, updatable = false)
	private Integer tipoCosto;

	public Tarea() {
	}

	public Double convertirDiasAHoras(TareaCronograma unaTarea) {
		Double horas = null;
		if (unaTarea != null) {
			horas = unaTarea.getDias() * 8; // todo:horas del proveedor
		}
		return horas;
	}

	public Double convertirHorasADias(TareaCronograma unaTarea) {
		Double dias = null;
		if (unaTarea != null) {
			dias = unaTarea.getHoras() / 8; // todo:horas del proveedor
		}
		return dias;
	}

	public Integer getRol() {
		return rol;
	}

	@Override
	public Integer getId() {
		return idtarea;
	}

	public Boolean isIncluir() {
		return incluir;
	}

	public String getNombre() {
		return nombre;
	}

	public Integer getOrden() {
		return orden;
	}

	public Double getPorcentaje() {
		return porcentaje;
	}

	public Proveedor getProveedor() {
		return proveedor;
	}

	public Integer getTipoCosto() {
		return tipoCosto;
	}


	public void setRol(Integer rol) {
		this.rol = rol;
	}

	public void setIdtarea(Integer idtarea) {
		this.idtarea = idtarea;
	}

	public void setIncluir(Boolean incluir) {
		this.incluir = incluir;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public void setOrden(Integer orden) {
		this.orden = orden;
	}

	public void setPorcentaje(Double porcentaje) {
		this.porcentaje = porcentaje;
	}

	public void setProveedor(Proveedor proveedor) {
		this.proveedor = proveedor;
	}

	public void setTipoCosto(Integer tipoCosto) {
		this.tipoCosto = tipoCosto;
	}

}
