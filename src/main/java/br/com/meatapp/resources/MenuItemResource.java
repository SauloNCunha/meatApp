package br.com.meatapp.resources;

import java.net.URI;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import br.com.meatapp.domain.MenuItem;
import br.com.meatapp.service.MenuItemService;

@RestController
@RequestMapping(value="menuItems")
public class MenuItemResource {
	
	@Autowired
	private MenuItemService menuItemService;
	
	@RequestMapping(method=RequestMethod.GET)
	public ResponseEntity<List<MenuItem>> findAll(){
		List<MenuItem> menuItems = menuItemService.findAll();
		return ResponseEntity.ok().body(menuItems);
	}
	
	@RequestMapping(value="{id}", method=RequestMethod.GET)
	public ResponseEntity<MenuItem> findById(@PathVariable Integer id){
		MenuItem menuItem = menuItemService.findById(id);
		return ResponseEntity.ok().body(menuItem);
	}
	
	@RequestMapping(method = RequestMethod.POST)
	public ResponseEntity<MenuItem> insert(@Valid @RequestBody MenuItem menuItem){
		menuItem = menuItemService.insert(menuItem);
		URI uri = ServletUriComponentsBuilder
				.fromCurrentRequest()
				.path("/id/{id}")
				.buildAndExpand(menuItem.getId()).toUri();
		return ResponseEntity.created(uri).body(menuItem);
	}
	
	@RequestMapping(value="id/{id}",method = RequestMethod.PUT)
	public ResponseEntity<Void> update(@Valid @RequestBody  MenuItem menuItem, @PathVariable  Integer id ){
		
		menuItem = menuItemService.update(menuItem, id);
		return ResponseEntity.noContent().build();
		
	}
	
	@RequestMapping(value="id/{id}",method = RequestMethod.DELETE)
	public ResponseEntity<Void> delete(@PathVariable  Integer id ){
		
		menuItemService.delete(id);
		return ResponseEntity.noContent().build();
		
	}

}
