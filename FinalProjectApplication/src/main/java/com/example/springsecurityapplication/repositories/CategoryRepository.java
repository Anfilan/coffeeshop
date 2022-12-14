package com.example.springsecurityapplication.repositories;



import com.example.springsecurityapplication.models.Category;
import com.example.springsecurityapplication.models.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CategoryRepository extends JpaRepository<Category,Integer> {
    //Category findByName (String name);

    Optional<Category> findCategoryById(int id);

    Optional<Category> findByName(String name);
    void deleteById(Integer integer);

}
