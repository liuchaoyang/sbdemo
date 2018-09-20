package com.example.sbdemo.util;

import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class ExportExcel {

    public static void export(HttpServletResponse response) throws IOException {
        //1.在内存中创建一个excel文件
        HSSFWorkbook hssfWorkbook = new HSSFWorkbook();
        //2.创建工作簿
        HSSFSheet sheet = hssfWorkbook.createSheet();
        //3.创建标题行
        HSSFRow titleRow = sheet.createRow(0);
        titleRow.createCell(0).setCellValue("姓名");
        titleRow.createCell(1).setCellValue("手机号");

        //4.遍历数据,创建数据行
        for(int i=0; i<2; i++) {
            int lastRowNum = sheet.getLastRowNum();
            HSSFRow row = sheet.createRow(lastRowNum + 1);
            row.createCell(0).setCellValue("people" + lastRowNum);
            row.createCell(1).setCellValue("mobile" + lastRowNum);
        }

        //5.获取输出流对象
        ServletOutputStream outputStream = response.getOutputStream();

        //9.设置信息头
        response.setHeader("Content-Disposition","attachment;filename=xxx.xls");
        //10.写出文件,关闭流
        hssfWorkbook.write(outputStream);
        hssfWorkbook.close();

    }
}
