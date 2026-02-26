package Code;

import jakarta.mail.*;
import jakarta.mail.internet.AddressException;
import jakarta.mail.internet.InternetAddress;
import jakarta.mail.internet.MimeMessage;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

public class MainRunner {

    static String path = System.getProperty("user.dir")+"\\src\\main\\resources\\AssertsData\\Adaps Assets Test.xlsx";
    static FileInputStream fis;
    static XSSFWorkbook workbook;

    static {
        try {
            fis = new FileInputStream(path);
            workbook = new XSSFWorkbook(fis);
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    static XSSFSheet sheet = workbook.getSheet("Test");

    public MainRunner() throws IOException {
    }

    public static void main(String[] args) throws IOException {

        String senderEmail = "salmanm@adaps.com";
        String senderPassword = "xywzkbzmwvvvxxxy";


        HashMap<String, String> data = ReadFromHashmap("SHREEJA BOGURUMPETA");

        String bodyTemplate = """
                <html>
                <body style="font-family: Calibri, sans-serif;">
                <p>Hi %s,</p>
                <p>Hope you are doing well.<br>
                I hope this message finds you well. <br></br>
                
                 Kindly confirm that you have received and are currently using the following company-assigned assets:</p>
                
                <th>ASSET ASSIGNED USER --> <td>%s</td> </th><br>
                <th>DEVICE NAME --> <td>%s</td> </th><br></br>
                
                <table border="1" cellpadding="5" cellspacing="0" style="border-collapse: collapse;">
                
                    <tr>
                        <th>Assert Name</th>
                        <th>Model</th>
                        <th>Tag</th>
                        <th>SERIALNUMBER</th>
                    </tr>
                    <tr><td>LAPTOP </td>                 <td>%s</td><td>%s</td><td>%s</td></tr>
                    <tr><td>MOUSE </td>                <td>%s</td><td>%s</td><td>%s</td></tr>
                    <tr><td>HEADSET </td>               <td>%s</td><td>%s</td><td>%s</td></tr>
                    <tr><td>MONITOR </td>               <td>%s</td><td>%s</td><td>%s</td></tr>
                </table>
                
                <p>Kindly reply to this message with <b>Confirmed</b> if the above details are correct and the assets are in your possession.<br>
                    If there is any discrepancy or issue, please let us know immediately.</p>
                
                <p>Thanks & Regards,</p>
                <p><span style="color:blue;">
                   <b>Krishna puli</b><br>
                    System Administrator<br>
                    Divyasree Trinity, Level 2, C-Wing, Plot No: 5&6<br>
                    Madhapur, Hitec City Road, Hyderabad 500081<br>
                    <b>P</b>: 9515.373.783 |
                    <b>E</b>:<span style="color:blue; text-decoration: underline;">krishnap@adaps.com</span>|
                    <b><span style="color:blue;">W</span></b>: <a href="https://www.adaps.com" style="text-decoration:underline; color:blue;">www.adaps.com</a></span></p>
                </body>
                </html>
                """;


        String body = String.format(bodyTemplate,
                data.get("ASSET ASSIGNED USER"),
                data.get("ASSET ASSIGNED USER"),
                data.get("DEVICE NAME"),
                data.get("LAPTOP MODEL"),
                data.get("LAPTOP TAG(COMPANY TAG)"),
                data.get("LAPTOP SERIALNUMBER"),
                data.get("MOUSE MODEL"),
                data.get("MOUSE TAG"),
                data.get("MOUSE SERIALNUMBER"),
                data.get("HEADSET MODEL"),
                data.get("HEADSET TAG"),
                data.get("HEADSET SERIALNUMBER"),
                data.get("MONITOR MODEL"),
                data.get("MONITOR TAG"),
                data.get("MONITOR SERILANUMBER"));

        sendEmail(senderEmail, senderPassword, "salmanm@adaps.com", "","Asset Confirmation", body);

    }


    public static void sendEmail(String fromEmail, String password, String toEmail, String cc,String subject, String body) {
        Properties props = new Properties();
        props.put("mail.smtp.host", "smtp.office365.com");
        props.put("mail.smtp.port", "587");
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");

        Session session = Session.getInstance(props, new Authenticator() {
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(fromEmail, password);
            }
        });

        try {
            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress(fromEmail));
            message.setRecipients(
                    Message.RecipientType.TO,
                    InternetAddress.parse(toEmail));
            message.setRecipients(
                    Message.RecipientType.CC,
                    InternetAddress.parse(cc)
            );

            message.setSubject(subject);
//            message.setText(body);
            message.setContent(body, "text/html; charset=utf-8");
            Transport.send(message);

        } catch (MessagingException e) {
            e.printStackTrace();
        }
    }

    public static HashMap<String, String> ReadFromHashmap(String User) throws IOException {
        HashMap<String, String> data;
        data = new HashMap<>();
        for (int i = 1; i <= sheet.getLastRowNum(); i++) {
            if (sheet.getRow(i).getCell(1).toString().equalsIgnoreCase(User)) {
                Row rowHeader = sheet.getRow(0);
                Row rowValue = sheet.getRow(i);
                for (int j = 0; j < rowValue.getLastCellNum(); j++) {
                    Cell cell = rowHeader.getCell(j);
                    String Header = cell.toString();
                    Cell cell1 = rowValue.getCell(j);
                    String Value = cell1.toString();
                    data.put(Header, Value);
                }
                break;
            }
        }
        return data;
    }


}
