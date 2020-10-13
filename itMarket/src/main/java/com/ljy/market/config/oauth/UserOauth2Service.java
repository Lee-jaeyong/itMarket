package com.ljy.market.config.oauth;

import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.oauth2.client.userinfo.DefaultOAuth2UserService;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest;
import org.springframework.security.oauth2.core.OAuth2AuthenticationException;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Service;

import com.ljy.market.user.UserDTO;
import com.ljy.market.user.UserService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class UserOauth2Service extends DefaultOAuth2UserService {

	private final UserService userService;

	@Override
	public OAuth2User loadUser(OAuth2UserRequest userRequest) throws OAuth2AuthenticationException {
		OAuth2User oauth2User = super.loadUser(userRequest);

		String id = oauth2User.getAttributes().get("email").toString();
		String picture = oauth2User.getAttributes().get("picture").toString();
		String name = oauth2User.getAttributes().get("name").toString();

		UserDTO user = new UserDTO();
		user.setId(id);
		user.setPw(id);
		try {
			userService.loadUserByUsername(user.getId());
		} catch (UsernameNotFoundException e) {
			userService.save(user);
		}
		return oauth2User;
	}
}
