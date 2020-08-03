package com.mayconinforgames.spring.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.mayconinforgames.spring.exception.ResourceNotFoundException;
import com.mayconinforgames.spring.model.Funcionario;
import com.mayconinforgames.spring.repository.FuncionarioRepository;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("/api/v1/")
public class FuncionarioController {

	@Autowired
	private FuncionarioRepository funcionarioRepository;

	@GetMapping("/funcionarios")
	public List<Funcionario> getAllFuncionarios() {
		return funcionarioRepository.findAll();
	}

	@PostMapping("/funcionarios")
	public Funcionario createFuncionario(@RequestBody Funcionario funcionario) {
		return funcionarioRepository.save(funcionario);
	}

	@GetMapping("/funcionarios/{id}")
	public ResponseEntity<Funcionario> getFuncionarioById(@PathVariable Long id) {

		Funcionario funcionario = funcionarioRepository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("Código do funcionario não encontrado: " + id));
		return ResponseEntity.ok(funcionario);
	}

	@PutMapping("/funcionarios/{id}")
	public ResponseEntity<Funcionario> atualizarFuncionario(@PathVariable Long id,
			@RequestBody Funcionario funcionarioDetails) {

		Funcionario funcionario = funcionarioRepository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("Código do funcionario não encontrado: " + id));

		funcionario.setPrimeiroNome(funcionarioDetails.getPrimeiroNome());
		funcionario.setSegundoNome(funcionarioDetails.getSegundoNome());
		funcionario.setEmailId(funcionarioDetails.getEmailId());

		Funcionario atualizarFuncionario = funcionarioRepository.save(funcionario);
		return ResponseEntity.ok(atualizarFuncionario);
	}

	@DeleteMapping("/funcionarios/{id}")
	public ResponseEntity<Map<String, Boolean>> deletarFuncionario(@PathVariable Long id) {
		Funcionario funcionario = funcionarioRepository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("Não exite Funcionario com id informado " + id));
		
		funcionarioRepository.delete(funcionario);
		Map<String, Boolean> respose = new HashMap<>();
		respose.put("deleted", Boolean.TRUE);
		return ResponseEntity.ok(respose);
	}
	
}
