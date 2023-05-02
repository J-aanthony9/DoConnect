package cogent.university.com.DoConnectBackend.service;



import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;


@Service
public class EmailService {
    @Autowired
    private JavaMailSender mailSender;

    public void sendEmail(String to, String body, String subject )  {

//        String sender = "dksfja@gmail.com";
//        MimeMessage message = mailSender.createMimeMessage();
//
//
//
//        message.setFrom(String.valueOf(new InternetAddress(sender)));
//        message.setRecipients(MimeMessage.RecipientType.TO, "recipient@example.com");
//        message.setSubject("Test email from Spring");
//
//        String htmlContent = "<h1>This is a test Spring Boot email</h1>" +
//                "<p>It can contain <strong>HTML</strong> content.</p>";
//        message.setContent(htmlContent, "text/html; charset=utf-8");
//
//        mailSender.send(message);


//             Creating a simple mail message
            SimpleMailMessage mailMessage
                    = new SimpleMailMessage();

            // Setting up necessary details
            mailMessage.setFrom("zomgitzjohn@gmail.com");
            mailMessage.setTo(to);
            mailMessage.setText(body);
            mailMessage.setSubject(subject);

            // Sending the mail
            mailSender.send(mailMessage);

    }
}
