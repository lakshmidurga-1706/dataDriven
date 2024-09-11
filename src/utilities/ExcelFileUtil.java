package utilities;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;

import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFCellStyle;
import org.apache.poi.xssf.usermodel.XSSFFont;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.grid.Main;

public class ExcelFileUtil {
	XSSFWorkbook wb;
	public ExcelFileUtil(String excelpath) throws Throwable
	{
		//creating constructor for reading excel path
		FileInputStream fi=new FileInputStream(excelpath);
		wb=new XSSFWorkbook(fi);
	
	}
//count no of row in a sheet
	public int rowCount(String sheetname)
	{
		return wb.getSheet(sheetname).getLastRowNum();
	}
	//method for reading cell data
	public String getCellData(String sheetname,int row, int column)
	{
		String Data="";
		if(wb.getSheet(sheetname).getRow(row).getCell(column).getCellType()==CellType.NUMERIC)
		{
			int celldata=(int)wb.getSheet(sheetname).getRow(row).getCell(column).getNumericCellValue();
			Data=String.valueOf(celldata);
		}
		else
		{
			Data=wb.getSheet(sheetname).getRow(row).getCell(column).getStringCellValue();
		}
		return Data;
			
	}
	//method for writing status into new wb
	public void setCellData(String sheetname,int row,int column, String status,String writeexcel) throws Throwable
	{
		//get sheet from wb
		XSSFSheet ws=wb.getSheet(sheetname);
		//get row from sheet
		XSSFRow rownum=ws.getRow(row);
		//create cell
		XSSFCell cell=rownum.createCell(column);
		cell.setCellValue(status);
		if(status.equalsIgnoreCase("Pass"))
		{
			XSSFCellStyle style=wb.createCellStyle();
			XSSFFont font=wb.createFont();
			font.setColor(IndexedColors.GREEN.getIndex());
			font.setBold(true);
			style.setFont(font);
			rownum.getCell(column).setCellStyle(style);
		}
		else if(status.equalsIgnoreCase("Fail"))
		{
			XSSFCellStyle style=wb.createCellStyle();
			XSSFFont font=wb.createFont();
			font.setColor(IndexedColors.RED.getIndex());
			font.setBold(true);
			style.setFont(font);
			rownum.getCell(column).setCellStyle(style);
		}
		else if(status.equalsIgnoreCase("Blocked"))
		{
			XSSFCellStyle style=wb.createCellStyle();
			XSSFFont font=wb.createFont();
			font.setColor(IndexedColors.BLUE.getIndex());
			font.setBold(true);
			style.setFont(font);
			rownum.getCell(column).setCellStyle(style);
		}
		FileOutputStream fo=new FileOutputStream(writeexcel);
		wb.write(fo);
	}
	public static void main(String[] args) throws Throwable {
	
		ExcelFileUtil xl=new ExcelFileUtil("D:/data.xlsx");
		int rc=xl.rowCount("Sheet1");
		System.out.println(rc);
	//itterate all rows
		for(int i=1;i<=rc;i++)
		{
			String fname=xl.getCellData("Sheet1", i, 0);
			String mname=xl.getCellData("Sheet1", i, 1);
			String lname=xl.getCellData("Sheet1", i, 2);
			String eid=xl.getCellData("Sheet1", i, 3);
			System.out.println(fname+"  "+mname+" "+lname+" "+eid);
			//write status as pass
			//xl.setCellData("Sheet1",i, 4,"Pass","D:/Results.xlsx");
			xl.setCellData("Sheet1",i, 4,"Fail","D:/Results.xlsx");
		}
	}
	}


