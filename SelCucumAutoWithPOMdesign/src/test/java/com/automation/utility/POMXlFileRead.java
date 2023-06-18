package com.automation.utility;

import java.io.File;
import java.io.IOException;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.util.Iterator;


import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFCellStyle;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class POMXlFileRead {
	public static void readCellDataFromXLfile(File path, String sName, int rNum, int cNum) {

		XSSFWorkbook workbook = null;
		try {
			workbook = new XSSFWorkbook(path);
			System.out.println(workbook.getSheetName(0));
			XSSFSheet sheet = workbook.getSheet(sName);
			XSSFRow row = sheet.getRow(rNum);
			XSSFCell cell = row.getCell(cNum);
			if (cell.getCellType() == CellType.NUMERIC)
				System.out.println("data = " + cell.getNumericCellValue());
			else if (cell.getCellType() == CellType.STRING)
				System.out.println("data = " + cell.getStringCellValue());
		} catch (InvalidFormatException | IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			workbook.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public static void readAllDataFromXLfile(File path, String sName) {

		XSSFWorkbook workbook = null;
		try {
			workbook = new XSSFWorkbook(path);
			System.out.println(workbook.getSheetName(0));
			XSSFSheet sheet = workbook.getSheet(sName);
			Iterator<Row> itRow = sheet.iterator();
			while (itRow.hasNext()) {
				Row row = itRow.next();
				Iterator<Cell> itCell = row.iterator();
				while (itCell.hasNext()) {
					Cell cell = itCell.next();

					if (cell.getCellType() == CellType.NUMERIC)
						System.out.print(cell.getNumericCellValue() + " ");
					else if (cell.getCellType() == CellType.STRING)
						System.out.print(cell.getStringCellValue() + " ");
				}
			}
		} catch (InvalidFormatException | IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			workbook.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
//Assignment to read all the sheets in the workbook
	public static void readWorksheet(File path) {
		XSSFWorkbook workbook = null;
		try {
			workbook = new XSSFWorkbook(path);
			Iterator<Sheet> allSheets = workbook.iterator();
			while (allSheets.hasNext()) {
				Sheet sheets = allSheets.next();
				Iterator<Row> allRows = sheets.iterator();
				System.out.println("\n" + sheets.getSheetName());
				while (allRows.hasNext()) {
					Row row = allRows.next();
					System.out.println();
					Iterator<Cell> allCells = row.iterator();
					while (allCells.hasNext()) {
						Cell cell = allCells.next();
						if (cell.getCellType() == CellType.NUMERIC)
							System.out.print(cell.getNumericCellValue() + " ");
						else if (cell.getCellType() == CellType.STRING)
							System.out.print(cell.getStringCellValue() + " ");
					}
				}
			}
		} catch (InvalidFormatException | IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			workbook.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
	public static void WriteXlFile(File path,String sName,int rNum,int cNum,String data) {
		FileInputStream fis;
		XSSFWorkbook workbook = null;
		try {
			fis = new FileInputStream(path);
			workbook = new XSSFWorkbook(fis);
			XSSFSheet sheet = workbook.getSheet(sName);
			XSSFRow row=sheet.getRow(rNum);
			XSSFCell cell = row.getCell(cNum);
			cell.setCellValue(data);
			FileOutputStream fos = new FileOutputStream(path); 
			workbook.write(fos);
			fos.flush();
			fos.close();
			workbook.close();
					
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public static void WriteXlFile(File path,String sName,int rNum,int cNum,int data) {
		FileInputStream fis;
		XSSFWorkbook workbook = null;
		try {
			fis = new FileInputStream(path);
			workbook = new XSSFWorkbook(fis);
			XSSFSheet sheet = workbook.getSheet(sName);
			XSSFRow row=sheet.getRow(rNum);
			XSSFCell cell = row.getCell(cNum);
			
		//	XSSFCellStyle style = workbook.createCellStyle();
			
		//	style.setFillBackgroundColor(IndexedColors.BLACK.getIndex());
		//	style.setFillForegroundColor(IndexedColors.WHITE.getIndex());
			cell.setCellValue(data);
		//	cell.setCellStyle(style);
			FileOutputStream fos = new FileOutputStream(path); 
			workbook.write(fos);
			fos.flush();
			fos.close();
			workbook.close();
					
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public static void WriteNewSheet(File path,String sName){
		FileInputStream fis = null;
		XSSFWorkbook workbook=null;
		
		try {
			fis=new FileInputStream(path);
			workbook = new XSSFWorkbook(fis);
			XSSFSheet sheet=workbook.createSheet(sName);
			XSSFRow row1 = sheet.createRow(0);
			XSSFCell cell11 = row1.createCell(0, CellType.NUMERIC);
			XSSFCell cell12 = row1.createCell(1, CellType.STRING);
			cell11.setCellValue(31);
			cell12.setCellValue("Sundar");
			XSSFRow row2 = sheet.createRow(1);
			XSSFCell cell21 = row2.createCell(0, CellType.NUMERIC);
			XSSFCell cell22 = row2.createCell(1, CellType.STRING);
			cell21.setCellValue(33);
			cell22.setCellValue("Thayal");
			FileOutputStream fos = new FileOutputStream(path); 
			workbook.write(fos);
			fos.flush();
			fos.close();
			workbook.close();
		} catch (IOException e) {
			
			// TODO Auto-generated catch block
			e.printStackTrace();
		}		
	}
}
	
