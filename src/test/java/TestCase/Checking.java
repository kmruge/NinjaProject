package TestCase;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Date;

import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.testng.annotations.Test;

public class Checking {
	
	@Test
	void hi() throws IOException
	{
		String[][] date=null;
		try {
		FileInputStream InFile=new FileInputStream("C:\\Users\\kmruge\\eclipse-workspace\\Ninja_Store_Project\\Files\\data.xlsx");
		XSSFWorkbook book=new XSSFWorkbook(InFile);
		XSSFSheet sheet=book.getSheet("RegisterCredential1");
		int lastRow=sheet.getLastRowNum();
		int lastCol=sheet.getRow(0).getLastCellNum();
		date=new String[lastRow][lastCol];
		for(int i=0;i<lastRow;i++)
		{
			XSSFRow row=sheet.getRow(i+1);
			for(int j=0;j<lastCol;j++)
			{
				XSSFCell cell=row.getCell(j);
				DataFormatter diff=new DataFormatter();
				String value=diff.formatCellValue(cell);
				date[i][j]=value;
			}
		}
		System.out.print(date.length);
		}catch(Exception e)
		{
			e.getStackTrace();
		}
	}
}
