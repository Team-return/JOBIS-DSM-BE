package team.retum.jobis.domain.interview.spi;

import team.retum.jobis.domain.interview.model.DocumentNumber;
import team.retum.jobis.domain.interview.spi.vo.DocumentNumberDetailVO;

import java.util.List;
import java.util.Optional;

public interface QueryDocumentNumberPort {

    Optional<DocumentNumber> getById(Long documentNumberId);

    List<DocumentNumber> getAll();

    Optional<DocumentNumberDetailVO> getDetailById(Long documentNumberId);
}
