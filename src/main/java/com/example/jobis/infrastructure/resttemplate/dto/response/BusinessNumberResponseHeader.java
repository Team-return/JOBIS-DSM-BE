package com.example.jobis.infrastructure.resttemplate.dto.response;

import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@Getter
@NoArgsConstructor
@XmlRootElement(name = "header")
@XmlAccessorType(XmlAccessType.NONE)

public class BusinessNumberResponseHeader {

    @XmlElement
    private Long resultCode;

    @XmlElement
    private String resultMsg;
}
