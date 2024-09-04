package com.baeksoo.shop;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class ShopApplication {

	public static void main(String[] args) {

		SpringApplication.run(ShopApplication.class, args);

		Friend friend1 = new Friend("kim",50);

		System.out.println(friend1.name);

	}

}

class Test{
	String name ="kim";
	void hello(){
		System.out.println("안녕");
	}

}

class Friend {
	String name;
	int age;

	Friend(String name, int age) {
		this.name = name;
		this.age = age;
	}
}