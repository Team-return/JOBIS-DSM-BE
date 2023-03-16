package team.returm.jobis.global.annotation.config;

import team.returm.jobis.global.annotation.ReadOnlyService;
import team.returm.jobis.global.annotation.Service;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.ComponentScan.Filter;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.FilterType;

@Configuration
@ComponentScan(
        basePackages = {"com.example.jobis"},
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
public class ComponentScanConfig {}
