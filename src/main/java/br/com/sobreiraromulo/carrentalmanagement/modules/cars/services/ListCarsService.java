package br.com.sobreiraromulo.carrentalmanagement.modules.cars.services;

import org.apache.commons.lang3.StringUtils;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import br.com.sobreiraromulo.carrentalmanagement.modules.cars.CarEntity;
import br.com.sobreiraromulo.carrentalmanagement.modules.cars.CarEntity.Status;
import br.com.sobreiraromulo.carrentalmanagement.modules.cars.dto.CarResponseDTO;
import br.com.sobreiraromulo.carrentalmanagement.modules.cars.repositories.CarRepository;

@Service
public class ListCarsService {

    private CarRepository carRepository;

    public ListCarsService(CarRepository carRepository) {
        this.carRepository = carRepository;
    }

    public Page<CarResponseDTO> execute(String brand, String status, PageRequest pageRequest) {
        Page<CarEntity> cars;

        if (StringUtils.isNotEmpty(brand) || StringUtils.isNotEmpty(status)) {
            cars = this.carRepository.findByBrandOrStatus(brand, Status.valueOf(status),
                    pageRequest);
        } else {
            cars = this.carRepository.findAll(pageRequest);
        }

        return cars.map(CarResponseDTO::fromEntity);
    }

}
