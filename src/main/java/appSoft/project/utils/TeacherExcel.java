package appSoft.project.utils;


import java.util.List;
import java.util.Map;

import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.springframework.web.servlet.view.document.AbstractXlsxView;

import appSoft.project.model.Student;
import appSoft.project.model.Teacher;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class TeacherExcel extends AbstractXlsxView {

	@Override
	protected void buildExcelDocument(
			Map<String, Object> model, 
			Workbook workbook, 
			HttpServletRequest request,
			HttpServletResponse response) 
					throws Exception {

		//1. define your own excel file name
		response.addHeader("Content-Disposition", "attachment;filename=Teacher List.xlsx");
		
		//2. read data given by Controller
		@SuppressWarnings("unchecked")
		List<Teacher> list = (List<Teacher>) model.get("teacherList");
		
		//3. create one sheet
		Sheet sheet = workbook.createSheet("TEACHER LIST");
		
		//4. create row#0 as header
		setHead(sheet);
		
		//5. create row#1 onwards from List<T> 
		setBody(sheet,list);
	}

	private void setHead(Sheet sheet) {
		Row row = sheet.createRow(0);
		row.createCell(0).setCellValue("ID");

		row.createCell(1).setCellValue("NAME");
		row.createCell(2).setCellValue("FACULTY");
		row.createCell(3).setCellValue("GRADE");
		row.createCell(4).setCellValue("SECTION");
		row.createCell(5).setCellValue("GENDER");
		row.createCell(6).setCellValue("SUBJECT");
		row.createCell(7).setCellValue("MOBILE NUMBER");
		row.createCell(8).setCellValue("ADDRESS");
		row.createCell(9).setCellValue("BASIC SALARY");
		row.createCell(10).setCellValue("PERIOD");
	}
	
	private void setBody(Sheet sheet, List<Teacher> list) {
		int rowNum = 1;
		for(Teacher spec : list) {
			Row row = sheet.createRow(rowNum++);
			row.createCell(0).setCellValue(spec.getId());

			row.createCell(1).setCellValue(spec.getFullName());
			row.createCell(2).setCellValue(spec.getFaculty().getName());
			row.createCell(3).setCellValue(spec.getGrade());
			row.createCell(4).setCellValue(spec.getSection());
			row.createCell(5).setCellValue(spec.getGender());
			row.createCell(6).setCellValue(spec.getSubject().getName());
			row.createCell(7).setCellValue(spec.getMobileNumber());
			row.createCell(8).setCellValue((spec.getAddress().getState()+","+spec.getAddress().getCity()));
			row.createCell(9).setCellValue(spec.getSalary());
			row.createCell(10).setCellValue(spec.getPeriod());

		}
	}

}
