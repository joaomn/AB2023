package br.com.blueapp.agenda.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import br.com.blueapp.agenda.entitys.AgendaUser;
import br.com.blueapp.agenda.entitys.dtos.AgendaUserDTO;
import br.com.blueapp.agenda.repositorys.AgendaUserRepository;

@Service
public class UserDetailsServiceImpls implements UserDetailsService {
	
	@Autowired
	AgendaUserRepository userRepository;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		AgendaUser agdUser = userRepository.findByUsername(username)
				.orElseThrow(() -> new UsernameNotFoundException(username + " Não foi encontrado"));
		return agdUser;
	}

}
