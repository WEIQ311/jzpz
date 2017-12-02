package com.jzpz.export.utils;

import org.apache.poi.ss.usermodel.BorderStyle;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.FontFamily;
import org.apache.poi.xssf.usermodel.*;

import java.text.DecimalFormat;

public class ExcelUtil {
	/*
	 // 创建字体对象
     Font ztFont = wb.createFont();
     ztFont.setItalic(true);                     // 设置字体为斜体字
     ztFont.setColor(Font.COLOR_RED);            // 将字体设置为“红色”
     ztFont.setFontHeightInPoints((short)22);    // 将字体大小设置为18px
     ztFont.setFontName("华文行楷");             // 将“华文行楷”字体应用到当前单元格上
     ztFont.setUnderline(Font.U_DOUBLE);         // 添加（Font.U_SINGLE单条下划线/Font.U_DOUBLE双条下划线）
     ztFont.setStrikeout(true);                  // 是否添加删除线
     ztStyle.setFont(ztFont);                    // 将字体应用到样式上面
     ztCell.setCellStyle(ztStyle);               // 样式应用到该单元格上

	 // 设置单元格边框
	 XSSFCellStyle borderStyle = (XSSFCellStyle)wb.createCellStyle();
	 // 设置单元格边框样式
	 // CellStyle.BORDER_DOUBLE      双边线
	 // CellStyle.BORDER_THIN        细边线
	 // CellStyle.BORDER_MEDIUM      中等边线
	 // CellStyle.BORDER_DASHED      虚线边线
	 // CellStyle.BORDER_HAIR        小圆点虚线边线
	 // CellStyle.BORDER_THICK       粗边线
	 borderStyle.setBorderBottom(CellStyle.BORDER_THICK);
	 borderStyle.setBorderTop(CellStyle.BORDER_DASHED);
	 borderStyle.setBorderLeft(CellStyle.BORDER_DOUBLE);
	 borderStyle.setBorderRight(CellStyle.BORDER_THIN);

	 // 设置单元格边框颜色
	 borderStyle.setBottomBorderColor(new XSSFColor(java.awt.Color.RED));
	 borderStyle.setTopBorderColor(new XSSFColor(java.awt.Color.GREEN));
	 borderStyle.setLeftBorderColor(new XSSFColor(java.awt.Color.BLUE));
     borderCell.setCellStyle(borderStyle);

	 // 设置单元格内容水平对其方式
	 XSSFCellStyle.ALIGN_CENTER       //居中对齐
	 XSSFCellStyle.ALIGN_LEFT         //左对齐
	 XSSFCellStyle.ALIGN_RIGHT        //右对齐
	 cellStyle.setAlignment(XSSFCellStyle.ALIGN_CENTER);
	 // 设置单元格内容垂直对其方式

	 XSSFCellStyle.VERTICAL_TOP       //上对齐
	 XSSFCellStyle.VERTICAL_CENTER    //中对齐
	 XSSFCellStyle.VERTICAL_BOTTOM    //下对齐
	 cellStyle.setVerticalAlignment(XSSFCellStyle.VERTICAL_CENTER);

	 // 设置单元格内容是否自动换行
	 cellStyle.setWrapText(true);

	 // 合并第十三行中的A、B、C三列
	 CellRangeAddress region = new CellRangeAddress(12, 12, 0, 2); // 参数都是从O开始
	 sheet.addMergedRegion(region);


	// 合并单元格行和列
	// 合并第十三行中的A、B、C三列
    CellRangeAddress region2 = new CellRangeAddress(13, 17, 3, 7); // 参数都是从O开始
    sheet.addMergedRegion(region2);

	 */

