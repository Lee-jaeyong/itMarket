package com.ljy.market.user;

import javax.persistence.Id;
import javax.persistence.MappedSuperclass;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@MappedSuperclass
abstract public class User {
	@Id
	private String id;
	private String pw;
}
