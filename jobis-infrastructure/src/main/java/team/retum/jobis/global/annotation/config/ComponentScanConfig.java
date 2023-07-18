package team.retum.jobis.global.annotation.config;

import com.example.jobisapplication.common.annotation.ReadOnlyUseCase;
import com.example.jobisapplication.common.annotation.UseCase;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.ComponentScan.Filter;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.FilterType;

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
