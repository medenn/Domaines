package win.Domaines.service;

import org.springframework.security.authentication.*;
import org.springframework.stereotype.Service;
import win.Domaines.config.JwtService;
import win.Domaines.entity.User;
import win.Domaines.repository.UserRepository;

@Service
public class AuthService {

    private final AuthenticationManager authManager;
    private final JwtService jwtService;
    private final UserRepository userRepository;

    public AuthService(AuthenticationManager authManager, JwtService jwtService, UserRepository userRepository){
        this.authManager = authManager;
        this.jwtService = jwtService;
        this.userRepository = userRepository;
    }

    public String authenticate(String username, String password){
        authManager.authenticate(new UsernamePasswordAuthenticationToken(username, password));
        User u = userRepository.findByUsername(username).orElseThrow();
        return jwtService.generateToken(u.getUsername()); // pass username only
    }
}
