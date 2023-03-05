package co.com.neoris.banco.comun.entity;

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
@Table(name="nr_cuenta")
public class CuentaEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="id")
	private Integer id;
	
	@Column(name="numero_cuenta")
	private String numeroCuenta;
	
	@Column(name="tipo_cuenta")
	private String tipoCuenta;
	
	@Column(name="saldo_inicial")
	private Integer saldoInicial;
	
	@Column(name="estado")
	private Boolean estado;
	
	@ManyToOne
	@JoinColumn(name="cliente_id", referencedColumnName = "id")
	private ClienteEntity clienteEntity;
	
}
