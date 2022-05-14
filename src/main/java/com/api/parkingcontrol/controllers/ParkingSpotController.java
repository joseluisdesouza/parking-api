package com.api.parkingcontrol.controllers;

import com.api.parkingcontrol.dtos.ParkingSpotDto;
import com.api.parkingcontrol.models.ParkingSpotModel;
import com.api.parkingcontrol.services.ParkingSpotService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RestController
@CrossOrigin(origins = "*", maxAge = 3600)
@RequestMapping("/parking-spot")
public class ParkingSpotController {

    @Autowired
    private ParkingSpotService parkingSpotService;

    @PostMapping
    // @Valid incluso no parametro faz com que as validações no dto sejam realizadas
    public ResponseEntity<Object> save(@RequestBody @Valid ParkingSpotDto parkingSpotDto) {
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(parkingSpotService.save(parkingSpotDto));
    }

    @GetMapping
    public ResponseEntity<Page<ParkingSpotModel>> getAllParkingSpots(
            @PageableDefault(page = 0,
                             size = 10,
                             sort = "id",
                             direction = Sort.Direction.ASC)
                             Pageable pageable) {
        return ResponseEntity.status(HttpStatus.OK)
                .body(parkingSpotService.findAll(pageable));
    }

    @GetMapping("/{id}")
    public ResponseEntity<Object> getById(@PathVariable UUID id) {
        Optional<ParkingSpotModel> parkingSpotModelOptional = parkingSpotService.findById(id);
        if (!parkingSpotModelOptional.isPresent()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("Parking Spot not found. ");
        }
        return ResponseEntity.status(HttpStatus.OK)
                .body(parkingSpotModelOptional.get());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Object> delete(@PathVariable UUID id) {
        Optional<ParkingSpotModel> parkingSpotModelOptional = parkingSpotService.findById(id);
        if (!parkingSpotModelOptional.isPresent()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("Parking Spot not found. ");
        }
        parkingSpotService.delete(parkingSpotModelOptional.get());
        return ResponseEntity.status(HttpStatus.OK)
                .body("Parking Spot deleted sucessfully");
    }

    @GetMapping("/name")
    public List<Object> returnAllResposibleName() {
        return parkingSpotService.returnAllResposibleName();
    }

//    @PutMapping("/{id}")
//    public ResponseEntity<Object> update(@PathVariable UUID id,
//                                         @RequestBody @Valid ParkingSpotDto parkingSpotDto) {
//        Optional<ParkingSpotModel> parkingSpotModelOptional = parkingSpotService.findById(id);
//        if (!parkingSpotModelOptional.isPresent()) {
//            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Parking Spot not found. ");
//        }
//        var parkingSpotModel = parkingSpotModelOptional.get();
//        parkingSpotModel.setParkingSpotNumber(parkingSpotDto.getParkingSpotNumber());
//        parkingSpotModel.setLicensePlateCar(parkingSpotDto.getLicensePlateCar());
//        parkingSpotModel.setBrandCar(parkingSpotDto.getBrandCar());
//        parkingSpotModel.setModelCar(parkingSpotDto.getModelCar());
//        parkingSpotModel.setColorCar(parkingSpotDto.getColorCar());
////        parkingSpotModel.setLicensePlateCar(parkingSpotDto.getLicensePlateCar());
////        parkingSpotModel.setBrandCar(parkingSpotDto.getBrandCar());
////        parkingSpotModel.setModelCar(parkingSpotDto.getModelCar());
////        parkingSpotModel.setColorCar(parkingSpotDto.getColorCar());
//        parkingSpotModel.setResponsibleName(parkingSpotDto.getResponsibleName());
//        parkingSpotModel.setApartment(parkingSpotDto.getApartment());
//        parkingSpotModel.setBlock(parkingSpotDto.getBlock());
//
//        return ResponseEntity.status(HttpStatus.OK).body(parkingSpotService.save(parkingSpotModel));
//    }

}
