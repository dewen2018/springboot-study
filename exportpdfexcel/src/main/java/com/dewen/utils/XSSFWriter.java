package com.dewen.utils;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.util.CellReference;
import org.apache.poi.xssf.streaming.SXSSFWorkbook;
import org.junit.Assert;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URL;

/**
 * poi官网给了一种大批量数据写入的方法，使用SXXFWorkbook类进行大批量写入操作解决了这个问题，
 * 可以监控该样例，我们会发现整体内存呈现锯齿状，能够及时回收，内存相对比较平稳。
 */
public class XSSFWriter {

    private static SXSSFWorkbook wb;

    public static void main(String[] args) throws IOException {
        wb = new SXSSFWorkbook(10000);
        Sheet sh = wb.createSheet();
        for (int rownum = 0; rownum < 100000; rownum++) {
            Row row = sh.createRow(rownum);
            for (int cellnum = 0; cellnum < 10; cellnum++) {
                Cell cell = row.createCell(cellnum);
                String address = new CellReference(cell).formatAsString();
                cell.setCellValue(address);
            }

        }

        // Rows with rownum < 900 are flushed and not accessible
        for (int rownum = 0; rownum < 90000; rownum++) {
            Assert.assertNull(sh.getRow(rownum));
        }

        // ther last 100 rows are still in memory
        for (int rownum = 90000; rownum < 100000; rownum++) {
            Assert.assertNotNull(sh.getRow(rownum));
        }
        URL url = XSSFWriter.class.getClassLoader().getResource("");

        FileOutputStream out = new FileOutputStream(url.getPath() + File.separator + "wirter.xlsx");
        wb.write(out);
        out.close();

        // dispose of temporary files backing this workbook on disk  
        wb.dispose();
    }
}
