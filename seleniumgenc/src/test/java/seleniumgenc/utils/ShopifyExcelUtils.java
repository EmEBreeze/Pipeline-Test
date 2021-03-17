package seleniumgenc.utils;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class ShopifyExcelUtils {
	
	public static List<ShopifyFormData> readDataFromExcel(String path, String sheetName) throws IOException
	{
		List<ShopifyFormData> listData = new ArrayList<ShopifyFormData>();
		FileInputStream fIn = new FileInputStream(new File(path));
		
		XSSFWorkbook workbook = new XSSFWorkbook(fIn);
		//XSSFSheet sheet = workbook.getSheet(sheetName);
		Sheet sheet = workbook.getSheet(sheetName);
		
		Iterator<Row> rowIt = sheet.iterator();
		while(rowIt.hasNext())
		{
			Row row = rowIt.next();
			
			if(row.getRowNum() == 0)
			{
				continue; //skip header row
			}
			
			Iterator<Cell> cellIt = row.iterator();
			ShopifyFormData sfd = new ShopifyFormData();
			
			while(cellIt.hasNext())
			{
				Cell cell = cellIt.next();
				int columnIndex = cell.getColumnIndex();
				DataFormatter format = new DataFormatter();
				
				switch(columnIndex)
				{
					case 0:
						sfd.setFirstname(format.formatCellValue(cell));
						break;
					case 1:
						sfd.setLastname(format.formatCellValue(cell));
						break;
					case 2:
						sfd.setUsername(format.formatCellValue(cell));
						break;
					case 3:
						sfd.setCity(City.valueOf(format.formatCellValue(cell)));
						break;
					case 4:
						sfd.setGender(Gender.valueOf(format.formatCellValue(cell)));
						break;
					case 5:
						sfd.setPassword(format.formatCellValue(cell));
						break;
					case 6:
						sfd.setTableText(format.formatCellValue(cell));
						break;
					case 7:
						sfd.setError(format.formatCellValue(cell));
						break;
				}
				//end switch
			}
			//end inner while
			listData.add(sfd);
		}
		//end outer while
		fIn.close();
		return listData;
	}
}
