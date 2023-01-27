package com.Foodservice.Dao;

import com.Foodservice.model.FoodMenu;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface FoodMenuRepo extends JpaRepository<FoodMenu,Long> {


    List<FoodMenu> findByCategory(String category);

   // List<FoodMenu> findByName(String name,String query);


     List<FoodMenu> findByNameContainingIgnoreCaseOrDescriptionContainingIgnoreCase(String query, String query1);
}
