package com.dewen.utils;

import com.dewen.entity.Product;
import com.lowagie.text.Document;
import com.lowagie.text.DocumentException;
import com.lowagie.text.Font;
import com.lowagie.text.Phrase;
import com.lowagie.text.pdf.BaseFont;
import com.lowagie.text.pdf.PdfPCell;
import com.lowagie.text.pdf.PdfPTable;
import com.lowagie.text.pdf.PdfWriter;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;

public class PdfUtil {

    public void createPDF(Document document, PdfWriter writer, List<Product> products) throws IOException {
        //Document document = new Document(PageSize.A4);
        try {
            document.addTitle("sheet of product");
            document.addAuthor("scurry");
            document.addSubject("product sheet.");
            document.addKeywords("product.");
            document.open();
            PdfPTable table = createTable(writer,products);
            document.add(table);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (DocumentException e) {
            e.printStackTrace();
        } finally {
            document.close();
        }
    }
    public static PdfPTable createTable(PdfWriter writer,List<Product> products) throws IOException, DocumentException {
        PdfPTable table = new PdfPTable(3);//生成一个两列的表格
        PdfPCell cell;
        int size = 20;
        Font font = new Font(BaseFont.createFont("C://Windows//Fonts//simfang.ttf", BaseFont.IDENTITY_H,
                BaseFont.NOT_EMBEDDED));
        for(int i = 0;i<products.size();i++) {
            cell = new PdfPCell(new Phrase(products.get(i).getProductCode(),font));//产品编号
            cell.setFixedHeight(size);
            table.addCell(cell);
            cell = new PdfPCell(new Phrase(products.get(i).getProductName(),font));//产品名称
            cell.setFixedHeight(size);
            table.addCell(cell);
            cell = new PdfPCell(new Phrase(products.get(i).getPrice()+"",font));//产品价格
            cell.setFixedHeight(size);
            table.addCell(cell);
        }
        return table;
    }
}