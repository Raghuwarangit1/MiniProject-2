package com.nt.service;


import java.io.File;
import java.io.FileOutputStream;
import java.util.Date;
import java.util.List;

import javax.mail.internet.MimeMessage;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFFont;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.ClassPathResource;
import org.springframework.data.domain.Example;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import com.lowagie.text.Document;
import com.lowagie.text.Font;
import com.lowagie.text.FontFactory;
import com.lowagie.text.PageSize;
import com.lowagie.text.Paragraph;
import com.lowagie.text.Phrase;
import com.lowagie.text.pdf.CMYKColor;
import com.lowagie.text.pdf.PdfPCell;
import com.lowagie.text.pdf.PdfPTable;
import com.lowagie.text.pdf.PdfWriter;
import com.nt.entity.CustomerPlan;
import com.nt.entity.Plan;
import com.nt.mail.SendingMail;
import com.nt.repositories.CustomerRepository;


@Service
public class CustomerPlanServiceImpl implements ICustomerPlanService {
        @Autowired
	private SendingMail sender;	
        @Value("${spring.mail.username}")
	private String fromMailString;
	
	@Autowired
private CustomerRepository repo;
	@Override
	public String savePlan(CustomerPlan plan) {
		
		return "CustomerPlan Is Acitvated id:"+repo.save(plan).getCId();
	}

	
	@Override
	public List<String> getAllPlan() {
		// TODO Auto-generated method stub
		return repo.getAllPlans();
	}

	@Override
	public List<String> getAllStatus() {
		// TODO Auto-generated method stub
		return repo.getAllStatus();
	}


	

	
	

	@Override
	public List<CustomerPlan> getAllCustomers() {
		// TODO Auto-generated method stub
		return repo.findAll();
	}

	@Override
	public List<CustomerPlan> getCustomerByExample(Plan plan) {
		CustomerPlan c=new CustomerPlan();
if(StringUtils.isNotBlank(plan.getPName()))
			c.setPName(plan.getPName());
if(StringUtils.isNotBlank(plan.getPStatus()))
		c.setStatus(plan.getPStatus());
if(StringUtils.isNotBlank(plan.getPGender()))
  c.setCGender(plan.getPGender());
if(plan.getPStartDate()!=null)
	  c.setPlanStartDate(plan.getPStartDate());
if(plan.getPEnddate()!=null)
	  c.setPlanStartDate(plan.getPEnddate());
Example<CustomerPlan> of = Example.of(c);

		return repo.findAll(of);
	}


	@Override
	public void downloadExcel(HttpServletResponse res) throws Exception {
		
		XSSFWorkbook workbook = new XSSFWorkbook();
		XSSFSheet sheet = workbook.createSheet("CustomerPlanInfo");
		 Row row = sheet.createRow(0);
        CellStyle style = workbook.createCellStyle();
        XSSFFont font = workbook.createFont();
        font.setBold(true);
        font.setFontHeight(16);
        Cell cell = row.createCell(0);
        cell.setCellValue("cId");
       


        Cell cell1 = row.createCell(1);
        cell1.setCellValue("cName");
       


        Cell cell2 = row.createCell(2);
        cell2.setCellValue("cmail");
        
        
        Cell cell3 = row.createCell(3);
        cell3.setCellValue("phno");
      
        
        Cell cel = row.createCell(4);
        cel.setCellValue("gender");
      
      
        Cell cell4 = row.createCell(5);
        cell4.setCellValue("ssn");
       
        Cell cell5 = row.createCell(6);
        cell5.setCellValue("pName");
      
        Cell cell6 = row.createCell(7);
        cell6.setCellValue("status");
       
        Cell cell7 = row.createCell(8);
        cell7.setCellValue("startDate");
       
        Cell cell8 = row.createCell(9);
        cell8.setCellValue("endDate");
       
        int rowNum = 1;
        for (CustomerPlan emp : repo.findAll()) {
            Row empDataRow = sheet.createRow(rowNum++);
            Cell empIdCell = empDataRow.createCell(0);
            //empIdCell.setCellStyle(style);
            empIdCell.setCellValue(emp.getCId());

            Cell empNameCell = empDataRow.createCell(1);
            //empNameCell.setCellStyle(style);
            empNameCell.setCellValue(emp.getCName());

            Cell empRoleCell = empDataRow.createCell(2);
            //empRoleCell.setCellStyle(style);
            empRoleCell.setCellValue(emp.getCmail());
            

            Cell ll = empDataRow.createCell(3);
          ll.setCellStyle(style);
           ll.setCellValue(emp.getCPhNo());
            

            Cell l = empDataRow.createCell(4);
           l.setCellStyle(style);
          l.setCellValue(emp.getCGender());
            

            Cell Cell = empDataRow.createCell(5);
            Cell.setCellStyle(style);
           Cell.setCellValue(emp.getCSsn());

           Cell Cell1 = empDataRow.createCell(6);
           Cell1.setCellStyle(style);
           Cell1.setCellValue(emp.getPName());

           Cell Cell2 = empDataRow.createCell(7);
           Cell2.setCellStyle(style);
           Cell2.setCellValue(emp.getStatus());
           

           Cell emps = empDataRow.createCell(8);
           emps.setCellStyle(style);
           emps.setCellValue(emp.getPlanStartDate());
           

           Cell e= empDataRow.createCell(9);
          e.setCellStyle(style);
           e.setCellValue(emp.getPlanEndDate());
           
        }
        File file=new File("records.xls");
        FileOutputStream os=new FileOutputStream(file);
        workbook.write(os);
         String bodyString="dowload customer plan details";
         String msg="<h1>CustomerPlan Info</h2>";
        sender.sendMail(bodyString, msg, file);
       
        workbook.write(res.getOutputStream());
        workbook.close();
       res.getOutputStream().close();
       

	}

