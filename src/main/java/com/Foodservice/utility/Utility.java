package com.Foodservice.utility;

import com.Foodservice.dto.FoodMenuDto;
import com.Foodservice.model.FoodMenu;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
public class Utility {

    private final ModelMapper modelMapper = new ModelMapper();

    public FoodMenuDto convertToDto(FoodMenu foodMenu) {
        FoodMenuDto foodMenuDTO = modelMapper.map(foodMenu, FoodMenuDto.class);
        return foodMenuDTO;
    }

    public FoodMenu convertToEntity(FoodMenuDto foodMenuDTO) {
        FoodMenu foodMenu = modelMapper.map(foodMenuDTO, FoodMenu.class);
        return foodMenu;
    }
}
