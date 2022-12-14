package com.practice.generating.export;

import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Component;

import com.lowagie.text.Document;
import com.lowagie.text.DocumentException;
import com.lowagie.text.Font;
import com.lowagie.text.FontFactory;
import com.lowagie.text.PageSize;
import com.lowagie.text.Paragraph;
import com.lowagie.text.Phrase;
import com.lowagie.text.pdf.CMYKColor;
import com.lowagie.text.pdf.PdfPCell;
import com.lowagie.text.pdf.PdfPTable;
import com.lowagie.text.pdf.PdfWriter;
import com.practice.generating.entity.Employee;

@Component
public class PDFGenerator {

	private List<Employee> employeeList;

	public List<Employee> getEmployeeList() {
		return employeeList;
	}
	
	
	public PDFGenerator() {
		super();
	}


	public PDFGenerator(List<Employee> employeeList) {
		super();
		this.employeeList = employeeList;
	}


	public void generate(HttpServletResponse response) throws DocumentException, IOException {

		// Creating the Object of Document
		Document document = new Document(PageSize.A4);

		// Getting instance of PdfWriter
		PdfWriter.getInstance(document, response.getOutputStream());

		// Opening the created document to modify it
		document.open();

		// Creating font
		// Setting font style and size
		Font fontTiltle = FontFactory.getFont(FontFactory.TIMES_ROMAN);
		fontTiltle.setSize(20);

		// Creating paragraph
		Paragraph paragraph = new Paragraph("List Of Employee", fontTiltle);
		Paragraph p1 = new Paragraph("");

		// Aligning the paragraph in document
		paragraph.setAlignment(Paragraph.ALIGN_CENTER);

		// Adding the created paragraph in document
		document.add(paragraph);
		document.add(p1);

		// Creating a table of 5 columns
		PdfPTable table = new PdfPTable(5);

		// Setting width of table, its columns and spacing
		table.setWidthPercentage(100f);
		table.setWidths(new int[] { 1,2,3,4,5 });
		table.setSpacingBefore(5);

		// Create Table Cells for table header
		PdfPCell cell = new PdfPCell();

		// Setting the background color and padding
		cell.setBackgroundColor(CMYKColor.MAGENTA);
		cell.setPadding(5);

		// Creating font
		// Setting font style and size
		Font font = FontFactory.getFont(FontFactory.TIMES_ROMAN);
		font.setColor(CMYKColor.WHITE);

		// Adding headings in the created table cell/ header
		// Adding Cell to table
		cell.setPhrase(new Phrase("ID", font));
		table.addCell(cell);
		cell.setPhrase(new Phrase("Name", font));
		table.addCell(cell);
		cell.setPhrase(new Phrase("Designation", font));
		table.addCell(cell);
		cell.setPhrase(new Phrase("Salary", font));
		table.addCell(cell);
		cell.setPhrase(new Phrase("PerformanceGrading", font));
		table.addCell(cell);

		// Iterating over the list of students
		for (Employee emp : employeeList) {
			// Adding student id
			table.addCell(String.valueOf(emp.getId()));
			// Adding student name
			table.addCell(emp.getName());
			// Adding student section
			table.addCell(emp.getDesignation());
			table.addCell(String.valueOf(emp.getSalary()));
			table.addCell(emp.getPerformanceGrading());
			
		}
		// Adding the created table to document
		document.add(table);

		// Closing the document
		document.close();

	}
	
	
}
