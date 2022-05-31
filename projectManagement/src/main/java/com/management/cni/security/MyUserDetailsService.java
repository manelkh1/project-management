package com.management.cni.security;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.management.cni.entity.User;
import com.management.cni.repository.UserRepository;

@Service
public class MyUserDetailsService implements UserDetailsService{

	@Autowired
	UserRepository userRepository;

    @Override
    @Transactional
    // loadUserByUsername recupere les donnees de l'utilisateur de la base de donnes a l'aide du username
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

    	User user = userRepository.findByEmail(username);

        return user;
    }

}
