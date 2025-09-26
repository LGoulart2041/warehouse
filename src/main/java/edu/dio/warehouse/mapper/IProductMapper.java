package edu.dio.warehouse.mapper;

import edu.dio.warehouse.controller.request.ProductSaveRequest;
import edu.dio.warehouse.controller.response.ProductDetailResponse;
import edu.dio.warehouse.controller.response.ProductSavedResponse;
import edu.dio.warehouse.dto.ProductStorefrontSaveDTO;
import edu.dio.warehouse.entity.ProductEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import static org.mapstruct.MappingConstants.ComponentModel.SPRING;

@Mapper(componentModel = SPRING)
public interface IProductMapper {

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "stocks", ignore = true)
    ProductEntity toEntity(final ProductSaveRequest request);

    ProductSavedResponse toSavedResponse(final ProductEntity entity);

    ProductStorefrontSaveDTO toDTO(final ProductEntity entity);

    ProductDetailResponse toDetailResponse(final ProductEntity entity);
}
