package com.brendt.bootHello.service;

import java.util.Collection;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.brendt.bootHello.dao.UserDao;
import com.brendt.bootHello.model.Role;
import com.brendt.bootHello.model.User;
import com.brendt.bootHello.security.UserPrincipal;

@Service
public class UserService implements UserDetailsService {

    @Autowired
    UserDao userDao;

    @Override
    @Transactional
    public UserDetails loadUserByUsername(String username)
            throws UsernameNotFoundException {
    	System.out.println("loadUserByUsername " + username);
        // Let people login with username
        User user = userDao.findByUsername(username);
              

        return UserPrincipal.create(user);
//    	return new org.springframework.security.core.userdetails.User(user.getUsername(), user.getPassword(),
//				mapRolesToAuthorities(user.getRoles()));
	}
    
    @Transactional
    public UserDetails loadUserById(Long id) throws Exception {
        User user = userDao.findById(id).orElseThrow(
            () -> new Exception(Long.toString(id))
        );
        return UserPrincipal.create(user);
    }

//    @Transactional
//    public UserDetails loadUserById(Long id)
//            throws UsernameNotFoundException {
//        // Let people login with username
//        User user = userDao.findById(id);
//              
//
//
//    	return new org.springframework.security.core.userdetails.User(user.getUsername(), user.getPassword(),
//				mapRolesToAuthorities(user.getRoles()));
//	}

	private Collection<? extends GrantedAuthority> mapRolesToAuthorities(Collection<Role> roles) {
		return roles.stream().map(role -> new SimpleGrantedAuthority(role.getName())).collect(Collectors.toList());
	}
}