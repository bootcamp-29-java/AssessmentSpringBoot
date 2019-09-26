/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bootcamp.cobaspringapplication.services.impl;

import com.bootcamp.cobaspringapplication.services.IReportService;
import com.mysql.jdbc.Connection;
import java.sql.DriverManager;
import java.util.HashMap;
import java.util.Map;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.JasperRunManager;
import org.springframework.stereotype.Service;

/**
 *
 * @author ASUS
 */
@Service
public class ReportService implements IReportService {

    @Override
    public String generateReport(String id) {
        try {
            Connection con = null;
            try {
                Class.forName("com.mysql.jdbc.Driver");
                con = (Connection) DriverManager.getConnection("jdbc:mysql://localhost:3306/db_assessment", "root", "");

            } catch (Exception e) {
                e.printStackTrace();
            }

            String path = "E:\\Kerjaan Metrodata Group\\Project 3\\Git Project\\AssessmentSpringBoot\\src\\main\\resources\\reports\\";
            // Compile the Jasper report from .jrxml to .japser
            JasperReport jasperReport = JasperCompileManager
                    .compileReport(path + "reportAssessment.jrxml");

            // Add parameters
            Map<String, Object> parameters = new HashMap<>();

            parameters.put("id", id);

            // Fill the report
            JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, parameters, con);
            
            byte[] bytes = JasperRunManager.runReportToPdf(jasperReport, parameters,con);
            
            // Export the report to a PDF file
            JasperExportManager.exportReportToPdfFile(jasperPrint, "E:\\Kerjaan Metrodata Group\\Project 3\\Git Project\\AssessmentSpringBoot\\src\\main\\resources\\static\\reports\\reportAssessment.pdf");
            
            System.out.println("Done");

            return "ReportView";
        } catch (Exception e) {
            e.printStackTrace();
            return "Error--> check the console log";
        }
    }
}
