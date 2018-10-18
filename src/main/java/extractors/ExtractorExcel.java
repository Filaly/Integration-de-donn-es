/*
package main.java.extractors;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URL;
import java.sql.*;

import org.apache.poi.hssf.usermodel.*;
import org.apache.poi.hssf.util.CellReference;
import org.apache.poi.poifs.filesystem.POIFSFileSystem;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;


public class ExtractorExcel {

    public ExtractorExcel() {
        super();
    }


    public void connect() {
        try {
            Class.forName("com.hxtt.sql.excel.ExcelDriver");
        } catch (ClassNotFoundException ex) {
            System.err.println("Erreur de chargement du driver.");
            return;
        }

        try {
            System.out.println("yeah");
            URL url = getClass().getResource("../modele/Source1.xls");
            Connection conn = DriverManager.getConnection("jdbc:excel:///" + url.getPath());
            System.out.println(url.getPath());
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
            System.err.println("Erreur de connection à la base de données.");
        }
    }


    public void connexion() throws SQLException, ClassNotFoundException {
        String fileName = "modele/Source1.xls";


        try {


            FileInputStream fis = new FileInputStream(fileName);


            Workbook workbook = null;
            if (fileName.toLowerCase().endsWith("xlsx")) {
                workbook = new XSSFWorkbook(fis);
            } else if (fileName.toLowerCase().endsWith("xls")) {
                workbook = new HSSFWorkbook(fis);
            }


            Sheet sheet = null;
            if (workbook != null) {
                sheet = workbook.getSheetAt(0);

            }

            FormulaEvaluator evaluator = null;
            if (workbook != null) {
                evaluator = workbook.getCreationHelper().createFormulaEvaluator();

            }

            // suppose your formula is in B3
            CellReference cellReference = new CellReference("B1");
            Row row = sheet.getRow(cellReference.getRow());
            Cell cell = row.getCell(cellReference.getCol());
            System.out.println(cell);


        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }


}*/
