package team.retum.jobis.domain;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HealthCheckWebAdapter {

  @GetMapping
  public String healthCheck() {
    return "OK";
  }
}
