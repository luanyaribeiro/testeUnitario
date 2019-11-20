package br.com.engsoftware.teste_unitario.model;

import java.util.List;

import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

@Data
@ToString(of = {"titulo"})
@EqualsAndHashCode(of = {"titulo", "autor"})
@Builder
public class Livro {
  private String titulo;
  private Autor autor;
  private boolean isReservado;
  private boolean isEmprestado;

  private Usuario usuarioReserva;

  private List<Emprestimo> historico;

  public void setEmprestado(boolean emprestado) {
    isEmprestado = emprestado;
    if(emprestado){
      isReservado = false;
      usuarioReserva = null;
    }
  }

  public void setDisponivel (){
    isEmprestado = false;
  }

  public boolean estaDisponivel (){
    if (!isEmprestado)
      return true;
    else
       return false;
  }
}
