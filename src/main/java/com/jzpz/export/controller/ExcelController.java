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

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Date;
import java.util.List;

@RestController
public class ExcelController {

    @Autowired
    private MediaInfoService mediaInfoService;

    @RequestMapping(value = "exportMediaInfo",method = RequestMethod.GET)
    public void exportMediaInfo(HttpServletResponse response){
        try {
            List<MediaInfo> mediaInfos = (List<MediaInfo>) mediaInfoService.getAllUploadImg().getData();
            XSSFWorkbook wb = new XSSFWorkbook();
            XSSFCellStyle style = ExcelUtil.excelTableHeadCellStyle(wb);// 表头字体

            XSSFSheet sheet = wb.createSheet("媒体信息");
            XSSFRow row0 = sheet.createRow(0);// 设置sheet页名称

            XSSFCell xssfCell0_0 = row0.createCell(0);
            xssfCell0_0.setCellValue("媒体ID");
            xssfCell0_0.setCellStyle(style);

            XSSFCell xssfCell0_11 = row0.createCell(1);
            xssfCell0_11.setCellValue("媒体名称");
            xssfCell0_11.setCellStyle(style);

            XSSFCell xssfCell0_1 = row0.createCell(2);
            xssfCell0_1.setCellValue("备注");
            xssfCell0_1.setCellStyle(style);

            XSSFCell xssfCell0_2 = row0.createCell(3);
            xssfCell0_2.setCellValue("录入者");
            xssfCell0_2.setCellStyle(style);

            XSSFCell xssfCell0_3 = row0.createCell(4);
            xssfCell0_3.setCellValue("录入时间");
            xssfCell0_3.setCellStyle(style);
            xssfCell0_3.setAsActiveCell();

            XSSFCellStyle cellStyle = ExcelUtil.excelTableContextCellStyle(wb);// 表内字体
            int rowIndex = 1;
            for (MediaInfo mediaInfo : mediaInfos) {
                XSSFRow row = sheet.createRow(rowIndex);

                XSSFCell xssfCell_0 = row.createCell(0);
                xssfCell_0.setCellValue(mediaInfo.getMediaId());
                xssfCell_0.setCellStyle(cellStyle);

                XSSFCell xssfCell_01 = row.createCell(1);
                xssfCell_01.setCellValue(mediaInfo.getMediaName());
                xssfCell_01.setCellStyle(cellStyle);
                //sheet.setColumnWidth(1, (mediaInfo.getMediaName().length()) * 256 );

                XSSFCell xssfCell_1 = row.createCell(2);
                xssfCell_1.setCellValue(mediaInfo.getRemark());
                xssfCell_1.setCellStyle(cellStyle);

                XSSFCell xssfCell_2 = row.createCell(3);
                xssfCell_2.setCellValue(mediaInfo.getInsertUser().getRealName());
                xssfCell_2.setCellStyle(cellStyle);

                XSSFCell xssfCell_3 = row.createCell(4);
                xssfCell_3.setCellValue(mediaInfo.getInsertTime());
                xssfCell_3.setCellStyle(cellStyle);
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

    @RequestMapping(value = "exportMediaDoc",method = RequestMethod.GET)
    public void exportMediaDoc(Integer mediaId,HttpServletResponse response){
        String  mediaInfo = mediaInfoService.findMediaById(mediaId);
        String path = "e:\\poi\\";
        String fileName = "poi.docx";
        String filePath = path + fileName;
        //创建word
        WordUtil.createWord(path,fileName);
        //写入数据
        String data = mediaInfo;//"本文是以poi3.9读写2010word、2010excel、2010ppt,记录学习的脚步相应的功能在代码都有注释,就不解释了 详情可以参看poi3.9的文档主测试函数 TestMain.java";
        WordUtil.writeDataDocx(filePath,data,true,12);
//        WordUtil.writeDataDoc(filePath,data);

        //读取数据
        //String contentWord=WordUtils.readDataDoc(filePath);
        String contentWord=WordUtil.readDataDocx(filePath);
        System.out.println("word的内容为:\n"+contentWord);
        System.out.println();
    }
}
