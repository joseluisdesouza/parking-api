package com.api.parkingcontrol.services;

import com.api.parkingcontrol.dtos.ParkingSpotDto;
import com.api.parkingcontrol.models.ParkingSpotModel;
import com.api.parkingcontrol.repositories.ParkingSpotRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.beans.BeanUtils;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(SpringExtension.class)
class ParkingSpotServiceTest {

    @InjectMocks
    ParkingSpotService parkingSpotService;

    @Mock
    ParkingSpotRepository parkingSpotRepository;

    @Test
    void save() {
        ParkingSpotModel parkingSpotModel = new ParkingSpotModel();
        parkingSpotModel.setParkingSpotNumber("11");
        parkingSpotModel.setLicensePlateCar("TG8585");
        parkingSpotModel.setBrandCar("Chevrollet");
        parkingSpotModel.setModelCar("Oxiz");
        parkingSpotModel.setColorCar("vermelho");
        parkingSpotModel.setResponsibleName("Pedro");
        parkingSpotModel.setApartment("344");
        parkingSpotModel.setBlock("03");
        when(parkingSpotRepository.save(any())).thenReturn(parkingSpotModel);
        //assertEquals(parkingSpotModel, parkingSpotService.save(parkingSpotModel));
    }

    @Test
    void findAll() {
        when(parkingSpotRepository.findAll()).thenReturn(any());
        //assertEquals(any(), parkingSpotService.findAll(any()));
    }

    @Test
    void findById() {
    }

    @Test
    void delete() {
    }
}