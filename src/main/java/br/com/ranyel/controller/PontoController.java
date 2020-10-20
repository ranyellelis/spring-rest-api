package br.com.ranyel.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import br.com.ranyel.domain.Ponto;
import br.com.ranyel.enums.EntradaSaidaEnum;
import br.com.ranyel.enums.PeriodoEnum;
import br.com.ranyel.service.PontoService;

@RestController
@RequestMapping(value = "/api/prova", produces = { "application/json" })
public class PontoController {

	@Autowired
	private PontoService pontoService;

	private static Logger log = LoggerFactory.getLogger(PontoController.class);

	@PutMapping(value = { "/inserir" })
	public @ResponseBody ResponseEntity<String> inserir(@RequestParam(name = "data", required = false) String data,
			@RequestParam(name = "hora", required = false) String hora,
			@RequestParam(name = "periodo", required = false) PeriodoEnum periodoEnum) {

		try {
			pontoService.inserir(data, hora, periodoEnum);

		} catch (Exception e) {
			log.error(e.getMessage());
			return new ResponseEntity<String>(e.getMessage(),HttpStatus.BAD_GATEWAY);
		}
		return new ResponseEntity<String>("Anotação de ponto inserido com sucesso", HttpStatus.CREATED);
	}

	@GetMapping(value = { "/listar" })
	public @ResponseBody ResponseEntity<List<Ponto>> listar() {
		try {

			List<Ponto> retorno = pontoService.listar();

			return new ResponseEntity<List<Ponto>>(retorno, HttpStatus.OK);

		} catch (Exception e) {
			log.error(e.getMessage());
			return new ResponseEntity<List<Ponto>>(HttpStatus.BAD_GATEWAY);
		}
	}

	@PostMapping(value = { "/alterar" })
	public @ResponseBody ResponseEntity<?>  alterar(
			@RequestParam(name = "data", required = true) String data,
			@RequestParam(name = "hora", required = true) String hora,
			@RequestParam(name = "turno", required = true) PeriodoEnum turno,
			@RequestParam(name = "entradaSaida", required = true) EntradaSaidaEnum entradaSaida) {
		try {
			pontoService.alterar(data, hora, turno, entradaSaida);

		} catch (Exception e) {
			log.error(e.getMessage());
			return new ResponseEntity<>(e.getMessage() ,HttpStatus.BAD_GATEWAY);
		}
		return new ResponseEntity<>("Registro de ponto alterado com sucesso.", HttpStatus.OK);
	}

	@DeleteMapping(value = { "/deletar/{id}" })
	public @ResponseBody ResponseEntity<?> deletar(@PathVariable Long id) {
		try {
			pontoService.deletar(id);

		} catch (Exception e) {
			log.error(e.getMessage());
			return new ResponseEntity<>(HttpStatus.BAD_GATEWAY);
		}
		return new ResponseEntity<>("Registro de ponto excluído com sucesso",HttpStatus.OK);
	}
}