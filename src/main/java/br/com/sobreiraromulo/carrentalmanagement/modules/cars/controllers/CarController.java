package br.com.sobreiraromulo.carrentalmanagement.modules.cars.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import br.com.sobreiraromulo.carrentalmanagement.modules.cars.dto.CreateCarRequestDTO;
import br.com.sobreiraromulo.carrentalmanagement.modules.cars.services.CreateCarService;
import br.com.sobreiraromulo.carrentalmanagement.modules.cars.services.ImportCarCsvService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;

@RestController
@RequestMapping("/cars")
public class CarController {

    @Autowired
    private CreateCarService createCarService;

    @Autowired
    private ImportCarCsvService importCarCsvService;

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

}
