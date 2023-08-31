package team.retum.jobis.common.spi;

public interface SendEmailPort {

    void sendMail(String authCode, String sendTo);
}
