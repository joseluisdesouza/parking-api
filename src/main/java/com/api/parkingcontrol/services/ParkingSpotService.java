package com.api.parkingcontrol.services;

import com.api.parkingcontrol.dtos.ParkingSpotDto;
import com.api.parkingcontrol.exception.ConflictException;
import com.api.parkingcontrol.mapper.ParkingSpotMapper;
import com.api.parkingcontrol.models.ParkingSpotModel;
import com.api.parkingcontrol.repositories.ParkingSpotRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class ParkingSpotService {

    @Autowired
    private ParkingSpotRepository parkingSpotRepository;

    @Transactional
    public ParkingSpotDto save(ParkingSpotDto parkingSpotDto)  {
        if (existsByCarModelLicensePlateCar(parkingSpotDto.getLicensePlateCar())) {
            throw new ConflictException("Conflict: License Plate Car is already in use!");
        }
        if (existsByParkingSpotNumber(parkingSpotDto.getParkingSpotNumber())) {
            throw new ConflictException("Conflict: Parking Spot is already in use!");
        }
        if (existsByApartmentAndBlock(parkingSpotDto.getApartment(), parkingSpotDto.getBlock())) {
            throw new ConflictException("Conflict: Parking Spot already registered for this apartment/block!");
        }
        ParkingSpotModel parkingSpotModel = ParkingSpotMapper.INSTANCE.convertToEntity(parkingSpotDto);
        parkingSpotModel.setRegistrationDate(LocalDateTime.now(ZoneId.of("UTC")));
        return new ParkingSpotDto(parkingSpotRepository.save(parkingSpotModel));
    }

    public Page<ParkingSpotModel> findAll(Pageable pageable) {
        return parkingSpotRepository.findAll(pageable);
    }

    public Optional<ParkingSpotModel> findById(UUID id) {
        return parkingSpotRepository.findById(id);
    }

    public List<Object> returnAllResposibleName() {
        return parkingSpotRepository.findByResponsibleName();
    }

    @Transactional
    public void delete(ParkingSpotModel model) {
        parkingSpotRepository.delete(model);
    }

    public boolean existsByCarModelLicensePlateCar(String licensePlateCar) {
        return parkingSpotRepository.existsByLicensePlateCar(licensePlateCar);
    }

    public boolean existsByParkingSpotNumber(String parkingSpotNumber) {
        return parkingSpotRepository.existsByParkingSpotNumber(parkingSpotNumber);
    }

    public boolean existsByApartmentAndBlock(String apartment, String block) {
        return parkingSpotRepository.existsByApartmentAndBlock(apartment, block);
    }
}
