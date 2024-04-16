package team.retum.jobis.domain.code.spi;

import team.retum.jobis.domain.code.model.Code;

public interface CommandCodePort {

    Code save(Code code);
}
