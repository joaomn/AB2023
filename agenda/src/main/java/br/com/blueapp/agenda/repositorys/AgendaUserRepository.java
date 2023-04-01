package br.com.blueapp.agenda.repositorys;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.blueapp.agenda.entitys.AgendaUser;

@Repository
public interface AgendaUserRepository extends JpaRepository<AgendaUser, Long>{

	
	Optional<AgendaUser> findByUsername(String username);
}
