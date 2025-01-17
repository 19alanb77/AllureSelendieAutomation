package selenideConfig;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

/**
 * Class that allows to read data from .xlsx file.
 * 
 * @author Alan Buda
 */ 
public class ExcelUtils {

	private static XSSFSheet ExcelWSheet; 
	private static XSSFWorkbook ExcelWBook;
	private static XSSFCell Cell;
	private static XSSFRow Row;
	
	/**
	 * Find values in .xlsx workbook.
	 * 
	 * @param FilePath
	 *          path to the .xlsx file 
	 * @param SheetName
	 *          name of the sheet in the workbook 
	 *          
	 * @return array with values from .xlsx file
	 */ 
	public static Object[][] getTableArray(String FilePath, String SheetName) throws Exception { 	
		String[][] tabArray = null;
		try {
			FileInputStream ExcelFile = new FileInputStream(FilePath);
			ExcelWBook = new XSSFWorkbook(ExcelFile);
			ExcelWSheet = ExcelWBook.getSheet(SheetName);	 
			int startRow = 1;
			int startCol = 1;
			int ci,cj;
			int totalRows = ExcelWSheet.getLastRowNum();
			int totalCols = ExcelWSheet.getRow(0).getLastCellNum() - 1;
			tabArray=new String[totalRows][totalCols];
			ci=0;
	 
			for (int i=startRow;i<=totalRows;i++, ci++) {              
				cj=0;
	    			for (int j=startCol;j<=totalCols;j++, cj++){
	    				tabArray[ci][cj]=getCellData(i,j);
	    				System.out.println(tabArray[ci][cj]);   
	    			}
			}
		} catch (FileNotFoundException e){ 
			System.out.println("Could not read the Excel sheet"); 
			e.printStackTrace(); 
		} catch (IOException e){
			System.out.println("Could not read the Excel sheet");
			e.printStackTrace();
		} 
			return(tabArray);	 
	 }
	 
	/**
	 * Find value of the given cell.
	 * 
	 * @param RowNum
	 *          number of the searched row  
	 * @param ColNum
	 *          number of the searched column
	 *          
	 * @return value of the cell
	 */ 
	 public static String getCellData(int RowNum, int ColNum) throws Exception {
		 try{
			 Cell = ExcelWSheet.getRow(RowNum).getCell(ColNum);  
			 String CellData = Cell.getStringCellValue();	 
			 return CellData;	 
			 
		 }catch (Exception e){
			 System.out.println(e.getMessage());
			 throw (e);
		 }
	 } 
}
