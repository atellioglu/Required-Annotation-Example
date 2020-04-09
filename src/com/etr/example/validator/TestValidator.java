package com.etr.example.validator;

import java.lang.reflect.Field;

import com.etr.example.ValidationException;
import com.etr.example.annotations.Required;

public class TestValidator implements BaseValidator<Object> {
	@Override
	public void validate(Object object) {
		Field[] fields = object.getClass().getDeclaredFields();
		for(Field field: fields) {
			field.setAccessible(true);
			Required declaredAnnotation = field.getDeclaredAnnotation(Required.class);
			if(declaredAnnotation != null) {
				try {
					Object valueObj = field.get(object);
					if(valueObj == null) {
						if(declaredAnnotation.message() != "") {
							throw new ValidationException(declaredAnnotation.message());
						}
						throw new ValidationException("Validation failed for field "+field.getName());
					}
				}catch(ValidationException ex) {
					throw ex;
				}
				catch(Exception ex) {
					throw new ValidationException("Can not get field with name="+field.getName());
				}
			}
		}
		System.out.println("Validation success");
	}
	

}
