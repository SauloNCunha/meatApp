package br.com.meatapp.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import br.com.meatapp.domain.Restaurant;
import br.com.meatapp.repository.RestaurantRepository;
import br.com.meatapp.service.exception.ObjectNotFoundException;

@Service
public class RestaurantService {
	@Autowired //A dependência automaticamente é instanciada pelo Spring pelo mecanismo de injeção de dependência
	private RestaurantRepository restaurantRepository;
	
	public List<Restaurant> findAll(){
		return restaurantRepository.findAll();
	}
	
	
	public Restaurant findById(Integer id) {
		Optional<Restaurant> restaurant = restaurantRepository.findById(id);
		return restaurant.orElseThrow(()-> new ObjectNotFoundException("Restaurante não encontrado! ID: "+ id));
	}
	
	public Restaurant insert(Restaurant restaurant) {
		restaurant.setId(null);
		return restaurantRepository.save(restaurant);
	}
	
	public Restaurant update(Restaurant restaurant, Integer id) {
		restaurant.setId(id);
		return restaurantRepository.save(restaurant);
	}
	
	public void delete(Integer id) {
		this.findById(id);
		try {
			restaurantRepository.deleteById(id);
		} catch (DataIntegrityViolationException e) {
			throw new DataIntegrityViolationException("Ocorreu um erro de integridade. Esse usuario possui vendas realizadas.");
		}
	}

}
