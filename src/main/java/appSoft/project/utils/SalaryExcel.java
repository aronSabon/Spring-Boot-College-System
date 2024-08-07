package appSoft.project.utils;


import java.util.List;
import java.util.Map;

import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.springframework.web.servlet.view.document.AbstractXlsxView;

import appSoft.project.model.Salary;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class SalaryExcel extends AbstractXlsxView {

	@Override
	protected void buildExcelDocument(
			Map<String, Object> model, 
			Workbook workbook, 
			HttpServletRequest request,
			HttpServletResponse response) 
					throws Exception {

		//1. define your own excel file name
		response.addHeader("Content-Disposition", "attachment;filename=Salary List.xlsx");
		
		//2. read data given by Controller
		@SuppressWarnings("unchecked")
		List<Salary> list = (List<Salary>) model.get("salaryList");
		
		//3. create one sheet
		Sheet sheet = workbook.createSheet("SALARY LIST");
		
		//4. create row#0 as header
		setHead(sheet);
		
		//5. create row#1 onwards from List<T> 
		setBody(sheet,list);
	}

	private void setHead(Sheet sheet) {
		Row row = sheet.createRow(0);
		row.createCell(0).setCellValue("Id");

		row.createCell(1).setCellValue("Name");
		row.createCell(2).setCellValue("Faculty");
		row.createCell(3).setCellValue("Grade");
		row.createCell(4).setCellValue("Subject");
		row.createCell(5).setCellValue("Section");
		row.createCell(6).setCellValue("Pay Date");
		row.createCell(7).setCellValue("Period");
		row.createCell(8).setCellValue("Month");
		row.createCell(9).setCellValue("Amount");
		row.createCell(10).setCellValue("Amount Paid");

	}
	
	private void setBody(Sheet sheet, List<Salary> list) {
		int rowNum = 1;
		for(Salary spec : list) {
			Row row = sheet.createRow(rowNum++);
			row.createCell(0).setCellValue(spec.getId());

			row.createCell(1).setCellValue(spec.getFullName());
			row.createCell(2).setCellValue(spec.getFaculty().getName());
			row.createCell(3).setCellValue(spec.getGrade());
			row.createCell(4).setCellValue(spec.getSubject().getName());
			row.createCell(5).setCellValue(spec.getSection());
			row.createCell(6).setCellValue(spec.getPayDate().toString());
			row.createCell(7).setCellValue(spec.getPeriod());
			row.createCell(8).setCellValue(spec.getMonth());
			row.createCell(9).setCellValue(spec.getAmount());
			row.createCell(10).setCellValue(spec.getAmountPaid());

		}
	}

}
