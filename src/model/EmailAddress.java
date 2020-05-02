package model;

import java.util.Properties;
import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

public class EmailAddress {


    public void  emailaddress(){
    String to = "kandreeva952@gmail.com";//от
        String password="Andr!1999";
    String from = "andreeva.cr1stin@yandex.ru";//кому-далее считать с поля формы
    String host = "587";//хост яндекса
    Properties properties = System.getProperties();
        properties.setProperty("smtp.yandex.ru", host);//протокол смтп яндекс

     Session session = Session.getDefaultInstance(properties, new javax.mail.Authenticator() {
         protected PasswordAuthentication getPasswordAuthentication() {
             return new PasswordAuthentication("andreeva.cr1stin@yandex.ru", "Andr!1999"); //логин и пароль для аутентификации на сервере
         }

     });
        try {
        MimeMessage message = new MimeMessage(session);
        message.setFrom(new InternetAddress(from));//от
        message.addRecipient(Message.RecipientType.TO, new InternetAddress(to));
        message.setSubject("Тема");//тема
        message.setText("Текст сообщения");//сообщение-даллее добавить файл сформированный
        Transport.send(message);
        System.out.println("Email Sent successfully....");
    }
        catch (
    MessagingException mex)
        { mex.printStackTrace(); }


}




}
