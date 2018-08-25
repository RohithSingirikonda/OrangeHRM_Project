package com.HRM.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

import org.apache.commons.io.FileUtils;
import org.apache.log4j.Logger;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;

import com.HRM.base.TestBase;

public class TestUtil extends TestBase{
		
		
		public static int Implicit_wait = 20;
		public static int PageLoad_Timeout=30;
		
	
		public static String ADMIN_TESTDATA_SHEET_PATH = System.getProperty("user.dir")+"/src/main/java/com/HRM/testdata/"
				+ "Admin_Datasheet.xlsx";
		//public static String QUALIFICATIONS_TESTDATA_SHEET_PATH = System.getProperty("user.dir")+"/src/main/java/com/HRM/testdata/"
			//	+ "Qualifications_Menu.xlsx";
		
		
		static Workbook book;
		static Sheet sheet;
		
		
		
		public static Object[][] getTestData(String sheetName) {
			FileInputStream file = null;
			try {
				file = new FileInputStream(ADMIN_TESTDATA_SHEET_PATH);
				//file = new FileInputStream(QUALIFICATIONS_TESTDATA_SHEET_PATH);
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			}
			try {
				book = WorkbookFactory.create(file);
			} catch (InvalidFormatException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}
			sheet = book.getSheet(sheetName);
			Object[][] data = new Object[sheet.getLastRowNum()][sheet.getRow(0).getLastCellNum()];
			// System.out.println(sheet.getLastRowNum() + "--------" +
			// sheet.getRow(0).getLastCellNum());
			for (int i = 0; i < sheet.getLastRowNum(); i++) {
				for (int k = 0; k < sheet.getRow(0).getLastCellNum(); k++) {
					data[i][k] = sheet.getRow(i + 1).getCell(k).toString();
					// System.out.println(data[i][k]);
				}
			}
			return data;
		}

		
		
		
	
	
	public static void takeScreenshotAtEndOfTest() throws IOException{
		
		File srcFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
		FileUtils.copyFile(srcFile, new File(System.getProperty("user.dir")+"/screenshots/"+System.currentTimeMillis()+".png"));
		
	}
	
	
}
