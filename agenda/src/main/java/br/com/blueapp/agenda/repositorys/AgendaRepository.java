package br.com.blueapp.agenda.repositorys;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import br.com.blueapp.agenda.entitys.AgendaEntity;

@Repository
public interface AgendaRepository extends JpaRepository<AgendaEntity, Long> {
	
	Optional<AgendaEntity> findContatoByEmail(String email);
	Optional<AgendaEntity> findContatoByName(String name);
	Optional<AgendaEntity> findContatoByPhone(String phone);
	
	List<AgendaEntity> findAll();

}
