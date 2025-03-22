package br.com.sobreiraromulo.carrentalmanagement.modules.users.dto;

import java.util.List;

import io.swagger.v3.oas.annotations.media.Schema;

//public record PaginatedResponse<T>(Map<String, Object> summary, List<T> data, PaginationResponse pagination) {}

@Schema(name = "PaginatedResponse", description = "Resposta paginada contendo uma lista de dados e metadados de paginação.")
public record PaginatedResponse<T>(
        @Schema(description = "Lista de itens da página atual.") List<T> data,
        @Schema(description = "Metadados da paginação (número da página, total de elementos, etc).") PaginationResponse pagination) {

}
