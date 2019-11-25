package br.com.meatapp.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.meatapp.domain.Orders;

@Repository
public interface OrdersRepository extends JpaRepository<Orders, Integer>{
	
	

}
