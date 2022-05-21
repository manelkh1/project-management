
package com.management.cni.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import java.util.Collection;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Table(name = "USER")
public class User implements UserDetails {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @Column(name = "first_name", length = 30, nullable = false)
  private String firstName;

  @Column(name = "last_name", length = 30, nullable = false)
  private String lastName;

  @Column(name = "photo")
  private byte[] photo;

  @Column(name = "post")
  private String post;

  @Column(name = "username")
  private String username;
  @Column(name = "email", nullable = false, unique = true)
  private String email;

  @Column(name = "password")
  private String password;

  @Column(name = "status")
  private Boolean status;

  @Column(name = "city")
  private String city;

  @Column(name = "country")
  private String country;

  @Column(name = "code_postal")
  private String codePostal;

  //ki tfasa5 ll user tetfasa5 session
  @OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL, mappedBy = "user")
  private Session session;

  @Enumerated(EnumType.STRING)
  private UserRole userRole;

  @OneToOne(mappedBy = "user")
  private Manager manager;

  @OneToOne(mappedBy = "user")
  private Member member;

  @OneToOne(mappedBy = "user")
  private Bank bank;

  @OneToOne(mappedBy = "user")
  private Admin admin;

  @Override
  public Collection<? extends GrantedAuthority> getAuthorities() {
    // TODO Auto-generated method stub
    return null;
  }

  @Override
  public String getUsername() {
    // TODO Auto-generated method stub
    return email;
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
//Indicates whether the user is enabled or disabled. A disabled user cannot be authenticated.
	@Override
	public boolean isEnabled() {
		// TODO Auto-generated method stub
    return true;
	}

}
