package appSoft.project;

import java.text.DateFormat;
import java.text.DecimalFormat;
import java.text.Format;
import java.text.NumberFormat;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.ZonedDateTime;
import java.time.temporal.ChronoUnit;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;

import appSoft.project.constant.FeesStatus;
import appSoft.project.model.Fees;
import appSoft.project.service.FeesService;

public class TestClass {
	

	public static void main(String[] args) {


		
		Date date = (Date) Date.from(ZonedDateTime.now().minusMonths(1).toInstant());
		System.out.println(date);
		LocalDate lDate = LocalDate.now().withDayOfMonth(28);
		System.out.println(lDate);
		//terdtttttthg
		
//		 Locale deLocale = new Locale("de","De");
//		   Locale usLocale = new Locale("en","US");
//		   Locale npLocale = new Locale("en","IN");
//
//		   long number = 123456789L;
//
//		   NumberFormat npnf = NumberFormat.getInstance(npLocale);
//		   System.out.println( npnf.format(number) );
//		   Date now = new Date();
//		   DateFormat npdf = DateFormat.getDateTimeInstance(DateFormat.LONG, DateFormat.LONG, npLocale);
//		   System.out.println( npdf.format(now) );
		   

		       



//		 DecimalFormat df = new DecimalFormat("##,##,###");
//		int num =987987987;
//		System.out.println(df.format(num));
//		System.out.println(LocalDate.now());
//	   	//System.out.println(UUID.randomUUID());
//	   	
//	   	String uid = UUID.randomUUID().toString();
//	   	System.out.println(uid.substring(0,8));
//	   	
//	   	String name = "hari";
//	   	String photo = "hari.jpeg";
//	   	
//	   int time =  LocalTime.now().getSecond();
//	   	System.out.println(photo+time);
//	   	System.out.println(LocalTime.now().truncatedTo(ChronoUnit.SECONDS));
//	   	
//	   	
//	   	String date = "2024-06-20";
//		System.out.println(date);
		
		
//		Integer[] array= {2,3,5,6,7,8};
//		System.out.println(Arrays.toString(array));
//		List<Integer> arrayList1= Arrays.asList(array);
//
//		List<Integer> arrayList= Arrays.asList(array);
//		
//		for(Integer i : arrayList) {
//			i=i+2;
//			System.out.println(i);
//		}
//		System.out.println(arrayList);
//		System.out.println(arrayList1);
//
	
		
	}
	

}
