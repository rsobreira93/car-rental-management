package br.com.sobreiraromulo.carrentalmanagement.modules.users.dto;

import org.springframework.data.domain.Page;

import io.swagger.v3.oas.annotations.media.Schema;

@Schema(description = "Informações da paginação")
public record PaginationResponse(
        @Schema(description = "Número da página atual", example = "1") int page,

        @Schema(description = "Quantidade de elementos por página", example = "10") int pageSize,

        @Schema(description = "Total de elementos", example = "50") long totalElements,

        @Schema(description = "Total de páginas disponíveis", example = "5") int totalPages) {

    public static PaginationResponse fromPage(Page<?> page) {
        return new PaginationResponse(
                page.getNumber(),
                page.getSize(),
                page.getTotalElements(),
                page.getTotalPages());
    }

}