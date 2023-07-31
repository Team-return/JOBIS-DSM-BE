package team.retum.jobis.common.spi;

public interface SesPort {

    void sendMail(String authCode, String sendTo);
}
