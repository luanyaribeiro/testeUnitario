package br.edu.ifma.engsoftii.atividade1.service;

import br.edu.ifma.engsoftii.atividade1.model.Autor;
import br.edu.ifma.engsoftii.atividade1.model.Emprestimo;
import br.edu.ifma.engsoftii.atividade1.model.Livro;
import br.edu.ifma.engsoftii.atividade1.model.Usuario;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

class EmprestimoServiceTest {
  
  private EmprestimoService emprestimoService;

  @BeforeEach
  public void setUp() {
    this.emprestimoService = new EmprestimoService();
  }


  @Test
  void testeEmprestimoDeLivroNaoReservado() {

    Usuario usuario = Usuario.builder()
            .matricula("001")
            .nome("Luany")
            .build();

    Livro livro = Livro.builder()
            .autor(new Autor())
            .isReservado(false)
            .isEmprestado(false)
            .build();

    emprestimoService.criarEmprestimo(livro, usuario);
    Assertions.assertEquals(true,  livro.isEmprestado());
  }

  @Test
  void testeEmprestimoDeLivroReservado() {
    Usuario usuario = Usuario.builder()
            .matricula("001")
            .nome("Luany")
            .build();

    Livro livro = Livro.builder()
            .autor(new Autor())
            .isReservado(true)
            .build();

    emprestimoService.criarEmprestimo(livro, usuario);
    assertEquals(null,  emprestimoService.criarEmprestimo(livro, usuario));

  }

  @Test
  void testeDataPrevistaCorreta() {
    Usuario usuario = Usuario.builder()
            .matricula("001")
            .nome("Luany")
            .build();

    Livro livro = Livro.builder()
            .autor(new Autor())
            .isReservado(false)
            .build();

    Emprestimo emprestimo = emprestimoService.criarEmprestimo(livro, usuario);
    Assertions.assertEquals(LocalDate.now().plusDays(7),emprestimo.getDataPrevista());
  }

  @Test
  void testeChecarUsuarioSemEmprestimo() {
    Usuario usuario = Usuario.builder()
            .matricula("001")
            .nome("Luany")
            .build();
    Assertions.assertEquals("001",usuario.getMatricula());
    Assertions.assertEquals("Luany",usuario.getNome());
  }

  @Test
  void testeUsuarioComUmEmprestimo() {
    Usuario usuario = Usuario.builder()
            .matricula("001")
            .nome("Luany")
            .build();

    Livro livro = Livro.builder()
            .autor(new Autor())
            .isReservado(false)
            .isEmprestado(false)
            .build();

    emprestimoService.criarEmprestimo(livro, usuario);
    Assertions.assertEquals(true,  livro.isEmprestado());
  }

  @Test
  void testeUsuarioComDoisEmprestimos() {
    Usuario usuario = Usuario.builder()
            .matricula("001")
            .nome("Luany")
            .build();

    Livro livro = Livro.builder()
            .autor(new Autor())
            .isReservado(false)
            .isEmprestado(false)
            .build();

    Livro livro2 = Livro.builder()
            .autor(new Autor())
            .isReservado(false)
            .isEmprestado(false)
            .build();

    emprestimoService.criarEmprestimo(livro, usuario);
    emprestimoService.criarEmprestimo(livro2, usuario);

    Assertions.assertEquals(true, livro.isEmprestado());
    Assertions.assertEquals(true, livro2.isEmprestado());
  }

  @Test
  void testeUsuarioComTresEmprestimos() {
    Usuario usuario = Usuario.builder()
            .matricula("001")
            .nome("Luany")
            .build();

    Livro livro = Livro.builder()
            .autor(new Autor())
            .isReservado(false)
            .isEmprestado(false)
            .build();

    Livro livro2 = Livro.builder()
            .autor(new Autor())
            .isReservado(false)
            .isEmprestado(false)
            .build();

    Livro livro3 = Livro.builder()
            .autor(new Autor())
            .isReservado(false)
            .isEmprestado(false)
            .build();

    emprestimoService.criarEmprestimo(livro, usuario);
    emprestimoService.criarEmprestimo(livro2, usuario);
    emprestimoService.criarEmprestimo(livro3, usuario);

    Assertions.assertEquals(true, livro.isEmprestado());
    Assertions.assertEquals(true, livro2.isEmprestado());
    Assertions.assertEquals(false, livro3.isEmprestado());
  }

  @Test
  void testeDevolucaoAntesDaDataPrevista() {
    Usuario usuario = Usuario.builder()
            .matricula("001")
            .nome("Luany")
            .build();

    Livro livro = Livro.builder()
            .autor(new Autor())
            .isReservado(false)
            .isEmprestado(false)
            .build();

    Emprestimo emprestimo = emprestimoService.criarEmprestimo(livro, usuario);
    Assertions.assertEquals(true,livro.isEmprestado());
    emprestimoService.finalizaEmprestimoHoje(livro, emprestimo);
    Assertions.assertEquals(true,livro.estaDisponivel());
  }

  @Test
  void testeDevolucaoNaDataPrevista() {
    Usuario usuario = Usuario.builder()
            .matricula("001")
            .nome("Luany")
            .build();

    Livro livro = Livro.builder()
            .autor(new Autor())
            .isReservado(false)
            .isEmprestado(false)
            .build();

    Emprestimo emprestimo = emprestimoService.criarEmprestimo(livro, usuario);
    Assertions.assertEquals(true,livro.isEmprestado());
    emprestimo.setDataDevolucao(LocalDate.now().plusDays(7));
    emprestimoService.finalizaEmprestimoHoje(livro, emprestimo);
    Assertions.assertEquals(true,livro.estaDisponivel());
  }

  @Test
  void testeDevolucaoComUmDiaDeAtraso() {

      Usuario usuario = Usuario.builder()
              .matricula("001")
              .nome("Luany")
              .build();

      Livro livro = Livro.builder()
              .autor(new Autor())
              .isReservado(false)
              .isEmprestado(false)
              .build();

      Emprestimo emprestimo = emprestimoService.criarEmprestimo(livro, usuario);
      Assertions.assertEquals(true,livro.isEmprestado());
      emprestimo.setDataDevolucao(LocalDate.now().plusDays(8));
      emprestimoService.finalizaEmprestimoHoje(livro, emprestimo);
      Assertions.assertEquals(true,livro.estaDisponivel());
      double valor = emprestimoService.valorAPagar(emprestimo);
      Assertions.assertEquals(valor, emprestimo.getValorPago());
  }

  @Test
  void testeDevolucaoComTrintaDiasDeAtraso() {
    Usuario usuario = Usuario.builder()
            .matricula("001")
            .nome("Luany")
            .build();

    Livro livro = Livro.builder()
            .autor(new Autor())
            .isReservado(false)
            .isEmprestado(false)
            .build();

    Emprestimo emprestimo = emprestimoService.criarEmprestimo(livro, usuario);
    Assertions.assertEquals(true,livro.isEmprestado());
    emprestimo.setDataDevolucao(LocalDate.now().plusDays(31));
    emprestimoService.finalizaEmprestimoHoje(livro, emprestimo);
    Assertions.assertEquals(true,livro.estaDisponivel());
    double valor = emprestimoService.valorAPagar(emprestimo);
    Assertions.assertEquals(valor, emprestimo.getValorPago());
  }
}