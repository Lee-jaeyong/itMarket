package com.ljy.market.config.oauth;

import org.springframework.core.MethodParameter;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.web.bind.support.WebDataBinderFactory;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.method.support.ModelAndViewContainer;

import com.ljy.market.user.UserEntity;

public class Oauth2Resolver implements HandlerMethodArgumentResolver {

	@Override
	public boolean supportsParameter(MethodParameter parameter) {
		return parameter.getParameterType() == UserEntity.class;
	}

	@Override
	public Object resolveArgument(MethodParameter parameter, ModelAndViewContainer mavContainer, NativeWebRequest webRequest,
		WebDataBinderFactory binderFactory) throws Exception {
		OAuth2User user = (OAuth2User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		UserEntity userEntity = new UserEntity();
		userEntity.setEmail(user.getAttributes().get("email").toString());
		return userEntity;
	}

}
