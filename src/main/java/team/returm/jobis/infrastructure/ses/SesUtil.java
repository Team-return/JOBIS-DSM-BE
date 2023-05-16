package team.returm.jobis.infrastructure.ses;

import com.amazonaws.services.simpleemail.AmazonSimpleEmailServiceAsync;
import com.amazonaws.services.simpleemail.model.Body;
import com.amazonaws.services.simpleemail.model.Content;
import com.amazonaws.services.simpleemail.model.Destination;
import com.amazonaws.services.simpleemail.model.Message;
import com.amazonaws.services.simpleemail.model.SendEmailRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

@RequiredArgsConstructor
@Component
public class SesUtil {

    private final AmazonSimpleEmailServiceAsync amazonSimpleEmailServiceAsync;
    private final TemplateEngine templateEngine;
    private final AwsSesProperties awsSesProperties;

    public void sendMail(String authCode, String userName, String sendTo) {
        String emailTemplate = templateEngine.process(
                "signup-template",
                generateContext(authCode, userName)
        );

        Message message = new Message()
                .withSubject(generateContent("[JOBIS 메일 인증]"))
                .withBody(new Body().withHtml(generateContent(emailTemplate)));

        SendEmailRequest emailRequest = new SendEmailRequest()
                .withSource(awsSesProperties.getSource())
                .withMessage(message)
                .withDestination(new Destination().withToAddresses(sendTo));

        amazonSimpleEmailServiceAsync.sendEmailAsync(emailRequest);
    }

    private Context generateContext(String authCode, String userName) {
        Context context = new Context();
        context.setVariable("code", authCode);
        context.setVariable("user", userName);

        return context;
    }

    private Content generateContent(String data) {
        return new Content()
                .withCharset("UTF-8")
                .withData(data);
    }
}
