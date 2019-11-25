package br.com.meatapp.repositories;


import org.springframework.data.jpa.repository.JpaRepository;

import br.com.meatapp.domain.User;

public interface UserRepository extends JpaRepository<User, Integer>{

}
