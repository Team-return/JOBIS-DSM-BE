package team.retum.jobis.thirdparty.smtp;

import jakarta.mail.internet.MimeMessage;
import lombok.Builder;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@Component
public class SmtpEmailService {
    private final JavaMailSender javaMailSender;

    @Async
    @SneakyThrows
    public void sendEmailAsync(SendEmailRequest request) {
        MimeMessage message = javaMailSender.createMimeMessage();

        MimeMessageHelper helper = new MimeMessageHelper(message, true, "UTF-8");

        helper.setFrom(request.from());
        helper.setTo(request.to());
        helper.setSubject(request.subject());

        helper.setText(request.message(), true);

        javaMailSender.send(message);
    }

    @Builder
    public record SendEmailRequest(
        String to,
        String subject,
        String message,
        String from
    ) { }
}
