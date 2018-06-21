package com.treinamento.microservices.springbootlab4;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
@SpringBootTest
@DataJpaTest
public class AlunoTest {

	@Autowired
	private TestEntityManager entityManager;

	@Autowired
	private AlunoRepository alunoRepository;

	private List<Aluno> alunos = new ArrayList<>();

	@Before
	public void start() {
		// given
		alunos.add(Aluno.builder()
				.nome("Eduardo Mallmann")
				.matricula(6210852)
				.email("mallmann.edu@gmail.com")
				.dataNascimento(Date.from(LocalDateTime.of(1982, 5, 22, 19, 30).toInstant(ZoneOffset.UTC)))
				.build());
		// and
		alunos.add(Aluno.builder()
				.nome("Claudio Mallmann")
				.matricula(8934320)
				.email("diomallmann@gmail.com")
				.dataNascimento(Date.from(LocalDateTime.of(1984, 6, 20, 7, 30).toInstant(ZoneOffset.UTC)))
				.build());
	}

	@Test
	public void should_create_and_find_aluno_by_email() {
		// when
		alunos.forEach(aluno -> entityManager.persistAndFlush(aluno));
		// and
		Aluno alunoSaved = alunoRepository.findByEmail("mallmann.edu@gmail.com");

		// then
		assertThat(alunoSaved.getId()).isNotNull();
		assertThat(alunoSaved.getEmail()).isEqualTo("mallmann.edu@gmail.com");
	}

	@Test
	public void should_create_and_find_aluno_by_containing_nome() {
		// when
		alunos.forEach(aluno -> entityManager.persistAndFlush(aluno));
		// and
		List<Aluno> alunoList = alunoRepository.findByNomeContaining("Mallmann");

		// then
		assertThat(alunoList.size()).isEqualTo(2);
		assertThat(alunoList.stream().allMatch(aluno -> aluno.getNome().contains("Mallmann"))).isTrue();
	}

	@Test
	public void should_create_and_find_birthday_in_current_month() {
		// when
		alunos.forEach(aluno -> entityManager.persistAndFlush(aluno));
		// and
		List<Aluno> alunoList = alunoRepository.findByDataNascimentoAtMesCorrente(LocalDateTime.now().getMonthValue());

		// then
		assertThat(alunoList.size()).isEqualTo(1);
		assertThat(alunoList.stream().allMatch(aluno -> aluno.getNome().contains("Claudio"))).isTrue();
	}


}
