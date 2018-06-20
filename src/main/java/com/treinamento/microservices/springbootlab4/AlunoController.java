package com.treinamento.microservices.springbootlab4;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestClientException;

import java.util.Calendar;
import java.util.List;

@RestController
@RequestMapping("/aluno")
public class AlunoController {

    private AlunoRepository alunoRepository;

    public AlunoController(AlunoRepository alunoRepository) {
        this.alunoRepository = alunoRepository;
    }

    @GetMapping
    public ResponseEntity<List<Aluno>> listAlunos() {
        return ResponseEntity.ok(alunoRepository.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Aluno> getAluno(@PathVariable("id") Long id) {
        return ResponseEntity.ok(alunoRepository.getOne(id));
    }

    @PostMapping
    public ResponseEntity<Aluno> createAluno(@RequestBody Aluno aluno) {
        return ResponseEntity.ok(alunoRepository.saveAndFlush(aluno));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Aluno> updateAluno(@PathVariable("id") Long id, @RequestBody Aluno aluno) {
        if (id.equals(aluno.getId())) {
            return ResponseEntity.ok(alunoRepository.saveAndFlush(aluno));
        } else {
            throw new RestClientException("Id and object didn't match");
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteAluno(@PathVariable("id") Long id) {
        return ResponseEntity.status(HttpStatus.NO_CONTENT).body(null);
    }

    @GetMapping("/nome/{nome}")
    public ResponseEntity<List<Aluno>> findByNome(@PathVariable String nome) {
        return ResponseEntity.ok(alunoRepository.findByNomeContaining(nome));
    }

    @GetMapping("/nascimento/mes/corrente")
    public ResponseEntity<List<Aluno>> findByDataNascimentoAtMesCorrente() {
        int mesCorrente = Calendar.getInstance().get(Calendar.MONTH);
        return ResponseEntity.ok(alunoRepository.findByDataNascimentoAtMesCorrente(mesCorrente));
    }
}
