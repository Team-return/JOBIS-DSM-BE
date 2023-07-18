package team.retum.jobis.global.annotation.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.ComponentScan.Filter;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.FilterType;
import team.retum.jobis.global.annotation.ReadOnlyService;
import team.retum.jobis.global.annotation.Service;

@Configuration
@ComponentScan(
        basePackages = {"team.retum.jobis"},
        includeFilters = {
                @Filter(
                        type = FilterType.ANNOTATION,
                        classes = {
                                Service.class,
                                ReadOnlyService.class
                        }
                )
        }
)
public class ComponentScanConfig {
}
