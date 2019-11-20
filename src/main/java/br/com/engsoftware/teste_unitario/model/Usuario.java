package br.com.engsoftware.teste_unitario.model;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class Usuario {
  private String nome;
  private String matricula;
}
