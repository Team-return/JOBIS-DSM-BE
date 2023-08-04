package team.retum.jobis.global.annotation.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.ComponentScan.Filter;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.FilterType;
import team.retum.jobis.common.annotation.ReadOnlyUseCase;
import team.retum.jobis.common.annotation.UseCase;

@Configuration
@ComponentScan(
        basePackages = {"team.retum.jobis"},
        includeFilters = {
                @Filter(
                        type = FilterType.ANNOTATION,
                        classes = {
                                UseCase.class,
                                ReadOnlyUseCase.class
                        }
                )
        }
)
public class ComponentScanConfig {
}
