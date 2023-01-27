package com.Foodservice.Controller;

//import com.Foodservice.Exceptions.FoodNotFoundException;
import com.Foodservice.GlobalException.ItemNotFoundException;
import com.Foodservice.Service.FoodMenuService;
import com.Foodservice.dto.FoodMenuDto;
import com.Foodservice.model.FoodMenu;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


import java.util.List;

@RestController
@RequestMapping("/foodhub")
@Slf4j

public class FoodMenuController {
    @Autowired
    private FoodMenuService foodService;

    public FoodMenuController(FoodMenuService foodService) {
        this.foodService = foodService;
    }
    Logger logger = LoggerFactory.getLogger(FoodMenuController.class);

    @GetMapping("/getAll")
    public ResponseEntity<List<FoodMenu>> getAllFood() {
        List<FoodMenu> foodmenu = foodService.getAllFood();

        return new ResponseEntity<>(foodmenu, HttpStatus.ACCEPTED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<FoodMenuDto> getFoodById(@PathVariable Long id) throws ItemNotFoundException {
        FoodMenuDto foodMenuDto = foodService.getFoodById(id);

        return new ResponseEntity<>(foodMenuDto, HttpStatus.OK);

    }

    @PostMapping("/save")
    public ResponseEntity<FoodMenu> createFood(@RequestBody @Valid FoodMenuDto food) {

        try {
            FoodMenu object = foodService.createFood(food);
            return new ResponseEntity<>(object, HttpStatus.CREATED);
        } catch (Exception ex) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }



    @PutMapping("/{id}")
    public ResponseEntity<FoodMenu> updateFood(@PathVariable Long id, @RequestBody FoodMenuDto food) throws  ItemNotFoundException{

        try {
            return new ResponseEntity<FoodMenu>(foodService.updateFood(id, food), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }


    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteFood(@PathVariable Long id) {
        boolean deleteFoodById = foodService.deleteFood(id);

        if (deleteFoodById) {
            return new ResponseEntity<>(("Customer deleted - Customer ID:" + id), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(("Customer deletion failed - Customer ID:" + id), HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/category/{category}")
    public List<FoodMenu> getFoodByCategory(@PathVariable("category") String category) throws ItemNotFoundException {

        List<FoodMenu> food = foodService.findByCategory(category);
        if (food.isEmpty()) {
            throw new ItemNotFoundException("No food found with the given category: " + category);
        }
        return food;
    }

    //@GetMapping("/search")
//    public List<FoodMenu> searchForFood(@RequestParam("query") String query) throws  ItemNotFoundException{
//
//        return foodService.searchFood(query);
    //}
    @GetMapping("/search")
    public List<FoodMenu> searchForFood(@RequestParam("query") String query){

        try {
            return foodService.searchFood(query);
        } catch (ItemNotFoundException e) {
            //log the exception
            return (List<FoodMenu>) new ResponseEntity<>("Item not found", HttpStatus.NOT_FOUND);
        }
    }


}

