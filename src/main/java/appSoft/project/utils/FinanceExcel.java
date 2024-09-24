package appSoft.project.utils;


import java.util.List;
import java.util.Map;

import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.springframework.web.servlet.view.document.AbstractXlsxView;

import appSoft.project.model.Expense;
import appSoft.project.model.Fees;
import appSoft.project.model.FeesPayment;
import appSoft.project.model.SalaryPayment;
import appSoft.project.model.Student;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class FinanceExcel extends AbstractXlsxView {

	@Override
	protected void buildExcelDocument(
			Map<String, Object> model, 
			Workbook workbook, 
			HttpServletRequest request,
			HttpServletResponse response) 
					throws Exception {

		//1. define your own excel file name
		response.addHeader("Content-Disposition", "attachment;filename=Finance List.xlsx");
		
		//2. read data given by Controller
		@SuppressWarnings("unchecked")
		List<Expense> eList = (List<Expense>) model.get("expenseList");
//		List<SalaryPayment> sList = (List<SalaryPayment>) model.get("salaryPaymentList");
		List<FeesPayment> fList = (List<FeesPayment>) model.get("feesPaymentList");

		
		//3. create one sheet
		Sheet sheet = workbook.createSheet("EXPENSES LIST");
		Sheet sheet1 = workbook.createSheet("INCOME LIST");

		//4. create row#0 as header
		setHead(sheet);
		setHead1(sheet1);

		
		//5. create row#1 onwards from List<T> 
		setBody(sheet,eList );
		setBody1(sheet1,fList);
//		setBody2(sheet,sList);
	}

	private void setHead(Sheet sheet) {
		Row row = sheet.createRow(0);
		row.createCell(0).setCellValue("Id");

		row.createCell(1).setCellValue("Expense Type");
		row.createCell(2).setCellValue("Particulars");
		row.createCell(3).setCellValue("Amount");
		row.createCell(4).setCellValue("Expense Date");
		
	

	}
	int rowNum=1;
	private void setBody(Sheet sheet, List<Expense> list) {
//		int rowNum = 1;
		for(Expense spec : list) {
			Row row = sheet.createRow(rowNum++);
			row.createCell(0).setCellValue(spec.getId());

			row.createCell(1).setCellValue(spec.getExpenseType());
			row.createCell(2).setCellValue(spec.getParticulars());
			row.createCell(3).setCellValue(spec.getAmount());
			row.createCell(4).setCellValue(spec.getPurchaseDate().toString());

		}
	}
//	private void setBody2(Sheet sheet, List<SalaryPayment> list) {
////		int rowNum = 1;
//		for(SalaryPayment spec : list) {
//			Row row = sheet.createRow(rowNum++);
//			row.createCell(0).setCellValue(spec.getId());
//
//			row.createCell(1).setCellValue("Salary");
//			row.createCell(2).setCellValue(spec.getFullName());
//			row.createCell(3).setCellValue(spec.getAmount());
//			row.createCell(4).setCellValue(spec.getDate().toString());
//
//		}
//	}
	
	private void setHead1(Sheet sheet1) {
		Row row = sheet1.createRow(0);
		row.createCell(0).setCellValue("Id");
		row.createCell(1).setCellValue("Fees Type");
		row.createCell(2).setCellValue("Amount");
		row.createCell(3).setCellValue("Income Date");


	}
	
	private void setBody1(Sheet sheet1, List<FeesPayment> list) {
		int rowNum = 1;
		for(FeesPayment spec : list) {
			Row row = sheet1.createRow(rowNum++);
			row.createCell(0).setCellValue(spec.getId());

			row.createCell(1).setCellValue(spec.getFeesType());
			row.createCell(2).setCellValue(spec.getAmount());
			row.createCell(3).setCellValue(spec.getDate().toString());

		}
	}
	

}