	@Override
	public void downloadpdf(HttpServletResponse res) throws Exception {
	List<CustomerPlan> findAll = repo.findAll();
	// Creating the Object of Document
    Document document = new Document(PageSize.A4);
    ServletOutputStream outputStream = res.getOutputStream();
    // Getting instance of PdfWriter
    PdfWriter.getInstance(document, outputStream);
    // Opening the created document to change it
    document.open();
    //tos end email we are creating onther document
    Document d2=new Document(PageSize.A4);
    File file=new File("data.pdf");
    FileOutputStream os=new FileOutputStream(file);
    PdfWriter.getInstance(d2, os);
   d2.open();
    // Creating font
    // Setting font style and size
    Font fontTiltle = FontFactory.getFont(FontFactory.TIMES_ROMAN);
    fontTiltle.setSize(20);
    // Creating paragraph

    Paragraph paragraph1 = new Paragraph("List of the Customers", fontTiltle);
    // Aligning the paragraph in the document
    paragraph1.setAlignment(Paragraph.ALIGN_CENTER);
    // Adding the created paragraph in the document
    document.add(paragraph1);
    //for mail
    d2.add(paragraph1);
    // Creating a table of the 4 columns
    PdfPTable table = new PdfPTable(10);
    // Setting width of the table, its columns and spacing
    table.setWidthPercentage(100 );
    table.setWidths(new int[] {3,3,3,3,3,3,3,3,3,3});
    table.setSpacingBefore(5);
    // Create Table Cells for the table header
    PdfPCell cell = new PdfPCell();
    // Setting the background color and padding of the table cell
    cell.setBackgroundColor(CMYKColor.BLUE);
    cell.setPadding(5);
    Font font = FontFactory.getFont(FontFactory.TIMES_ROMAN);
    font.setColor(CMYKColor.WHITE);
         
        cell.setPhrase(new Phrase("c- ID", font));
         
        table.addCell(cell);
        
        cell.setPhrase(new Phrase("c- Name", font));
         
        table.addCell(cell);
         
        cell.setPhrase(new Phrase("c-mail", font));
        table.addCell(cell);
         
        cell.setPhrase(new Phrase("c-phno", font));
        table.addCell(cell);
         
        cell.setPhrase(new Phrase("cGender", font));
        table.addCell(cell);
         
        cell.setPhrase(new Phrase("c-ssn", font));
        table.addCell(cell);
        cell.setPhrase(new Phrase("c-pname", font));
        table.addCell(cell);
        cell.setPhrase(new Phrase("c-status", font));
        table.addCell(cell);
        cell.setPhrase(new Phrase("c-startdate", font));
        table.addCell(cell);
        cell.setPhrase(new Phrase("c-endDate", font));
        table.addCell(cell);
       
        for (CustomerPlan user : findAll) {
            table.addCell(String.valueOf(user.getCId()));
            table.addCell(user.getCName());
            table.addCell(user.getCmail());
            table.addCell(user.getCPhNo().toString());
            table.addCell(String.valueOf(user.getCGender()));
            table.addCell(String.valueOf(user.getCSsn()));
            table.addCell(String.valueOf(user.getPName()));
            table.addCell(String.valueOf(user.getStatus()));
            table.addCell(String.valueOf(user.getPlanStartDate()));
            table.addCell(String.valueOf(user.getPlanEndDate()));
        }
     // Adding the created table to the document
        document.add(table);
        d2.add(table);
         String bodyString="CustomerPlan Info";
         String msg="<h1>dowload customer plan details</h2>";
      
       document.close();
        outputStream.close();
        
        d2.close();
        os.close();
        
        //after closing document and strams only we should send mail
        //other wilse document of the mail will not get data
        sender.sendMail(bodyString, msg, file);
        
  
       
       
		
	}//


	
}
