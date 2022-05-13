
package com.management.cni.Entity;

import java.util.Collection;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
@Table(name = "T_user", uniqueConstraints = { @UniqueConstraint(columnNames = "email") })
//UserDetails store user details
public class User implements UserDetails{
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
	
	@Column(name = "email", nullable = false, unique = true)
    private String email ;
	
	@Column(name= "password")
	private String password;
	
	@Column(name="status")
	private Boolean status;
	
	@Column(name="city")
	private String city;
	
	@Column(name="country")
	private String country;
	
	@Column(name="code_postal")
	private String codePostal;
	
	//ki tfasa5 ll user tetfasa5 session
	@OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL, mappedBy = "user")
    private Session session;
	
	
	@JsonIgnoreProperties({ "hibernateLazyInitializer", "handler" })
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "id_role", referencedColumnName = "id")
	private Role role;
	
	
	//one user have many invitation 
	@JsonIgnore
	@OneToMany(mappedBy = "user")
	private List<Invitation> invitation;
	
	//one user have many chats
	@JsonIgnore
	@OneToMany(mappedBy = "user")
	private List<Chat> chats;
	
	@JsonIgnore
	@OneToMany(mappedBy = "user")
	private List<Project> projects;
	
	@JsonIgnore
	@OneToMany(mappedBy = "user")
	private List<Notification> notifications;
	
	public User() {
		super();
	}

	public User(Long id, String firstName, String lastName, byte[] photo, String post, String email, String password,
			Boolean status, String city, String country, String codePostal) {
		super();
		this.id = id;
		this.firstName = firstName;
		this.lastName = lastName;
		this.photo = photo;
		this.post = post;
		this.email = email;
		this.password = password;
		this.status = status;
		this.city = city;
		this.country = country;
		this.codePostal = codePostal;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public byte[] getPhoto() {
		return photo;
	}

	public void setPhoto(byte[] photo) {
		this.photo = photo;
	}

	public String getPost() {
		return post;
	}

	public void setPost(String post) {
		this.post = post;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public Boolean getStatus() {
		return status;
	}

	public void setStatus(Boolean status) {
		this.status = status;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	public String getCodePostal() {
		return codePostal;
	}

	public void setCodePostal(String codePostal) {
		this.codePostal = codePostal;
	}
	
	

	public Role getRole() {
		return role;
	}

	public void setRole(Role role) {
		this.role = role;
	}

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
		return status;
	}
	
	
	
	
}
