package br.edu.ifma.engsoftii.atividade1.model;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class Usuario {
  private String nome;
  private String matricula;
}
