package co.com.neoris.banco.comun.entity;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
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
@Table(name="nr_cliente")
public class ClienteEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="id")
	private Integer id;
	
	@Column(name="cliente_id")
	private String clienteIden;
	
	@Column(name="contrasena")
	private String contrasena;
	
	@Column(name="estado")
	private Boolean estado;
	
	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name="persona_id", referencedColumnName = "id")
	private PersonaEntity personaEntity;
	
}
