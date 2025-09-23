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
public abstract class IProductMapper {

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "stocks", ignore = true)
    public abstract ProductEntity toEntity(final ProductSaveRequest request);

    public abstract ProductSavedResponse toSavedResponse(final ProductEntity entity);

    public abstract ProductStorefrontSaveDTO toDTO(final ProductEntity entity);

    @Mapping(target = "price", expression = "java(getCurrentPrice(entity.getStocks))")
    public abstract ProductDetailResponse toDetailResponse(final ProductEntity entity);
}
