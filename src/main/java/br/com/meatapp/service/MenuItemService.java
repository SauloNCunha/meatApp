package br.com.meatapp.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import br.com.meatapp.domain.MenuItem;
import br.com.meatapp.repository.ItemMenuRepository;
import br.com.meatapp.service.exception.ObjectNotFoundException;

@Service
public class MenuItemService {
	@Autowired //A dependência automaticamente é instanciada pelo Spring pelo mecanismo de injeção de dependência
	private ItemMenuRepository menuRepositorys;
	
	public List<MenuItem> findAll(){
		return menuRepositorys.findAll();
	}
	
	
	public MenuItem findById(Integer id) {
		Optional<MenuItem> itemMenu = menuRepositorys.findById(id);
		return itemMenu.orElseThrow(()-> new ObjectNotFoundException("Item de Menu não encontrado! ID: "+ id));
	}
	
	public MenuItem insert(MenuItem menuItem) {
		menuItem.setId(null);
		return menuRepositorys.save(menuItem);
	}
	
	public MenuItem update(MenuItem menuItem, Integer id) {
		menuItem.setId(id);
		return menuRepositorys.save(menuItem);
	}
	
	public void delete(Integer id) {
		this.findById(id);
		try {
			menuRepositorys.deleteById(id);
		} catch (DataIntegrityViolationException e) {
			throw new DataIntegrityViolationException("Ocorreu um erro de integridade. Esse item de menu possui vendas realizadas.");
		}
	}


}
