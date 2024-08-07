package appSoft.project.utils;


import java.util.List;
import java.util.Map;

import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.springframework.web.servlet.view.document.AbstractXlsxView;

import appSoft.project.model.Expense;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class ExpenseExcel extends AbstractXlsxView {

	@Override
	protected void buildExcelDocument(
			Map<String, Object> model, 
			Workbook workbook, 
			HttpServletRequest request,
			HttpServletResponse response) 
					throws Exception {

		//1. define your own excel file name
		response.addHeader("Content-Disposition", "attachment;filename=Expense List.xlsx");
		
		//2. read data given by Controller
		@SuppressWarnings("unchecked")
		List<Expense> list = (List<Expense>) model.get("expenseList");
		
		//3. create one sheet
		Sheet sheet = workbook.createSheet("EXPENSE LIST");
		
		//4. create row#0 as header
		setHead(sheet);
		
		//5. create row#1 onwards from List<T> 
		setBody(sheet,list);
	}

	private void setHead(Sheet sheet) {
		Row row = sheet.createRow(0);
		row.createCell(0).setCellValue("ID");

		row.createCell(1).setCellValue("Expense Type");
		row.createCell(2).setCellValue("Particulars");
		row.createCell(3).setCellValue("Amount");
		row.createCell(4).setCellValue("Purchase Date");
		row.createCell(5).setCellValue("Purchase By");
		row.createCell(6).setCellValue("Grade");
		row.createCell(7).setCellValue("Remarks");
	}
	
	private void setBody(Sheet sheet, List<Expense> list) {
		int rowNum = 1;
		for(Expense spec : list) {
			Row row = sheet.createRow(rowNum++);
			row.createCell(0).setCellValue(spec.getId());

			row.createCell(1).setCellValue(spec.getExpenseType());
			row.createCell(2).setCellValue(spec.getParticulars());
			row.createCell(3).setCellValue(spec.getAmount());
			row.createCell(4).setCellValue(spec.getPurchaseDate().toString());
			row.createCell(5).setCellValue(spec.getPurchaseBy().toString());
			row.createCell(6).setCellValue(spec.getGrade());
			row.createCell(7).setCellValue(spec.getRemarks());

		}
	}

}
