package appSoft.project;

import java.sql.Date;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.UUID;

public class TestClass {
	
	public static void main(String[] args) {
		System.out.println(LocalDate.now());
	   	//System.out.println(UUID.randomUUID());
	   	
	   	String uid = UUID.randomUUID().toString();
	   	System.out.println(uid.substring(0,8));
	   	
	   	String name = "hari";
	   	String photo = "hari.jpeg";
	   	
	   int time =  LocalTime.now().getSecond();
	   	System.out.println(photo+time);
	   	
	}

}
