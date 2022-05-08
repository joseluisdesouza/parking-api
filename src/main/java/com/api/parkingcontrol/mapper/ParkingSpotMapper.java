package com.api.parkingcontrol.mapper;

import com.api.parkingcontrol.dtos.ParkingSpotDto;
import com.api.parkingcontrol.models.ParkingSpotModel;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public abstract class ParkingSpotMapper {
    public static final ParkingSpotMapper INSTANCE = Mappers.getMapper(ParkingSpotMapper.class);

    public abstract ParkingSpotDto toParkingSpotDTO(ParkingSpotModel parkingSpotModel);
}
