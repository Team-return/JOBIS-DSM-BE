package team.returm.jobis.global.util.jms;

import javax.mail.internet.MimeMessage;
import lombok.RequiredArgsConstructor;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Component;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;
import team.returm.jobis.global.exception.MailSendException;

@RequiredArgsConstructor
@Component
public class JmsUtil {

    private final JavaMailSender javaMailSender;
    private final TemplateEngine templateEngine;

    public void sendMail(String email, String authenticationCode, String userName) {

        try {
            MimeMessage message = javaMailSender.createMimeMessage();
            MimeMessageHelper messageHelper = new MimeMessageHelper(message, false, "UTF-8");

            messageHelper.setTo(email);
            messageHelper.setSubject("[JOBIS 메일 인증]");

            String emailTemplate = templateEngine.process(
                    "signup-template",
                    generateContext(authenticationCode, userName)
            );
            messageHelper.setText(emailTemplate, true);

            javaMailSender.send(message);
        } catch (Exception e) {
            throw MailSendException.EXCEPTION;
        }
    }

    private Context generateContext(String authCode, String userName) {
        Context context = new Context();
        context.setVariable("code", authCode);
        context.setVariable("user", userName);

        return context;
    }
}
