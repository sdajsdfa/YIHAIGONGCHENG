package com.yhgc.api.util;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.*;
import javax.servlet.http.HttpServletResponse;
import java.io.OutputStream;
import java.util.List;

/**
 * excel的工具类
 */
public class ExcelTool {

    /**
     * 需要传入哪些数据？
     *      filename:文件名称
     *      String[] heads:表格的头部
     *      List<String[]> list:表格中的数据
     *      response
     */
    public static void export(String filename,String[] heads, List<String[]> list, HttpServletResponse response) throws Exception{
        //1.创建一个Excel文件(在内存中)
    	HSSFWorkbook wb = new HSSFWorkbook();
        //2.创建一个表
        Sheet sheet = wb.createSheet();

        //一.创建头部
        // 1.1 创建第一行
        Row row = sheet.createRow(0);
        // 1.2 创建相应的列(第一行的格子) - heads有多长，就有多少个格式
        for (int i = 0; i < heads.length; i++) {
            //1.3 创建格子
            Cell headCell = row.createCell(i);
            //1.4 把数据放进去
            headCell.setCellValue(heads[i]);
        }
        //二.创建相应的数据展示
        // 2.1 创建相应的行(从第二行开始) - list有多长，就有多少行
        for (int i = 0; i < list.size(); i++) {
            //2.2 创建这一行
            Row dataRow = sheet.createRow(i + 1);
            //2.3 创建这一行对应的列(格子) 
            // 拿到这一行的数据
            String[] strings = list.get(i);
            for (int j = 0; j < strings.length; j++) {
                Cell cell = dataRow.createCell(j);
                //2.4 把数据放到格子中
                cell.setCellValue(strings[j]);
            }
        }

        //下面设置好直接使用即可导出
        response.setContentType("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet"); //mime类型
        response.setHeader("Content-disposition", "attachment;filename="+filename );
        response.setHeader("Pragma", "No-cache");//设置不要缓存
        OutputStream ouputStream = response.getOutputStream();
        wb.write(ouputStream); //把excel文件写到输出流中
        ouputStream.flush();
        ouputStream.close();

    }
}
