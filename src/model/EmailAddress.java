package model;

import java.util.Properties;
import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

public class EmailAddress {


    public void  emailaddress(){
    String to = "andreeva.cr1stin@yandex.ru";//от
    String from = "kandreeva952@gmail.com";//кому-далее считать с поля формы
    String host = "127.0.0.1";//хост яндекса
    Properties properties = System.getProperties();
        properties.setProperty("smtp.yandex.ru", host);//протокол смтп яндекс

     Session session = Session.getDefaultInstance(properties);
        try {
        MimeMessage message = new MimeMessage(session);

        message.setFrom(new InternetAddress(from));

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
