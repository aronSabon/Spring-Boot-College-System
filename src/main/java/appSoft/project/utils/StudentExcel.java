package appSoft.project.utils;


import java.util.List;
import java.util.Map;

import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.springframework.web.servlet.view.document.AbstractXlsxView;

import appSoft.project.model.Student;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class StudentExcel extends AbstractXlsxView {

	@Override
	protected void buildExcelDocument(
			Map<String, Object> model, 
			Workbook workbook, 
			HttpServletRequest request,
			HttpServletResponse response) 
					throws Exception {

		//1. define your own excel file name
		response.addHeader("Content-Disposition", "attachment;filename=Student List.xlsx");
		
		//2. read data given by Controller
		@SuppressWarnings("unchecked")
		List<Student> list = (List<Student>) model.get("studentList");
		
		//3. create one sheet
		Sheet sheet = workbook.createSheet("STUDENT LIST");
		
		//4. create row#0 as header
		setHead(sheet);
		
		//5. create row#1 onwards from List<T> 
		setBody(sheet,list);
	}

	private void setHead(Sheet sheet) {
		Row row = sheet.createRow(0);
		row.createCell(0).setCellValue("ID");

		row.createCell(1).setCellValue("ROLL NO");
		row.createCell(2).setCellValue("FULL NAME");
		row.createCell(3).setCellValue("FACULTY");
		row.createCell(4).setCellValue("GRADE");
		row.createCell(5).setCellValue("MOBILE NO");
		row.createCell(6).setCellValue("EMAIL");
		row.createCell(7).setCellValue("ADMISSION DATE");
	}
	
	private void setBody(Sheet sheet, List<Student> list) {
		int rowNum = 1;
		for(Student spec : list) {
			Row row = sheet.createRow(rowNum++);
			row.createCell(0).setCellValue(spec.getId());

			row.createCell(1).setCellValue(spec.getRollNo());
			row.createCell(2).setCellValue(spec.getFullName());
			row.createCell(3).setCellValue(spec.getFaculty().getName());
			row.createCell(4).setCellValue(spec.getGrade());
			row.createCell(5).setCellValue(spec.getMobileNumber());
			row.createCell(6).setCellValue(spec.getEmail());
			row.createCell(7).setCellValue(spec.getAdmissionDate().toString());

		}
	}

}
