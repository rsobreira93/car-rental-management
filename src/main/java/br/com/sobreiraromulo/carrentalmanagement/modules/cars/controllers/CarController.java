package br.com.sobreiraromulo.carrentalmanagement.modules.cars.controllers;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import br.com.sobreiraromulo.carrentalmanagement.modules.cars.dto.CarResponseDTO;
import br.com.sobreiraromulo.carrentalmanagement.modules.cars.dto.CreateCarRequestDTO;
import br.com.sobreiraromulo.carrentalmanagement.modules.cars.dto.UpdateCarRequestDTO;
import br.com.sobreiraromulo.carrentalmanagement.modules.cars.services.CreateCarService;
import br.com.sobreiraromulo.carrentalmanagement.modules.cars.services.ImportCarCsvService;
import br.com.sobreiraromulo.carrentalmanagement.modules.cars.services.ListCarsService;
import br.com.sobreiraromulo.carrentalmanagement.modules.cars.services.UpdateStatusCarService;
import br.com.sobreiraromulo.carrentalmanagement.modules.users.dto.PaginatedResponse;
import br.com.sobreiraromulo.carrentalmanagement.modules.users.dto.PaginationResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.ExampleObject;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@RequestMapping("/cars")
@Tag(name = "Carros", description = "Informações do carros")
@SecurityRequirement(name = "jwt_auth")
public class CarController {

  @Autowired
  private CreateCarService createCarService;

  @Autowired
  private ImportCarCsvService importCarCsvService;

  @Autowired
  private ListCarsService listCarsService;

  @Autowired
  private UpdateStatusCarService updateCarStatus;

  @PostMapping
  @PreAuthorize("hasRole('ADMIN')")
  public ResponseEntity<?> createCar(@RequestBody CreateCarRequestDTO createCarRequestDTO) {
    createCarService.execute(createCarRequestDTO);

    return ResponseEntity.status(HttpStatus.CREATED).body(null);
  }

  @PostMapping("/upload")
  @PreAuthorize("hasRole('ADMIN')")
  @Operation(summary = "Upload de CSV", description = "Realiza o upload de um arquivo CSV contendo os dados dos carros para inserção em lote.")
  @ApiResponses({
      @ApiResponse(responseCode = "200", description = "Upload realizado com sucesso."),
      @ApiResponse(responseCode = "400", description = "Arquivo inválido ou formato incorreto."),
      @ApiResponse(responseCode = "500", description = "Erro interno ao processar o arquivo.")
  })
  public ResponseEntity<String> uploadCars(@RequestParam("file") MultipartFile file) {
    try {
      importCarCsvService.execute(file);
      return ResponseEntity.status(HttpStatus.ACCEPTED).body("Upload successful!");
    } catch (Exception e) {
      return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
          .body("Error processing file: " + e.getMessage());
    }
  }

  @GetMapping
  @PreAuthorize("hasRole('ADMIN') or hasRole('EMPLOYEE')")
  @Operation(summary = "Lista carros paginados", description = "Lista carros com paginação, ordenação e metadados.")
  @ApiResponses({
      @ApiResponse(responseCode = "200", description = "Lista de carros retornada com sucesso", content = @Content(mediaType = "application/json", schema = @Schema(implementation = PaginatedResponse.class, description = "PaginatedResponse<CarResponseDTO>"), examples = @ExampleObject(value = """
          {
            "data": [
              {
                "id": "123e4567-e89b-12d3-a456-426614174000",
                "brand": "Mercedes",
                "model": "Classe A",
                "licensePlate": "AAAABBBB",
                "year": "2009",
                "status": "AVAILABLE"
              }
            ],
            "pagination": {
              "page": 0,
              "pageSize": 10,
              "totalElements": 50,
              "totalPages": 5
            }
          }
          """))),
      @ApiResponse(responseCode = "401", description = "Acesso negado", content = @Content(mediaType = "text/plain", examples = @ExampleObject(value = "Invalid Token.")))
  })
  public ResponseEntity<PaginatedResponse<CarResponseDTO>> listCars(
      @RequestParam(name = "brand", required = false) String brand,
      @RequestParam(name = "status", required = false) String status,
      @RequestParam(name = "page", defaultValue = "0") Integer page,
      @RequestParam(name = "pageSize", defaultValue = "10") Integer pageSize) {
    PageRequest pageRequest = PageRequest.of(page, pageSize, Sort.by("status").ascending());

    var pageResponse = listCarsService.execute(brand, status, pageRequest);

    return ResponseEntity.ok(new PaginatedResponse<>(
        pageResponse.getContent(),
        PaginationResponse.fromPage(pageResponse)));
  }

  @PatchMapping("/{id}")
  @PreAuthorize("hasRole('ADMIN')")
  @Operation(summary = "Atualização do status do carro", description = "Realiza a  Atualização do status do carro.")
  @ApiResponses({
      @ApiResponse(responseCode = "204", description = "Atualização com sucesso."),
      @ApiResponse(responseCode = "400", description = "Status inválido."),
      @ApiResponse(responseCode = "500", description = "Erro interno.")
  })
  public ResponseEntity<HttpStatus> updateCarStatus(@PathVariable(name = "id", required = true) UUID id,
      @RequestBody final UpdateCarRequestDTO updateCarRequestDTO) {
    updateCarStatus.execute(id, updateCarRequestDTO.status());

    return ResponseEntity.status(HttpStatus.NO_CONTENT).body(null);
  }

}
