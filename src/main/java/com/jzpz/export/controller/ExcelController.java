package com.jzpz.export.controller;

import com.jzpz.domain.MediaInfo;
import com.jzpz.export.utils.ExcelUtil;
import com.jzpz.export.utils.WordUtil;
import com.jzpz.service.MediaInfoService;
import com.jzpz.util.Constants;
import org.apache.poi.xssf.usermodel.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;
import java.util.Date;
import java.util.List;

/**
 * @author weiQiang
 */
@RestController
public class ExcelController {

    @Autowired
    private MediaInfoService mediaInfoService;

    @RequestMapping(value = "exportMediaInfo", method = RequestMethod.GET)
    public void exportMediaInfo(HttpServletResponse response) {
        try {
            List<MediaInfo> mediaInfos = (List<MediaInfo>) mediaInfoService.getAllUploadImg().getData();
            XSSFWorkbook wb = new XSSFWorkbook();
            // 表头字体
            XSSFCellStyle style = ExcelUtil.excelTableHeadCellStyle(wb);

            XSSFSheet sheet = wb.createSheet("媒体信息");
            // 设置sheet页名称
            XSSFRow row0 = sheet.createRow(0);

            XSSFCell xssfCell0 = row0.createCell(0);
            xssfCell0.setCellValue("媒体ID");
            xssfCell0.setCellStyle(style);

            XSSFCell xssfCell1 = row0.createCell(1);
            xssfCell1.setCellValue("媒体名称");
            xssfCell1.setCellStyle(style);

            XSSFCell xssfCell01 = row0.createCell(2);
            xssfCell01.setCellValue("备注");
            xssfCell01.setCellStyle(style);

            XSSFCell xssfCell02 = row0.createCell(3);
            xssfCell02.setCellValue("录入者");
            xssfCell02.setCellStyle(style);

            XSSFCell xssfCell03 = row0.createCell(4);
            xssfCell03.setCellValue("录入时间");
            xssfCell03.setCellStyle(style);
            xssfCell03.setAsActiveCell();

            // 表内字体
            XSSFCellStyle cellStyle = ExcelUtil.excelTableContextCellStyle(wb);
            int rowIndex = 1;
            for (MediaInfo mediaInfo : mediaInfos) {
                XSSFRow row = sheet.createRow(rowIndex);

                XSSFCell xssfCell00 = row.createCell(0);
                xssfCell00.setCellValue(mediaInfo.getMediaId());
                xssfCell00.setCellStyle(cellStyle);

                XSSFCell xssfCell = row.createCell(1);
                xssfCell.setCellValue(mediaInfo.getMediaName());
                xssfCell.setCellStyle(cellStyle);
                //sheet.setColumnWidth(1, (mediaInfo.getMediaName().length()) * 256 );

                XSSFCell xssfCell2 = row.createCell(2);
                xssfCell2.setCellValue(mediaInfo.getRemark());
                xssfCell2.setCellStyle(cellStyle);

                XSSFCell xssfCell3 = row.createCell(3);
                xssfCell3.setCellValue(mediaInfo.getInsertUser().getRealName());
                xssfCell3.setCellStyle(cellStyle);

                XSSFCell xssfCell4 = row.createCell(4);
                xssfCell4.setCellValue(mediaInfo.getInsertTime());
                xssfCell4.setCellStyle(cellStyle);
                rowIndex++;
            }
            response.setContentType("application/vnd.ms-excel");
            String fileName = "媒体信息" + Constants.formatDate(new Date(), "yyyyMMddHHmmss") + ".xlsx";
            fileName = new String(fileName.getBytes("GB2312"), "ISO_8859_1");
            response.setHeader("Content-disposition", "attachment;filename=" + fileName);
            wb.write(response.getOutputStream());
            response.getOutputStream().close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @RequestMapping(value = "exportMediaDoc", method = RequestMethod.GET)
    public void exportMediaDoc(Integer mediaId, HttpServletResponse response) {
        String mediaInfo = mediaInfoService.findMediaById(mediaId);
        String path = "e:\\poi\\";
        String fileName = "poi.docx";
        String filePath = path + fileName;
        //创建word
        WordUtil.createWord(path, fileName);
        //写入数据
        //"本文是以poi3.9读写2010word、2010excel、2010ppt,记录学习的脚步相应的功能在代码都有注释,就不解释了 详情可以参看poi3.9的文档主测试函数 TestMain.java";
        String data = mediaInfo;
        WordUtil.writeDataDocx(filePath, data, true, 12);
//        WordUtil.writeDataDoc(filePath,data);

        //读取数据
        //String contentWord=WordUtils.readDataDoc(filePath);
        String contentWord = WordUtil.readDataDocx(filePath);
        System.out.println("word的内容为:\n" + contentWord);
        System.out.println();
    }
}
