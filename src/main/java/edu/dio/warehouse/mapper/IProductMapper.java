package edu.dio.warehouse.mapper;

import edu.dio.warehouse.dto.ProductStorefrontSaveDTO;
import edu.dio.warehouse.entity.ProductEntity;
import org.mapstruct.Mapper;

import static org.mapstruct.MappingConstants.ComponentModel.SPRING;

@Mapper(componentModel = SPRING)
public interface IProductMapper {

    ProductStorefrontSaveDTO toDTO(final ProductEntity entity);
}
