package model;

import javafx.scene.layout.AnchorPane;
import javafx.stage.FileChooser;
import sun.plugin.javascript.navig.Anchor;

import java.io.File;
import java.util.Properties;
import javax.activation.DataHandler;
import javax.activation.DataSource;
import javax.activation.FileDataSource;
import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;


public class EmailAddress {

    public void  emailaddress(AnchorPane ap){
        /*
        Properties properties= new Properties();
        properties.put("mail.smtp.host","smtp.yandex.ru");
        properties.put("mail.smtp.socketFactory.port",465);
        properties.put("mail.smtp.socketFactory.class","javax.net.ssl.SSLSocketFactory");
        properties.put("mail.smtp.auth","true");
        properties.put("mail.smtp.port",465);
        Session s=Session.getDefaultInstance(properties,
                new javax.mail.Authenticator()
                {protected PasswordAuthentication getPasswordAuthentication()
                {
                    return  new PasswordAuthentication("andreeva.cr1stin@yandex.ru", "Andr!1999"); }});
        try {
            Message message=new MimeMessage(s);
            message.setFrom( new InternetAddress("andreeva.cr1stin@yandex.ru"));//
            message.setRecipients(Message.RecipientType.TO,InternetAddress.parse("kandreeva952@gmail.com"));//получить из текстового поля
            message.setSubject("тестовое письмо");//получить из текстового поля
            message.setText("Проверка отправки письма");//получить из текстового поля
            MimeBodyPart messageBodyPart = new MimeBodyPart();

            Multipart multipart = new MimeMultipart();

            messageBodyPart = new MimeBodyPart();
            FileChooser fileChooser = new FileChooser();//класс работы с диалоговым окном
            fileChooser.setTitle("Выберите файл...");//заголовок диалога
            //задает фильтр для указанного расшиерения
            fileChooser.getExtensionFilters().addAll(new FileChooser.ExtensionFilter("Файл", "*.txt"),
                    new FileChooser.ExtensionFilter("Файл", "*.docx"));
            File file=fileChooser.showOpenDialog(anchorPane.getScene().getWindow());
            String str=file.getPath();//получаем строку с путем к файлу
            System.out.println(""+str);

            //  String file = "C:\\images\\test.txt";
         //   String fileName = "test.txt";//строка с названием файла

            DataSource source = new FileDataSource(str);
            messageBodyPart.setDataHandler(new DataHandler(source));
            messageBodyPart.setFileName(str);
            multipart.addBodyPart(messageBodyPart);

            message.setContent(multipart);

            Transport.send(message);
            System.out.println("Письмо отправлено");

         */
        Properties properties= new Properties();
        properties.put("mail.smtp.host","smtp.yandex.ru");
        properties.put("mail.smtp.socketFactory.port",465);
        properties.put("mail.smtp.socketFactory.class","javax.net.ssl.SSLSocketFactory");
        properties.put("mail.smtp.auth","true");
        properties.put("mail.smtp.port",465);
        Session s=Session.getDefaultInstance(properties,
                new javax.mail.Authenticator()
                {protected PasswordAuthentication getPasswordAuthentication()
                {
                    return  new PasswordAuthentication("andreeva.cr1stin@yandex.ru", "Andr!1999"); }});


        try {
            Message message=new MimeMessage(s);
            message.setFrom( new InternetAddress("andreeva.cr1stin@yandex.ru"));//
            message.setRecipients(Message.RecipientType.TO,InternetAddress.parse("kandreeva952@gmail.com"));
            message.setSubject("тестовое письмо");//тема письма
            message.setText("Проверка отправки письма");//текст письма
            MimeBodyPart messageBodyPart = new MimeBodyPart();

            Multipart multipart = new MimeMultipart();

            messageBodyPart = new MimeBodyPart();
            FileChooser fileChooser = new FileChooser();//класс работы с диалоговым окном
            fileChooser.setTitle("Выберите файл...");//заголовок диалога
            //задает фильтр для указанного расшиерения
            fileChooser.getExtensionFilters().addAll(new FileChooser.ExtensionFilter("Файл", "*.txt"),
                    new FileChooser.ExtensionFilter("Файл", "*.docx"));
            File file=fileChooser.showOpenDialog( ap.getScene().getWindow());
            String str=file.getPath();//получаем строку с путем к файлу
            System.out.println(""+str);
           DataSource source = new FileDataSource(str);
           messageBodyPart.setDataHandler(new DataHandler(source));
            messageBodyPart.setFileName(str);
            multipart.addBodyPart(messageBodyPart);

            message.setContent(multipart);
            Transport.send(message);
            System.out.println("Письмо отправлено");
        }
        catch (Exception ex)
        {
            System.out.println("Ошибка отправки сообщения"+ ex);
        }
    }
}
