package com.example.employeemanagement;

import lombok.AllArgsConstructor;
import lombok.Data;
@Data
@AllArgsConstructor
public class Section {

	private final String name;
	private int age;

	private void privateMethod() {
		System.out.println("Inside the private method");
	}

	private static void privateStaticMethod() {
		System.out.println("Inside a private static method");
	}

}
