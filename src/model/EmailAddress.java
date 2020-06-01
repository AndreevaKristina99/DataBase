package model;
import javafx.scene.layout.AnchorPane;
import javafx.stage.FileChooser;
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
    /**
     * класс который позволяет отправлять составленное резюме на указанную электроннуб почту
     * @param ap
     * @param emAddress
     */
    public void emailaddress(AnchorPane ap,String emAddress) {
        /**
         * устанавливаем моединение по протоколу smtp
         * указываем параметры почты с которой будет отправлено письмо
         * в качетсве параметра принимает адрес получателя
         * путь к файлу указывается статически
         */
        Properties properties = new Properties();
        properties.put("mail.smtp.host", "smtp.yandex.ru");
        properties.put("mail.smtp.socketFactory.port", 465);
        properties.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
        properties.put("mail.smtp.auth", "true");
        properties.put("mail.smtp.port", 465);
        Session s = Session.getDefaultInstance(properties,
                new javax.mail.Authenticator() {
                    protected PasswordAuthentication getPasswordAuthentication() {
                        return new PasswordAuthentication("andreeva.cr1stin@yandex.ru", "Andr!1999");
                    }
                });
        try {
            Message message = new MimeMessage(s);
            message.setFrom(new InternetAddress("andreeva.cr1stin@yandex.ru"));//
            message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(emAddress/*"kandreeva952@gmail.com"*/));
            message.setSubject("Резюме_Андреева К.А.");//тема письма
            message.setText("Проверка отправки письма");//текст письма
            MimeBodyPart messageBodyPart = new MimeBodyPart();
            Multipart multipart = new MimeMultipart();
            messageBodyPart = new MimeBodyPart();
            FileChooser fileChooser = new FileChooser();//класс работы с диалоговым окном
            fileChooser.setTitle("Выберите файл...");//заголовок диалога
            DataSource source = new FileDataSource("C:\\images\\news3.txt");
            messageBodyPart.setDataHandler(new DataHandler(source));
            messageBodyPart.setFileName("C:\\images\\news3.txt");
            multipart.addBodyPart(messageBodyPart);
            message.setContent(multipart);
            Transport.send(message);
            System.out.println("Письмо отправлено");
        } catch (Exception ex) {
            System.out.println("Ошибка отправки сообщения" + ex);
        }
    }
}
