package team.retum.jobis.thirdparty.webhook.slack;

import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import net.gpedro.integrations.slack.SlackApi;
import net.gpedro.integrations.slack.SlackAttachment;
import net.gpedro.integrations.slack.SlackException;
import net.gpedro.integrations.slack.SlackField;
import net.gpedro.integrations.slack.SlackMessage;
import org.springframework.stereotype.Component;
import team.retum.jobis.domain.bug.model.BugAttachment;
import team.retum.jobis.domain.bug.model.BugReport;
import team.retum.jobis.global.util.LogUtil;
import team.retum.jobis.thirdparty.s3.S3Properties;
import team.retum.jobis.thirdparty.webhook.WebhookUtil;

import java.util.Collections;
import java.util.Date;
import java.util.List;

@RequiredArgsConstructor
@Slf4j
@Component
public class SlackAdapter implements WebhookUtil {

    private static final String FALLBACK = "Ok";
    private static final String COLOR = "danger";
    private static final String BUG_REPORT_TITLE = "버그 제목";
    private static final String CONTENT = "버그 내용";
    private static final String DEVELOPMENT_AREA = "버그가 발생한 분야";
    private static final String WRITER = "버그를 제보한 사용자";
    private static final String EXCEPTION_TITLE = "예외 발생";
    private static final String REQUEST_URI = "Request URI";
    private static final String METHOD = "Request Method";
    private static final String CURRENT_TIME = "Request Time";
    private static final String BUG_TEXT = "버그 제보가 도착했습니다.";
    private static final String EXCEPTION_TEXT = "서버 에러 발생 😱😱😱";

    private final S3Properties s3Properties;

    private final SlackApi slackApi;

    @Override
    public void sendBugReport(BugReport bugReport, String writer) {
        List<SlackAttachment> slackAttachments = createBugReportSlackAttachments(bugReport, writer);

        sendSlackMessage(BUG_TEXT, slackAttachments);
    }

    @Override
    public void sendExceptionInfo(HttpServletRequest request, Exception exception) {
        SlackAttachment slackAttachment = createExceptionSlackAttachment(request, exception);

        sendSlackMessage(EXCEPTION_TEXT, Collections.singletonList(slackAttachment));
    }

    private List<SlackAttachment> createBugReportSlackAttachments(BugReport bugReport, String writer) {
        BugAttachment bugAttachment = bugReport.getAttachment();
        if (bugAttachment.attachmentUrls().isEmpty()) {
            return Collections.singletonList(
                    createBugReportSlackAttachment(bugReport, writer, null, true)
            );
        } else {
            return bugAttachment.attachmentUrls().stream()
                    .map(url -> {
                        boolean isFirst = bugAttachment.attachmentUrls().get(0).equals(url);
                        return createBugReportSlackAttachment(
                                bugReport, writer, url, isFirst
                        );
                    })
                    .toList();
        }
    }

    private SlackAttachment createBugReportSlackAttachment(
            BugReport bugReport,
            String writer,
            String attachmentUrl,
            boolean isFirst
    ) {
        SlackAttachment slackAttachment = new SlackAttachment();
        slackAttachment.setFallback(FALLBACK);
        slackAttachment.setColor(COLOR);

        if (isFirst) {
            List<SlackField> slackFields = createBugReportSlackFields(bugReport, writer);
            slackAttachment.setFields(slackFields);
        }

        if (attachmentUrl != null) {
            slackAttachment.setImageUrl(s3Properties.getUrl() + attachmentUrl);
        }

        return slackAttachment;
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
                createSlackField(REQUEST_URI, request.getRequestURI()),
                createSlackField(METHOD, request.getMethod()),
                createSlackField(CURRENT_TIME, new Date().toString())
        );

        slackAttachment.setFallback(FALLBACK);
        slackAttachment.setColor(COLOR);
        slackAttachment.setTitle(EXCEPTION_TITLE);
        slackAttachment.setTitleLink(request.getContextPath());
        slackAttachment.setText(LogUtil.stackTraceToString(exception));
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
            log.error(LogUtil.stackTraceToString(e));
        }
    }
}
