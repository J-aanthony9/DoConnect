package cogent.university.com.DoConnectBackend.service;

import cogent.university.com.DoConnectBackend.entity.User;
import cogent.university.com.DoConnectBackend.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.security.core.authority.SimpleGrantedAuthority;


import java.util.ArrayList;
import java.util.Set;
import java.util.HashSet;

@Service
public class CustomUserDetailsService implements UserDetailsService {
    @Autowired
    private UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findByUsername(username);
        
        //https://www.baeldung.com/spring-security-granted-authority-vs-role
        // remove role, use simplegrantedauthority
        
        Set<SimpleGrantedAuthority> authorities = new HashSet<>();
//      
//          authorities.add(new SimpleGrantedAuthority("ROLE_" + user.getUsertype()));
//          
        authorities.add(new SimpleGrantedAuthority("ROLE_ADMIN"));
        authorities.add(new SimpleGrantedAuthority("ROLE_USER"));
        
        return new org.springframework.security.core.userdetails.User(user.getUsername(), user.getPassword(), authorities);
    }
}