	/**
	 * 导出Excel标题字体
	 * 
	 * @param wb
	 * @return
	 */
	public static XSSFCellStyle excelTitleCellStyle(XSSFWorkbook wb) {
		XSSFFont font = wb.createFont();
		font.setFamily(FontFamily.SWISS);
		font.setFontHeightInPoints(Short.valueOf("12"));

		XSSFCellStyle cellStyle_title = wb.createCellStyle();
		cellStyle_title.setAlignment(XSSFCellStyle.ALIGN_CENTER);
		cellStyle_title.setBorderTop(BorderStyle.THIN);
		cellStyle_title.setBorderBottom(BorderStyle.THIN);
		cellStyle_title.setBorderLeft(BorderStyle.THIN);
		cellStyle_title.setBorderRight(BorderStyle.THIN);
		cellStyle_title.setFont(font);
		return cellStyle_title;
	}

	/**
	 * 补充Excel空格
	 * 
	 * @param row
	 *            行
	 * @param cellStyle
	 *            表格样式
	 * @param startCellIndex
	 *            开始列
	 * @param endCellIndex
	 *            结束咧
	 */
	public static void makeUpCell(XSSFRow row, XSSFCellStyle cellStyle, int startCellIndex, int endCellIndex) {
		for (int i = startCellIndex; i <= endCellIndex; i++) {
			XSSFCell xssfCell_title_6 = row.createCell(i);
			xssfCell_title_6.setCellValue("");
			xssfCell_title_6.setCellStyle(cellStyle);
		}
	}

	/**
	 * 表头字体
	 * 
	 * @param wb
	 * @return
	 */
	public static XSSFCellStyle excelTableHeadCellStyle(XSSFWorkbook wb) {
		XSSFFont font = wb.createFont();
		font.setFamily(FontFamily.SWISS);
		font.setFontHeightInPoints(Short.valueOf("9"));
		font.setBold(true);

		XSSFCellStyle cellStyle_title = wb.createCellStyle();
//		cellStyle_title.setAlignment(XSSFCellStyle.ALIGN_CENTER);
		cellStyle_title.setBorderTop(BorderStyle.THIN);
		cellStyle_title.setBorderBottom(BorderStyle.THIN);
		cellStyle_title.setBorderLeft(BorderStyle.THIN);
		cellStyle_title.setBorderRight(BorderStyle.THIN);
		cellStyle_title.setFont(font);
		return cellStyle_title;
	}

	/**
	 * 表内容字体
	 * 
	 * @param wb
	 * @return
	 */
	public static XSSFCellStyle excelTableContextCellStyle(XSSFWorkbook wb) {
		XSSFFont font = wb.createFont();
		font.setFamily(FontFamily.SWISS);
		font.setFontHeightInPoints(Short.valueOf("9"));

		XSSFCellStyle cellStyle_title = wb.createCellStyle();
		cellStyle_title.setBorderTop(BorderStyle.THIN);
		cellStyle_title.setBorderBottom(BorderStyle.THIN);
		cellStyle_title.setBorderLeft(BorderStyle.THIN);
		cellStyle_title.setBorderRight(BorderStyle.THIN);
		cellStyle_title.setFont(font);
		return cellStyle_title;
	}

	/**
	 * 转换表格内容为String
	 * 
	 * @param cell
	 * @return
	 */
	public static String cellValueToString(Cell cell) {
		if (cell == null) {
			return "";
		}
		String res = "";
		switch (cell.getCellType()) {
		case Cell.CELL_TYPE_NUMERIC:
			double value1 = cell.getNumericCellValue();
			DecimalFormat decimalFormat = new DecimalFormat("#");
			res = decimalFormat.format(value1) + "";
			break;
		case Cell.CELL_TYPE_BLANK:
			break;
		case Cell.CELL_TYPE_BOOLEAN:
			boolean value2 = cell.getBooleanCellValue();
			res = value2 + "";
			break;
		case Cell.CELL_TYPE_FORMULA:
			break;
		case Cell.CELL_TYPE_STRING:
			String value3 = cell.getStringCellValue();
			res = value3 + "";
			break;
		case Cell.CELL_TYPE_ERROR:
			byte value4 = cell.getErrorCellValue();
			res = value4 + "";
			break;
		default:
			break;
		}
		return res;
	}
}
