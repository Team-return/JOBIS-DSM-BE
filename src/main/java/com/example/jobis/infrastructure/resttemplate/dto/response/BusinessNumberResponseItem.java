package com.example.jobis.infrastructure.resttemplate.dto.response;

import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@Getter
@NoArgsConstructor
@XmlRootElement(name = "item")
@XmlAccessorType(XmlAccessType.NONE)
public class BusinessNumberResponseItem {

    @XmlElement
    private String bno;

    @XmlElement
    private String cno;

    @XmlElement
    private String company;

    @XmlElement
    private String bstt;

    @XmlElement
    private String taxtype;
}
