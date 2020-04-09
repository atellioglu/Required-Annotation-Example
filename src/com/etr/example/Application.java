package com.etr.example;

import com.etr.example.entity.User;
import com.etr.example.validator.TestValidator;

public class Application {

	public static void main(String[] args){
		User user = new User();
		user.setSurname("Abdullah");
		new TestValidator().validate(user);
	}
}
