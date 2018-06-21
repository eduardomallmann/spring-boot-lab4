package com.treinamento.microservices.springbootlab4;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AlunoRepository extends JpaRepository<Aluno, Long> {

    Aluno findByEmail(String email);

    List<Aluno> findByNomeContaining(String nome);

    @Query("SELECT a FROM Aluno a WHERE MONTH(a.dataNascimento) = :mes")
    List<Aluno> findByDataNascimentoAtMesCorrente(@Param("mes") Integer mes);
}
