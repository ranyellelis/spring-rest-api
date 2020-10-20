package br.com.ranyel.repository;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import br.com.ranyel.domain.Ponto;
import br.com.ranyel.enums.EntradaSaidaEnum;
import br.com.ranyel.enums.PeriodoEnum;

@Repository
public interface PontoRepository extends JpaRepository<Ponto, Long>{
	
	@Query(value = "select p from Ponto p where p.data = ?1 and p.turno = ?2")
	List<Ponto> buscarPorDataTurno(LocalDate data, PeriodoEnum periodoEnum);
	
	@Query(value = "select p from Ponto p where p.data = ?1 and p.turno = ?2 and p.entradaSaida = ?3")
	Ponto buscarPorDataTurnoEntradaSaida(LocalDate data, PeriodoEnum periodoEnum, EntradaSaidaEnum entradaSaidaEnum);

}
