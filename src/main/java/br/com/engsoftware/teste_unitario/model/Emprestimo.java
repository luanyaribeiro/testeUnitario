package br.com.engsoftware.teste_unitario.model;

import java.time.LocalDate;
import java.time.Period;

import lombok.Data;

@Data
public class Emprestimo {
  private Usuario usuario;
  private Livro livroLocado;
  private LocalDate dataEmprestimo;
  private LocalDate dataPrevista;
  private LocalDate dataDevolucao;

  private double valorPago;

  public static double valorPadraoEmprestimo = 5;
  public static int limitePorUsuario = 2;
  public static int diasDeEmprestimo = 7;

  public double getValorAPagar(){
    double valor = valorPadraoEmprestimo;
    if(dataDevolucao.isAfter(dataPrevista)){
      Period periodo  = Period.between(dataDevolucao, dataPrevista);
      int diasAtraso = periodo.getDays();
      if(diasAtraso * 0.4 > (valorPadraoEmprestimo * 0.6)){
        valor = valor + (valorPadraoEmprestimo * 0.6);
      }else if(diasAtraso > 0){
        valor = valor + (diasAtraso * 0.4);
      }
    }
    return valor;
  }

  public void setDataEmprestimo(LocalDate dataEmprestimo) {
    this.dataEmprestimo = dataEmprestimo;
  }

  public void setDataPrevista(LocalDate dataPrevista) {
    this.dataPrevista = dataPrevista;
  }

  public void setDataDevolucao(LocalDate dataDevolucao){
    this.dataDevolucao = dataDevolucao;
  }
}
