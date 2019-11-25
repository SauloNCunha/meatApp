package br.com.meatapp.service;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.meatapp.domain.MenuItem;
import br.com.meatapp.domain.Restaurant;
import br.com.meatapp.domain.User;
import br.com.meatapp.repository.ItemMenuRepository;
import br.com.meatapp.repository.RestaurantRepository;
import br.com.meatapp.repository.UserRepository;

@Service
public class DBService {
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private RestaurantRepository restaurantRepository;
	
	@Autowired
	private ItemMenuRepository menuItemRepository;

	
	
	

	public void instanciaDatabase() {
		
		
			User user1 = new User(1, "Saulo", "saulon@hot.com", "123");
			User user2 = new User(2, "Saulo", "saulo@gmail.com", "456");
			userRepository.saveAll(Arrays.asList(user1, user2));
			
			Restaurant res1 = new Restaurant(1,"teste1","pizzaria","1:00", 1.2,"dfghj","8:00","about");
			Restaurant res2 = new Restaurant(2,"teste1","lanchonete","2:00", 1.5,"ddasd","5:00","about1");
			restaurantRepository.saveAll(Arrays.asList(res1,res2));
			
				
			MenuItem item1 = new MenuItem(1,"item1", "teste1", 1.4, "fghfgdsgfd", res1);
			MenuItem item2 = new MenuItem(2,"item2", "teste2", 1.7, "fghvsdvgfgdsgfd", res2);
			menuItemRepository.saveAll(Arrays.asList(item1,item2));
			
		

	}
}
