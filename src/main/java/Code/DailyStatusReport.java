package Code;

import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.File;
import java.io.FileInputStream;

import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Properties;

import static Code.MainRunner.sendEmail;
import static Code.table.checkClass.WebForm;

public class DailyStatusReport {


    public static String ExtractDataFromExcel() throws IOException {
        String path = "D:\\Perso Docs\\AdapsAssertTest\\src\\main\\resources\\AssertsData\\Adaps Assets Test.xlsx";
        FileInputStream fin = new FileInputStream(path);
        XSSFWorkbook workbook = new XSSFWorkbook(fin);
        XSSFSheet sheet = workbook.getSheet("DSR");
        return sheet.getRow(0).getCell(0).toString();
    }

    public static String Date() {
        LocalDateTime date = LocalDateTime.now();
        DateTimeFormatter pattern = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        return date.format(pattern);
    }

    public static String ExtractFromTextFile(String PropKey) throws IOException {
        File f = new File("D:\\Perso_Docs\\AdapsAssertTest\\src\\main\\resources\\AssertsData\\DSR.txt");
        FileInputStream fin = new FileInputStream(f);
        Properties pro = new Properties();
        pro.load(fin);
        return pro.get(PropKey).toString();
    }

    public static ArrayList Todaylist;
    public static ArrayList Tomarrolist;

    public static ArrayList getTodayTask(String whichDay) throws IOException {
        if (ExtractFromTextFile(whichDay).contains(",")) {
            Todaylist = new ArrayList();
            int size = ExtractFromTextFile(whichDay).split(",").length;
            for (int i = 0; i < size; i++) {
                Todaylist.add(ExtractFromTextFile(whichDay).split(",")[i].trim());
            }
        } else {
            Todaylist = new ArrayList();
            Todaylist.add(ExtractFromTextFile(whichDay));
        }
        return Todaylist;
    }

    public static ArrayList getTomarrowTask(String whichDay) throws IOException {
        if (ExtractFromTextFile(whichDay).contains(",")) {
            Tomarrolist = new ArrayList();
            int size = ExtractFromTextFile(whichDay).split(",").length;
            for (int i = 0; i < size; i++) {
                Tomarrolist.add(ExtractFromTextFile(whichDay).split(",")[i].trim());
            }
        } else {
            Tomarrolist = new ArrayList();
            Tomarrolist.add(ExtractFromTextFile(whichDay));
        }
        return Tomarrolist;
    }

    public static void SendDsrFromOutlook() throws IOException {
        getTodayTask("WhatIDidToday");
        getTomarrowTask("WhatIPlannedForTomorrow");

        String senderEmail = "salmanm@adaps.com";
        String senderPassword = "xywzkbzmwvvvxxxy";

        String bodyTemplate = """
                <!DOCTYPE html>
                <html>
                <head>
                    <style>
                        table {
                            border-collapse: collapse;
                            margin: 0;
                            border-top: 4px solid black;
                            border-bottom: 1px solid #999999;
                            border-left: 4px solid black;
                            border-right: 4px solid black;
                        }
                        .section-header {
                            background-color: #3399ff;
                            color: white;
                            padding: 3px;
                            font-weight: bold;
                        }
                        td {
                            border: 1px solid black;
                            padding: 10px;
                            text-align: left;
                        }
                        th {
                            padding: 4px;
                            text-align: left;
                            background-color: #f2f2f2;
                            border: 1px solid black;
                        }
                        tr {
                            border: 1px solid black;
                            padding: 10px;
                        }
                    </style>
                </head>
                <body>
                
                <p>Hi Parvathi,</p>
                <p>Please have a look at my status report.</p>
                
                <table style="width: 1000px; background-color: #0671C1; color: white;">
                    <tr>
                        <td style="padding: 3px;">What did we do this day?</td>
                    </tr>
                </table>
                <table style="width: 1000px;">
                    <tr>
                        <td>
                            <ul>%s</ul>
                        </td>
                    </tr>
                </table>
                
                <table style="width: 1000px; background-color: #0671C1; color: white;">
                    <tr>
                        <td style="padding: 3px;">What is planned for next day?</td>
                    </tr>
                </table>
                <table style="width: 1000px;">
                    <tr>
                        <td>
                            <ul>%s</ul>
                        </td>
                    </tr>
                </table>
                
                <table style="width: 1000px; background-color: #0671C1; color: white;">
                    <tr>
                        <td style="padding: 3px;">Impediments</td>
                    </tr>
                </table>
                <table style="width: 1000px;">
                    <thead>
                    <tr>
                        <th>Sl. No.</th>
                        <th>Description</th>
                        <th>Status</th>
                        <th>Start Date</th>
                        <th>Owner</th>
                    </tr>
                    </thead>
                    <tbody>
                    <tr>
                        <td>&nbsp;</td>
                        <td></td>
                        <td></td>
                        <td></td>
                        <td></td>
                    </tr>
                    <tr>
                        <td>&nbsp;</td>
                        <td></td>
                        <td></td>
                        <td></td>
                        <td></td>
                    </tr>
                    </tbody>
                </table>
                
                <br>
                <p>Best regards,</p>
                <p><span style="color:blue;">
                   <b>Salman Khan Mohammad</b><br>
                    Test Engineer<br>
                    <b>P</b>: 7995.645.056 |
                    <b>E</b>:<span style="color:blue; text-decoration: underline;">salmanm@adaps.com</span> |
                    <b><span style="color:blue;">W</span></b>: <a href="https://www.adaps.com" style="text-decoration:underline; color:blue;">www.adaps.com</a></span></p>
                
                </body>
                </html>
                """;

        StringBuilder Today = new StringBuilder();
        for (Object item : Todaylist) {
            Today.append("<li>").append(item.toString().replace("\"", "")).append("</li>");
        }
        StringBuilder Tomorrow = new StringBuilder();
        for (Object item : Tomarrolist) {
            Tomorrow.append("<li>").append(item.toString().replace("\"", "")).append("</li>");
        }
        String body = String.format(bodyTemplate, Today, Tomorrow);

//        sendEmail(senderEmail, senderPassword, "parvathiy@adaps.com", "charumathip@adaps.com, kiran@adaps.com,pmo@adaps.com", "Daily status report || HD-Korber-WMS ||" + Date(), body);

        sendEmail(senderEmail, senderPassword, "salmanm@adaps.com", "", "Daily status report || HD-Korber-WMS ||" + Date(), body);

        System.out.println("DSR sent through Outlook!!!");

    }


    public static void main(String[] args) throws IOException, InterruptedException {
        SendDsrFromOutlook();
//        WebForm();
    }
}
