package br.com.sobreiraromulo.carrentalmanagement.modules.cars.services;

import org.springframework.stereotype.Service;

import br.com.sobreiraromulo.carrentalmanagement.exceptions.CarAlreadyExists;
import br.com.sobreiraromulo.carrentalmanagement.modules.cars.CarEntity;
import br.com.sobreiraromulo.carrentalmanagement.modules.cars.CarEntity.Status;
import br.com.sobreiraromulo.carrentalmanagement.modules.cars.dto.CreateCarRequestDTO;
import br.com.sobreiraromulo.carrentalmanagement.modules.cars.repositories.CarRepository;

@Service
public class CreateCarService {

    private CarRepository carRepository;

    public CreateCarService(CarRepository carRepository) {
        this.carRepository = carRepository;
    }

    public void execute(CreateCarRequestDTO createCarRequestDTO) {
        this.carRepository.findByLicensePlate(createCarRequestDTO.licensePlate())
                .ifPresent((user -> {
                    throw new CarAlreadyExists("License plate already exists.");
                }));

        CarEntity car = new CarEntity(
                createCarRequestDTO.brand(),
                createCarRequestDTO.model(),
                createCarRequestDTO.licensePlate(),
                createCarRequestDTO.year(),
                Status.AVAILABLE);

        this.carRepository.save(car);
    }

}
