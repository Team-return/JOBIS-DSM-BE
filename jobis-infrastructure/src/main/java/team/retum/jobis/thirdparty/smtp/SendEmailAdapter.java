package team.retum.jobis.thirdparty.smtp;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;
import team.retum.jobis.common.spi.SendEmailPort;
import team.retum.jobis.thirdparty.smtp.SmtpEmailService.SendEmailRequest;

@RequiredArgsConstructor
@Component
public class SendEmailAdapter implements SendEmailPort  {
    private final SmtpProperties smtpProperties;
    private final TemplateEngine templateEngine;
    private final SmtpEmailService smtpEmailService;

    public void sendMail(String authCode, String sendTo) {
        String emailTemplate = templateEngine.process(
            "signup-template",
            generateContext(authCode)
        );

        SendEmailRequest request = SendEmailRequest.builder()
            .to(sendTo)
            .from(smtpProperties.getUsername())
            .message(emailTemplate)
            .subject("[JOBIS 메일 인증]")
            .build();
        smtpEmailService.sendEmailAsync(request);
    }

    private Context generateContext(String authCode) {
        Context context = new Context();
        context.setVariable("code", authCode);

        return context;
    }
}
