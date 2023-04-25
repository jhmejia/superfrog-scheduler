package edu.tcu.cs.superfrogscheduler.superfroguser;

import edu.tcu.cs.superfrogscheduler.system.exception.ObjectNotFoundException;
import jakarta.transaction.Transactional;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.security.crypto.password.PasswordEncoder;


import java.util.List;

@Service
@Transactional
public class SuperFrogUserService implements UserDetailsService {

    private final SuperFrogUserRepository superFrogUserRepository;

    private final PasswordEncoder passwordEncoder;

    public SuperFrogUserService(SuperFrogUserRepository superFrogUserRepository, PasswordEncoder passwordEncoder) {
        this.superFrogUserRepository = superFrogUserRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public List<SuperFrogUser> findAll() {
        return this.superFrogUserRepository.findAll();
    }

    public SuperFrogUser findById(Integer userId) {
        return this.superFrogUserRepository.findById(userId)
                .orElseThrow(() -> new ObjectNotFoundException("user", userId));
    }

    public SuperFrogUser save(SuperFrogUser newUser) {
        // We NEED to encode plain password before saving to the DB! TODO
        newUser.setPassword(this.passwordEncoder.encode(newUser.getPassword()));
        return this.superFrogUserRepository.save(newUser);
    }

    public SuperFrogUser update(Integer userId, SuperFrogUser update) {
        SuperFrogUser oldUser = this.superFrogUserRepository.findById(userId)
                .orElseThrow(() -> new ObjectNotFoundException("user", userId));
        oldUser.setEmail(update.getEmail());
        oldUser.setActive(update.isActive());
        oldUser.setRoles(update.getRoles());
        return this.superFrogUserRepository.save(oldUser);
    }

    public void delete(Integer userId) {
        this.superFrogUserRepository.findById(userId)
                .orElseThrow(() -> new ObjectNotFoundException("user", userId));
        this.superFrogUserRepository.deleteById(userId);
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return this.superFrogUserRepository.findByUsername(username)
                .map(superFrogUser -> new MyUserPrincipal(superFrogUser))// if found, wrap the returned user instance in a MyUserPrincipal
                .orElseThrow(() -> new UsernameNotFoundException("username " + username + " is not found.")); // Otherwise, throw an exception

    }
}