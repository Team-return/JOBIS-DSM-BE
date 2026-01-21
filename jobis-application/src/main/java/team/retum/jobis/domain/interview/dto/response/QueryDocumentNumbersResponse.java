package team.retum.jobis.domain.interview.dto.response;

import lombok.Getter;
import team.retum.jobis.domain.interview.model.DocumentNumber;

import java.util.List;

@Getter
public class QueryDocumentNumbersResponse {

    private final List<DocumentNumberResponse> documentNumbers;

    public QueryDocumentNumbersResponse(List<DocumentNumber> documentNumbers) {
        this.documentNumbers = documentNumbers.stream()
            .map(DocumentNumberResponse::from)
            .toList();
    }

    @Getter
    public static class DocumentNumberResponse {
        private final Long id;
        private final String documentNumber;

        private DocumentNumberResponse(Long id, String documentNumber) {
            this.id = id;
            this.documentNumber = documentNumber;
        }

        public static DocumentNumberResponse from(DocumentNumber documentNumber) {
            return new DocumentNumberResponse(
                documentNumber.getId(),
                documentNumber.getDocumentNumber()
            );
        }
    }
}
