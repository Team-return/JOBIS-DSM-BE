package team.retum.jobis.domain.interview.spi;

import team.retum.jobis.domain.interview.model.DocumentNumber;

public interface CommandDocumentNumberPort {

    DocumentNumber save(DocumentNumber documentNumber);

    void delete(DocumentNumber documentNumber);
}
