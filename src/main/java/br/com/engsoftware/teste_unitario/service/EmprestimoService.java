package br.com.engsoftware.teste_unitario.service;

import br.com.engsoftware.teste_unitario.model.Emprestimo;
import br.com.engsoftware.teste_unitario.model.Livro;
import br.com.engsoftware.teste_unitario.model.Usuario;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class EmprestimoService {
  private List<Emprestimo> emprestimos = new ArrayList<>();

  public List<Emprestimo> consultarEmprestimosPorUsuario(Usuario usuario) {
    return emprestimos.stream().filter(
        emprestimo -> emprestimo.getUsuario() == usuario)
        .collect(Collectors.toList());
  }

  public Emprestimo criarEmprestimo(Livro livro, Usuario usuario){
    List<Emprestimo> emprestimosDoUsuario  = consultarEmprestimosPorUsuario(usuario);
    if(emprestimosDoUsuario.size() == Emprestimo.limitePorUsuario){
      return null;
    }
    if(livro.isReservado() && livro.getUsuarioReserva() != usuario){
      return null;
    }
    livro.setEmprestado(true);

    Emprestimo emprestimo = new Emprestimo();
    emprestimo.setUsuario(usuario);
    emprestimo.setLivroLocado(livro);
    emprestimo.setDataEmprestimo(LocalDate.now());
    emprestimo.setDataPrevista(LocalDate.now().plusDays(7));

    emprestimos.add(emprestimo);
    return emprestimo;
  }

  public void finalizaEmprestimoHoje (Livro livro, Emprestimo emprestimo){
    livro.setDisponivel();
    emprestimo.setDataDevolucao(LocalDate.now());
  }

  public double valorAPagar (Emprestimo emprestimo){
    emprestimo.getValorAPagar();
    return 0;
  }

}
