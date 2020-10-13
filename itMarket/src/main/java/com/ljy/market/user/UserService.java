package com.ljy.market.user;

import org.modelmapper.ModelMapper;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class UserService implements UserDetailsService {

	private final UserRepository userRepository;
	private final PasswordEncoder passwordEncoder;
	private final ModelMapper modelMapper;

	public UserEntity save(UserDTO userDTO) {
		UserEntity user = createUser(userDTO);
		return userRepository.save(user);
	}

	private UserEntity createUser(UserDTO userDTO) {
		UserEntity user = modelMapper.map(userDTO, UserEntity.class);
		user.setPw(passwordEncoder.encode(user.getPw()));
		user.setRole(UserRole.ADMIN);
		return user;
	}

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		UserEntity user = userRepository.findById(username).orElseThrow(() -> new UsernameNotFoundException(username));
		return user;
	}

}
