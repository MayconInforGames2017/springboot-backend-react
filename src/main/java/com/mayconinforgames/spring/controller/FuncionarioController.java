package com.mayconinforgames.spring.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
	public Funcionario inserirFuncionario(@RequestBody Funcionario funcionario) {
		return funcionarioRepository.save(funcionario);
	}

}
