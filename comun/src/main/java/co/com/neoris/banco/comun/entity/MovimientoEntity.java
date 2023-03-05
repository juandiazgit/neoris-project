package co.com.neoris.banco.comun.entity;

import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name="nr_movimiento")
public class MovimientoEntity {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="id")
	private Integer id;
	
	@Column(name="fecha")
	private LocalDate fecha;
	
	@Column(name="tipo_movimiento")
	private String tipoMovimiento;
	
	@Column(name="valor")
	private Integer valor;

	@Column(name="saldo")
	private Integer saldo;
	
	@Column(name="estado")
	private Boolean estado;
	
	@ManyToOne
	@JoinColumn(name="cuenta_id", referencedColumnName = "id")
	private CuentaEntity cuentaEntity;

}
