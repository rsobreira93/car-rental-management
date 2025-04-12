package br.com.sobreiraromulo.carrentalmanagement.modules.cars.services;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import br.com.sobreiraromulo.carrentalmanagement.exceptions.CsvException;
import br.com.sobreiraromulo.carrentalmanagement.modules.cars.CarEntity;
import br.com.sobreiraromulo.carrentalmanagement.modules.cars.CarEntity.Status;
import br.com.sobreiraromulo.carrentalmanagement.modules.cars.repositories.CarRepository;

@Service
public class ImportCarCsvService {

    private static final Logger logger = LoggerFactory.getLogger(ImportCarCsvService.class);

    private final CarRepository carRepository;

    public ImportCarCsvService(CarRepository carRepository) {
        this.carRepository = carRepository;
    }

    public void execute(MultipartFile file) throws Exception {
        if (file.isEmpty()) {
            throw new CsvException("The file is empty.");
        }

        try (BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(file.getInputStream()))) {
            String line;
            boolean isFirstLine = true;
            List<CarEntity> cars = new ArrayList<>();

            while ((line = bufferedReader.readLine()) != null) {
                if (isFirstLine) {
                    isFirstLine = false;
                    continue;
                }

                // Supondo que o CSV seja separado por vírgula
                String[] columns = line.split(",");
                if (columns.length < 5) {
                    throw new CsvException("Invalid line: " + line);
                }

                CarEntity car = new CarEntity();
                car.setBrand(columns[0]);
                car.setModel(columns[1]);
                car.setYear(columns[2]);
                car.setLicensePlate(columns[3]);
                car.setStatus(Status.valueOf(columns[4]));

                if (carRepository.findByLicensePlate(car.getLicensePlate()).isPresent()) {
                    logger.warn("Car with license plate {} already exists. Ignored line: {}.", car.getLicensePlate(),
                            line);
                    continue;
                }

                cars.add(car);
            }

            carRepository.saveAll(cars);
        } catch (IOException e) {
            throw new Exception("Error reading CSV file.", e);
        }
    }

}
