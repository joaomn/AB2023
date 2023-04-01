package br.com.blueapp.agenda.entitys.dtos;

import java.util.Collection;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import br.com.blueapp.agenda.entitys.AgendaUser;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class AgendaUserDTO implements UserDetails {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private Long userId;
	
	private String username;
	
	private String password;
	
	private String messagem;
	
	public AgendaUserDTO (AgendaUser user) {
		
		this.userId = user.getUserId();
		this.password = user.getPassword();
		this.username = user.getUsername();
		
				
		
	}

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean isAccountNonExpired() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean isEnabled() {
		// TODO Auto-generated method stub
		return true;
	}

}
