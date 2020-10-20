package br.com.ranyel.service;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.com.ranyel.domain.Ponto;
import br.com.ranyel.enums.EntradaSaidaEnum;
import br.com.ranyel.enums.PeriodoEnum;
import br.com.ranyel.repository.PontoRepository;

@Service
@Transactional
public class PontoService {
	
	@Autowired
	private PontoRepository repositorio;
	
	public void inserir(String data, String hora, PeriodoEnum periodoEnum) {
		try {
			LocalDate dia = LocalDate.parse(data, DateTimeFormatter.ofPattern("dd/MM/yyyy"));
			LocalTime horas = LocalTime.parse(hora, DateTimeFormatter.ofPattern("HH:mm:ss"));
			 
			List<Ponto> registros = repositorio.buscarPorDataTurno(dia,periodoEnum);
			
			Ponto ponto = new Ponto();
			ponto.setData(dia);
			ponto.setTurno(periodoEnum);
			ponto.setHora(horas);
			
			if(registros.isEmpty()) {
				ponto.setEntradaSaida(EntradaSaidaEnum.E);
			} else if (registros.size() == 1) {
				ponto.setEntradaSaida(EntradaSaidaEnum.S);
			}
			
			repositorio.save(ponto);
		} catch (Exception e) {
			throw e;
		}
	}
	
	public List<Ponto> listar(){
		return repositorio.findAll();
	}

	public void deletar(Long id) {
		try {
			repositorio.deleteById(id);
		} catch (Exception e) {
			throw e;
		}
		
	}

	public void alterar(String data, String hora, PeriodoEnum turno, EntradaSaidaEnum entradaSaida) {
		try {
			LocalDate dia = LocalDate.parse(data, DateTimeFormatter.ofPattern("dd/MM/yyyy"));
			LocalTime horas = LocalTime.parse(hora, DateTimeFormatter.ofPattern("HH:mm:ss"));
			
			Ponto ponto = repositorio.buscarPorDataTurnoEntradaSaida(dia, turno, entradaSaida);
			ponto.setHora(horas);
			repositorio.save(ponto);
		} catch (Exception e) {
			throw e;
		}
	}
}
