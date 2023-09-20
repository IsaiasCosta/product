package com.api.product.dtos;

import jakarta.validation.constraints.NotBlank; // não pode ser vazio ou branco
import jakarta.validation.constraints.NotNull; // não pode ser nulo
import jakarta.validation.constraints.Null;

import java.math.BigDecimal;

/* Criando a DTO Usando Records, Records são inmutaveis uma criada não podemos alterar o valor
*  Usando validações com @NotBlank,@Notnull, que faz parta do pacote Jakarta valitation*/
public record ProductRecordDto(@NotBlank String name, @NotNull BigDecimal value, @NotNull String description,@NotNull String cor)  {

}
