package team.retum.jobis.thirdparty.python;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import team.retum.jobis.common.spi.PythonPort;

import java.io.IOException;
import java.net.URI;
import java.net.URLEncoder;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.nio.charset.StandardCharsets;
import java.util.List;

@RequiredArgsConstructor
@Component
public class PythonService implements PythonPort {

    private final PythonProperty pythonProperty;

    @Override
    public String getInterestByPython(List<String> major, List<String> tech) {

        String interestsParam = String.join(",", major);
        String techParam = String.join(",", tech);

        String baseUrl = pythonProperty.getBase_url();
        String url = String.format("%s?major=%s&tech=%s",
            baseUrl,
            URLEncoder.encode(interestsParam, StandardCharsets.UTF_8),
            URLEncoder.encode(techParam, StandardCharsets.UTF_8));

        HttpClient client = HttpClient.newHttpClient();

        HttpRequest request = HttpRequest.newBuilder()
            .uri(URI.create(url))
            .GET()
            .build();

        HttpResponse<String> response;
        try {
            response = client.send(request, HttpResponse.BodyHandlers.ofString());
        } catch (IOException | InterruptedException e) {
            throw new RuntimeException(e);
        }

        // 응답 반환
        return response.body();
    }
}
