package com.Foodservice.Service;

import com.Foodservice.Dao.FoodMenuRepo;
//import com.Foodservice.Exceptions.FoodNotFoundException;
import com.Foodservice.GlobalException.ItemNotFoundException;
import com.Foodservice.dto.FoodMenuDto;
import com.Foodservice.model.FoodMenu;
import com.Foodservice.utility.Utility;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@Slf4j
public class FoodMenuService {
 @Autowired
    private final FoodMenuRepo foodRepository;

    @Autowired
    private Utility utility;

    public FoodMenuService(FoodMenuRepo foodRepository) {
        this.foodRepository = foodRepository;
    }

    public List<FoodMenu> getAllFood() {

        return foodRepository.findAll();
    }

    public FoodMenuDto getFoodById(Long id) throws ItemNotFoundException {

        FoodMenu foodMenu = foodRepository.findById(id)
                .orElseThrow(() -> throwException(String.valueOf(id)));
        return  utility.convertToDto(foodMenu);

    }

    private ItemNotFoundException throwException(String value) {

        throw new ItemNotFoundException("Item Not Found with ID: " + value);
    }
    public FoodMenu createFood(FoodMenuDto food) {

        return foodRepository.save(utility.convertToEntity(food));
    }

    public FoodMenu updateFood(Long id, FoodMenuDto food) throws ItemNotFoundException{

        FoodMenu object = foodRepository.findById(id)
                .orElseThrow(() -> throwException(String.valueOf(id)));
        return foodRepository.save(utility.convertToEntity(food));

//        return foodRepository.findById(id)
//                .map(f -> {
//                    f.setName(food.getName());
//                    f.setDescription(food.getDescription());
//                    f.setPrice(food.getPrice());
//                    f.setIngredients(food.getIngredients());
//                    f.setImageUrl(food.getImageUrl());
//                    f.setAvailable(food.isAvailable());
//                    f.setCategory(food.getCategory());
//                    return foodRepository.save(utility.convertToEntity());
//                })
//                .orElseThrow(() -> new ItemNotFoundException( "Item Not Found For Update"));
    }

    public boolean deleteFood(Long id) {

        Optional<FoodMenu> customer = foodRepository.findById(id);
        if (customer.isPresent()) {
            foodRepository.deleteById(id);
            return true;
        } else {
            throwException(String.valueOf(id));
            return false;
        }
    }


    public List<FoodMenu> findByCategory(String category) {

        return foodRepository.findByCategory(category);
    }




    //    public List<FoodMenu> searchFood(String query) throws  ItemNotFoundException{
//        return foodRepository.findByNameContainingIgnoreCaseOrDescriptionContainingIgnoreCase(query,query);
//    }
    public List<FoodMenu> searchFood(String query) throws ItemNotFoundException {

        List<FoodMenu> food = foodRepository.findByNameContainingIgnoreCaseOrDescriptionContainingIgnoreCase(query,query);
        if (food.isEmpty()) {
            throw new ItemNotFoundException("No food found with the given query: " + query);
        }
        return food;
    }
}
