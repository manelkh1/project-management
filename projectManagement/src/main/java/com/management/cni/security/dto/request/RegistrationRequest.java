package com.management.cni.security.dto.request;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;


/**
 * Created on Ağustos, 2020
 *
 * @author Faruk
 */
@Getter
@Setter
@ToString
@NoArgsConstructor
public class RegistrationRequest {

	private String name;

	private String email;

	private String username;

	private String password;

}
