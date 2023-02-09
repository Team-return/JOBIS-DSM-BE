package com.example.jobis.global.entity;

import com.fasterxml.uuid.Generators;
import lombok.Getter;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import java.util.UUID;

@Getter
@MappedSuperclass
public abstract class BaseUUIDEntity {
    @Id
    @Column(columnDefinition = "BINARY(16)", nullable = false)
    private UUID id = Generators.timeBasedGenerator().generate();
}
