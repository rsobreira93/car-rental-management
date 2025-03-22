package br.com.sobreiraromulo.carrentalmanagement.modules.users.controllers.dto;

import java.util.List;

//public record PaginatedResponse<T>(Map<String, Object> summary, List<T> data, PaginationResponse pagination) {}

public record PaginatedResponse<T>(List<T> data, PaginationResponse pagination) {

}
