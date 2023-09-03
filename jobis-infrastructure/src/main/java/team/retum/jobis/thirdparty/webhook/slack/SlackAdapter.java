package team.retum.jobis.thirdparty.webhook.slack;

import lombok.RequiredArgsConstructor;
import net.gpedro.integrations.slack.SlackApi;
import net.gpedro.integrations.slack.SlackAttachment;
import net.gpedro.integrations.slack.SlackException;
import net.gpedro.integrations.slack.SlackField;
import net.gpedro.integrations.slack.SlackMessage;
import org.springframework.stereotype.Component;
import team.retum.jobis.domain.bug.model.BugReport;
import team.retum.jobis.thirdparty.s3.S3Properties;
import team.retum.jobis.thirdparty.webhook.WebhookUtil;

import javax.servlet.http.HttpServletRequest;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.Collections;
import java.util.Date;
import java.util.List;

@RequiredArgsConstructor
@Component
public class SlackAdapter implements WebhookUtil {

    private static final String FALLBACK = "Ok";
    private static final String COLOR = "danger";
    private static final String BUG_REPORT_TITLE = "버그 제목";
    private static final String CONTENT = "버그 내용";
    private static final String DEVELOPMENT_AREA = "버그가 발생한 분야";
    private static final String WRITER = "버그 신고한 유저";
    private static final String EXCEPTION_TITLE = "예외 발생";
    private static final String URL = "Request URL";
    private static final String METHOD = "Request Method";
    private static final String CURRENT_TIME = "Request Time";
    private static final String IP = "Request IP";
    private static final String USER_AGENT = "Request User-Agent";
    private static final String BUG_TEXT = "버그 제보가 도착했습니다.";
    private static final String EXCEPTION_TEXT = "서버 에러 발생 😱😱😱";

    private final S3Properties s3Properties;

    private final SlackApi slackApi;

    @Override
    public void sendBugReport(BugReport bugReport, String writer) {
        List<SlackAttachment> slackAttachments;

        if (bugReport.getBugAttachments().isEmpty()) {
            slackAttachments = createBugReportSlackAttachments(bugReport, writer);
        } else {
            slackAttachments = createBugReportSlackAttachmentsWithImage(bugReport, writer);
        }

        sendSlackMessage(BUG_TEXT, slackAttachments);
    }

    @Override
    public void sendExceptionInfo(HttpServletRequest request, Exception exception) {
        SlackAttachment slackAttachment = createExceptionSlackAttachment(request, exception);

        sendSlackMessage(EXCEPTION_TEXT, Collections.singletonList(slackAttachment));
    }

    private List<SlackAttachment> createBugReportSlackAttachments(BugReport bugReport, String writer) {
        SlackAttachment slackAttachment = new SlackAttachment();

        List<SlackField> slackFields = createBugReportSlackFields(bugReport, writer);

        slackAttachment.setFallback(FALLBACK);
        slackAttachment.setColor(COLOR);
        slackAttachment.setFields(slackFields);

        return Collections.singletonList(slackAttachment);
    }

    private List<SlackAttachment> createBugReportSlackAttachmentsWithImage(BugReport bugReport, String writer) {
        return bugReport.getBugAttachments().stream()
                .map(bugAttachment -> {
                    SlackAttachment slackAttachment = new SlackAttachment();

                    List<SlackField> slackFields = createBugReportSlackFields(bugReport, writer);

                    slackAttachment.setFallback(FALLBACK);
                    slackAttachment.setColor(COLOR);
                    slackAttachment.setFields(slackFields);
                    slackAttachment.setImageUrl(s3Properties.getUrl() + bugAttachment.getAttachmentUrl());

                    return slackAttachment;
                })
                .toList();
    }

    private List<SlackField> createBugReportSlackFields(BugReport bugReport, String writer) {
        return List.of(
                createSlackField(BUG_REPORT_TITLE, bugReport.getTitle()),
                createSlackField(CONTENT, bugReport.getContent()),
                createSlackField(DEVELOPMENT_AREA, bugReport.getDevelopmentArea().toString()),
                createSlackField(WRITER, writer)
        );
    }

    private SlackAttachment createExceptionSlackAttachment(HttpServletRequest request, Exception exception) {
        SlackAttachment slackAttachment = new SlackAttachment();

        List<SlackField> slackFields = List.of(
                createSlackField(URL, request.getRequestURL().toString()),
                createSlackField(METHOD, request.getMethod()),
                createSlackField(CURRENT_TIME, new Date().toString()),
                createSlackField(IP, request.getRemoteAddr()),
                createSlackField(USER_AGENT, request.getHeader(USER_AGENT.substring(8)))
        );

        slackAttachment.setFallback(FALLBACK);
        slackAttachment.setColor(COLOR);
        slackAttachment.setTitle(EXCEPTION_TITLE);
        slackAttachment.setTitleLink(request.getContextPath());
        slackAttachment.setText(stackTraceToString(exception));
        slackAttachment.setFields(slackFields);
        return slackAttachment;
    }

    private SlackField createSlackField(String title, String value) {
        return new SlackField()
                .setTitle(title)
                .setValue(value);
    }

    private void sendSlackMessage(String text, List<SlackAttachment> slackAttachments) {
        SlackMessage slackMessage = new SlackMessage();
        slackMessage.setText(text);
        slackMessage.setAttachments(slackAttachments);

        try {
            slackApi.call(slackMessage);
        } catch (SlackException e) {
            e.printStackTrace();
        }
    }

    private String stackTraceToString(Exception exception) {
        StringWriter stringWriter = new StringWriter();
        PrintWriter printWriter = new PrintWriter(stringWriter);
        exception.printStackTrace(printWriter);

        return stringWriter.toString();
    }
}
