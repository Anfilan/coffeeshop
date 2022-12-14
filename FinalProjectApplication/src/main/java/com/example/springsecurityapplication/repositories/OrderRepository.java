package com.example.springsecurityapplication.repositories;

import com.example.springsecurityapplication.models.Order;
import com.example.springsecurityapplication.models.Person;
import com.example.springsecurityapplication.models.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrderRepository extends JpaRepository<Order, Integer> {

    List<Order> findByPerson(Person person);

    List<Order> findById(int id);

    //List<Order> findByNumberContainingIgnoreCase(String number);

    @Query(value = "select * from t_orders where (lower(number) LIKE '%?1%')", nativeQuery = true)
    List<Order> findByNumberContainingIgnoreCase(String number);


//    List<Order> findByNumberContainingIgnoreCase(String number);





}
