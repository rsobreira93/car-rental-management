package br.com.sobreiraromulo.carrentalmanagement.modules.cars.services;

import java.util.UUID;

import org.springframework.stereotype.Service;

import br.com.sobreiraromulo.carrentalmanagement.exceptions.CarNotFound;
import br.com.sobreiraromulo.carrentalmanagement.modules.cars.CarEntity;
import br.com.sobreiraromulo.carrentalmanagement.modules.cars.CarEntity.Status;
import br.com.sobreiraromulo.carrentalmanagement.modules.cars.repositories.CarRepository;

@Service
public class UpdateStatusCarService {

    private CarRepository carRepository;

    public UpdateStatusCarService(CarRepository carRepository) {
        this.carRepository = carRepository;
    }

    public void execute(UUID id, String status) {
        CarEntity car = this.carRepository.findById(id)
                .orElseThrow(() -> {
                    throw new CarNotFound("Car not found with this id.");
                });

        System.err.println(Status.valueOf(status));

        car.setStatus(Status.valueOf(status));

        carRepository.save(car);

    }
}
