/*
 * package com.example.employeemanagement;
 * 
 * import org.springframework.boot.SpringApplication; import
 * org.springframework.boot.autoconfigure.SpringBootApplication;
 * 
 * @SpringBootApplication public class EmployeemanagementApplication {
 * 
 * public static void main(String[] args) {
 * SpringApplication.run(EmployeemanagementApplication.class, args); }
 * 
 * }
 */
package com.example.employeemanagement;

import java.lang.reflect.Field;
import java.lang.reflect.Method;

public class EmployeemanagementApplication {

	public static void main(String[] args) throws Exception {
		Section section = new Section("Sasi", 24);
		// accessing and modifying private fields
		Field[] fields = section.getClass().getDeclaredFields();
		for (Field field : fields) {
			System.out.println(field.getName());

			if (field.getName().equals("name")) {
				field.setAccessible(true);
				field.set(section, "Sanjay");
			}
		}
		// accessing private methods
		Method[] methods = section.getClass().getDeclaredMethods();
		for (Method method : methods) {
			System.out.println(method.getName());
			if (method.getName().equals("privateStaticMethod")) {
				method.setAccessible(true);
				method.invoke(null);
			}
		}

	}
}