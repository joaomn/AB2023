package br.com.blueapp.agenda.entitys;



import java.io.Serializable;
import java.util.Collection;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import br.com.blueapp.agenda.entitys.dtos.AgendaUserDTO;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "TB_USUARIOS_LOGIN")
public class AgendaUser implements UserDetails, Serializable {
	
	
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "ID_Usuario")
	private Long userId;
	
	@Column(nullable = false, unique = true, name = "Login")
	private String username;
	
	
	@Column(nullable = false)
	private String password;
	
	
//	public AgendaUserDTO toDto(){
//		
//		return new AgendaUserDTO(this);
//		
//	}
//	
//	
//	public AgendaUser(AgendaUserDTO userDTO) {
//		this.password = userDTO.getPassword();
//		this.username = userDTO.getUsername();
//	}


	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		// TODO Auto-generated method stub
		return null;
	}


	@Override
	public String getPassword() {
		// TODO Auto-generated method stub
		return this.password;
	}


	@Override
	public String getUsername() {
		// TODO Auto-generated method stub
		return this.username;
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
