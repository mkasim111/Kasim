package utilities;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class ExcelUtility {

	private FileInputStream file = null;
	private XSSFWorkbook workbook = null;
	private XSSFSheet sheet = null;
	public ExcelUtility(String sheetName) {
		try {
			file = new FileInputStream(new File(System.getProperty("user.dir") + "\\src\\test\\resources\\excel\\TestDataExcel.xlsx"));
			workbook = new XSSFWorkbook(file);
			sheet = workbook.getSheet(sheetName);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public int GetRowCount() {
		return sheet.getPhysicalNumberOfRows();
	}

	public int GetColCount() {
		return sheet.getRow(0).getPhysicalNumberOfCells();
	}

	public String readCellData(String value) {
		for (int i = 0; i < GetRowCount(); i++) {
			for (int j = 0; j < GetColCount(); j++) {
				if (sheet.getRow(i).getCell(j).getStringCellValue().equals(value)) {
					return sheet.getRow(i + 1).getCell(j).getStringCellValue();
				}
			}
		}
		return null;

	}

}
