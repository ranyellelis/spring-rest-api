package br.com.ranyel.domain;

import java.time.LocalDate;
import java.time.LocalTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import br.com.ranyel.enums.EntradaSaidaEnum;
import br.com.ranyel.enums.PeriodoEnum;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
public class Ponto {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column
	private LocalDate data;

	@Column
	private LocalTime hora;

	@Enumerated(EnumType.STRING)
	private PeriodoEnum turno;

	@Enumerated(EnumType.STRING)
	private EntradaSaidaEnum entradaSaida;

}
