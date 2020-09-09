package com.fun.business.sharon.utils;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.ArrayUtils;
import org.apache.poi.hssf.usermodel.HSSFFont;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.*;

import java.io.IOException;
import java.io.OutputStream;
import java.util.List;
import java.util.function.Function;

/**
 * @author liangxin
 * @Description: poi导入导出
 * @date 2020/5/28 0028 14:56
 */
@Slf4j
public class POIUtil {

    private static final int PAGE_SIZE = 5000;

    private static final int SHEET_SIZE = PAGE_SIZE * 2;

    /**
     * 创建自定义Excel文件
     * @param headers       列头数组
     * @param list          excel数据
     * @param dataFun       行级的数组组装，需要返回List&lt;List&lt;String&gt;&gt;
     * @param singleSheet   是否单sheet页输出
     * @param os            输出流
     * @throws IOException
     */
    public static <E> void createCustomExcel(String[] headers, List<E> list, Function<E, List<List<String>>> dataFun,
                                             boolean singleSheet, OutputStream os) throws IOException {
        if (ArrayUtils.isEmpty(headers) || list == null || os == null || null == dataFun) {
            log.warn("createExcel param error.");
            return;
        }

        HSSFWorkbook wb = new HSSFWorkbook();
        int size = list.size();
        int page = singleSheet ? 1 : (size - 1) / SHEET_SIZE + 1;
        int start = 0;
        int end = 0;
        for (int i = 1; i <= page; i++) {
            end = start + (i == page ? (size - SHEET_SIZE * (i - 1)) : SHEET_SIZE);
            List<E> sheetData = list.subList(start, end);
            Sheet sheet = wb.createSheet(String.format("Page %s", i));
            start = end;

            createCustomSheetHeader(wb, sheet, headers);
            Row row = null;
            int rowIndex = 0;
            for (E item : sheetData) {
                List<List<String>> rowsData = dataFun.apply(item);
                for (List<String> rowData : rowsData) {
                    row = sheet.createRow(++rowIndex);
                    for (int cellIndex = 0; cellIndex < rowData.size(); cellIndex++) {
                        row.createCell(cellIndex).setCellValue(rowData.get(cellIndex));
                    }
                }
            }
        }

        wb.write(os);
    }

    private static void createCustomSheetHeader(HSSFWorkbook wb, Sheet sheet, String[] header) {
        if (null == sheet) {
            return;
        }
        Row row = sheet.createRow(0);
        row.setHeightInPoints(40);//行高 5=8 41-->55

        CellStyle style = wb.createCellStyle();
        style.setAlignment(HorizontalAlignment.CENTER);//水平居中
        style.setVerticalAlignment(style.getVerticalAlignmentEnum().CENTER); //垂直居中

        style.setWrapText(true);//设置自动换行

        style.setFillForegroundColor(IndexedColors.LIGHT_BLUE.index);//背景颜色
        style.setFillPattern(FillPatternType.SOLID_FOREGROUND);//背景颜色
        style.setFillBackgroundColor(IndexedColors.LIGHT_BLUE.index);//背景颜色

        HSSFFont font = wb.createFont();
        font.setFontName("宋体");//字体样式
        font.setFontHeightInPoints((short) 16);//字体大小
        font.setBold(true);//粗体显示
        style.setFont(font);

        for (int j = 0; j < header.length; j++) {
            Cell cell = row.createCell(j);
            cell.setCellValue(header[j]);
            cell.setCellStyle(style);
        }

        sheet.setColumnWidth(0, 256 * 5 + 184);//列宽
        sheet.setColumnWidth(1, 256 * 20 + 184);//列宽
        sheet.setColumnWidth(2, 256 * 20 + 184);//列宽
        sheet.setColumnWidth(3, 256 * 20 + 184);//列宽
        sheet.setColumnWidth(4, 256 * 20 + 184);//列宽
        sheet.setColumnWidth(5, 256 * 20 + 184);//列宽
        sheet.setColumnWidth(6, 256 * 20 + 184);//列宽
        sheet.setColumnWidth(7, 256 * 15 + 184);//列宽
        sheet.setColumnWidth(8, 256 * 15 + 184);//列宽
        sheet.setColumnWidth(9, 256 * 15 + 184);//列宽
        sheet.setColumnWidth(10, 256 * 20 + 184);//列宽
        sheet.setColumnWidth(11, 256 * 20 + 184);//列宽
        sheet.setColumnWidth(12, 256 * 15 + 184);//列宽
        sheet.setColumnWidth(13, 256 * 15 + 184);//列宽
        sheet.setColumnWidth(14, 256 * 15 + 184);//列宽
        sheet.setColumnWidth(15, 256 * 15 + 184);//列宽
        sheet.setColumnWidth(16, 256 * 15 + 184);//列宽
        sheet.setColumnWidth(17, 256 * 15 + 184);//列宽
    }

}
