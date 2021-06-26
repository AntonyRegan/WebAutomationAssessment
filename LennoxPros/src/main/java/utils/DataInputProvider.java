package utils;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.Date;
import java.text.DateFormat;
import java.text.SimpleDateFormat;

import org.apache.poi.hssf.usermodel.HSSFDateUtil;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.testng.annotations.DataProvider;

public class DataInputProvider {
	static XSSFWorkbook workbook = null;
	static XSSFSheet sheet = null;
	static XSSFRow row = null;
	static XSSFCell cell = null;
	FileInputStream fis;
	
	public DataInputProvider() {
		
		 try {
			fis = new FileInputStream("./data/TestData.xlsx");
			 workbook = new XSSFWorkbook(fis);
		        fis.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	       
	}
	
	@SuppressWarnings("deprecation")
	public String getCellData(String sheetName, String colName, int rowNum)
    {
        try
        {
			/*
			 * FileInputStream fis = new FileInputStream("./data/TestData.xlsx"); workbook =
			 * new XSSFWorkbook(fis);
			 */
			sheet = workbook.getSheet(sheetName);
        	
            int col_Num = -1;
            sheet = workbook.getSheet(sheetName);
            row = sheet.getRow(0);
            for(int i = 0; i < row.getLastCellNum(); i++)
            {
                if(row.getCell(i).getStringCellValue().trim().equals(colName.trim()))
                    col_Num = i;
            }
 
            row = sheet.getRow(rowNum - 1);
            cell = row.getCell(col_Num);
 
            if(cell.getCellTypeEnum() == CellType.STRING)
                return cell.getStringCellValue();
            else if(cell.getCellTypeEnum() == CellType.NUMERIC || cell.getCellTypeEnum() == CellType.FORMULA)
            {
                String cellValue = String.valueOf(cell.getNumericCellValue());
                if(HSSFDateUtil.isCellDateFormatted(cell))
                {
                    DateFormat df = new SimpleDateFormat("dd/MM/yy");
                    Date date = (Date) cell.getDateCellValue();
                    cellValue = df.format(date);
                }
                return cellValue;
            }else if(cell.getCellTypeEnum() == CellType.BLANK)
                return "";
            else
                return String.valueOf(cell.getBooleanCellValue());
        }
        catch(Exception e)
        {
            e.printStackTrace();
            String colNum = "";
			return "row "+rowNum+" or column "+colNum +" does not exist  in Excel";
        }
    }
	
	
	public Object[][] getTestData(String dataSheetName){
		Object[][] data = null;


		sheet = workbook.getSheet(dataSheetName);

		//get the number of rows
		int rowCount = sheet.getLastRowNum();
		//get the number of columns
		int columnCount = sheet.getRow(0).getLastCellNum();

		data = new String[rowCount][columnCount];

		for(int i=1; i<=rowCount; i++) {
			row = (XSSFRow) sheet.getRow(i);
			for(int j=0; j<columnCount; j++) {
				String cellValue = "";
				cellValue = row.getCell(j).getStringCellValue();
				data[i-1][j] = cellValue;
				System.out.println("Cell Value == "+cellValue);
			}
		}




		return data;
	}
}
