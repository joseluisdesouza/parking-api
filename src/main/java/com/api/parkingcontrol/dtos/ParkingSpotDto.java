package com.api.parkingcontrol.dtos;

import com.api.parkingcontrol.models.ParkingSpotModel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.With;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Data
@With
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ParkingSpotDto {

    @NotBlank
    private String parkingSpotNumber;
    @NotBlank
    private String responsibleName;
    @NotBlank
    private String apartment;
    @NotBlank
    private String block;

    @Size(max = 7)
    private String licensePlateCar;
    @NotBlank
    private String brandCar;
    @NotBlank
    private String modelCar;
    @NotBlank
    private String colorCar;

    public ParkingSpotDto(ParkingSpotModel parkingSpotModel) {
        this.parkingSpotNumber = parkingSpotModel.getParkingSpotNumber();
        this.responsibleName = parkingSpotModel.getResponsibleName();
        this.apartment = parkingSpotModel.getApartment();
        this.block = parkingSpotModel.getBlock();

        this.licensePlateCar = parkingSpotModel.getLicensePlateCar();
        this.brandCar = parkingSpotModel.getBrandCar();
        this.modelCar = parkingSpotModel.getModelCar();
        this.colorCar = parkingSpotModel.getColorCar();
    }
}
