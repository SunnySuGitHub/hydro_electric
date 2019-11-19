package com.hust.hydroelectric_backend.utils;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.InputStream;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

/**
 * @author: suxinyu
 * @DateTme: 2019/11/19 11:03
 */
public class ExcelImportUtil {

    private static DecimalFormat df = new DecimalFormat("0");
    private static SimpleDateFormat fmt = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss"); //日期格式yyyy-mm-dd HH:mm:ss


    //判断是否2003
    public static boolean isExcel2003(String filePath){
        if(filePath.endsWith("xls")){
            return true;
        }else {
            return false;
        }
    }

    //判断是否03以上
    public static boolean isExcel2007(String filePath){
        if(filePath.endsWith("xlsx")){
            return true;
        }else {
            return false;
        }
    }

    public static Object[] readSheets(InputStream is, boolean isExcel2003) {
        Object[] obs = new Object[2];
        Workbook workbook = null;
        String[] blockName;
        try{
            if(isExcel2003){
                workbook = new HSSFWorkbook(is);
            } else {
                workbook = new XSSFWorkbook(is);
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        List<List<String >>[] res =  new ArrayList[workbook.getNumberOfSheets()];//双层list加一个数组，[i]代表一个双层List，代表一个sheet的所有数据
        blockName = new String[workbook.getNumberOfSheets()];
        for(int i = 0 ; i  < workbook.getNumberOfSheets(); i++){
            res[i] = read(workbook , i);
            blockName[i] = workbook.getSheetName(i);
        }
        obs[0] = res;
        obs[1] = blockName;
        return obs;
    }

    public static List<List<String>> read(Workbook workbook, int i){
        int totalRows = 0;
        int totalCells = 0;
        //获取当前workbook第i个sheet的所有数据
        List<List<String>> dataList3 = new ArrayList<>();
        //sheet的索引从0开始，第一张表是0，i代表有几页
        workbook.getNumberOfSheets();
        Sheet sheet = workbook.getSheetAt(i);
        //得到当前表的行数
        totalRows = sheet.getPhysicalNumberOfRows();
        //得到当前表的列数
        if(totalRows>0 && sheet.getRow(0)!=null){
            totalCells = sheet.getRow(1).getPhysicalNumberOfCells();
        }
        //两个for循环遍历每一行的每一个Cell
        for(int r = 0;r < sheet.getPhysicalNumberOfRows();r++){
            Row row = sheet.getRow(r);
            if(row == null){
                continue;
            }
            List<String> rowList = new ArrayList<>();
            for(int c = 0;c<totalCells;c++){
                Cell cell = row.getCell(c);
                String cellValue = null;
                if(cell != null){
                    //判断读取的数据类型
                    switch (cell.getCellType()) {
                        case XSSFCell.CELL_TYPE_NUMERIC:
                            if (DateUtil.isCellDateFormatted(cell)) {
                                cellValue = cell.getDateCellValue().getTime()/1000 + ""; //日期型
                            } else {
                                cellValue = df.format(cell.getNumericCellValue()); //数字型
                            }
                            break;
                        case XSSFCell.CELL_TYPE_STRING: //文本类型
                            cellValue = cell.getStringCellValue();
                            break;
                        case XSSFCell.CELL_TYPE_BOOLEAN: //布尔型
                            cellValue = String.valueOf(cell.getBooleanCellValue());
                            break;
                        case XSSFCell.CELL_TYPE_BLANK: //空白
                            cellValue = "";
                            break;
                        case XSSFCell.CELL_TYPE_ERROR: //错误
                            cellValue = "错误";
                            break;
                        case XSSFCell.CELL_TYPE_FORMULA: //公式
                            try {
                                cellValue = String.valueOf(cell.getStringCellValue());
                            } catch (IllegalStateException e) {
                                cellValue = String.valueOf(cell.getNumericCellValue());
                            }
                            break;
                        default:
                            cellValue = cell.getRichStringCellValue() == null ? null : cell.getRichStringCellValue().toString();
                    }
                }
                rowList.add(cellValue);//保存某行每一个cell的值
            }
            dataList3.add(rowList);
        }
        return dataList3;
    }
}
