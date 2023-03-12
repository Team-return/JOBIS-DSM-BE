package com.example.jobis.global.annotation.config;

import com.example.jobis.global.annotation.ReadOnlyService;
import com.example.jobis.global.annotation.Service;
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
