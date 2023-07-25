package smu.likelion.Traditional.Market.config.auth;

import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.transaction.annotation.Transactional;
import smu.likelion.Traditional.Market.domain.entity.User;
import smu.likelion.Traditional.Market.repository.UserRepository;
import smu.likelion.Traditional.Market.util.ExceptionUtil;

import java.util.Collections;
import java.util.Set;

@RequiredArgsConstructor
public class AuthUserDetailsService implements UserDetailsService {
    private final UserRepository userRepository;

    @Override
    @Transactional
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findByEmail(username).orElseThrow(
                () -> ExceptionUtil.id(username, User.class.getName())
        );

        return AuthUser.builder()
                .username(user.getEmail())
                .password(user.getPassword())
                .role(user.getRole().getName()) // expect error
                .build();
    }
}
