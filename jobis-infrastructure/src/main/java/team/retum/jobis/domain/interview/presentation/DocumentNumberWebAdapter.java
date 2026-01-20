package team.retum.jobis.domain.interview.presentation;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import team.retum.jobis.domain.interview.dto.response.QueryDocumentNumberDetailResponse;
import team.retum.jobis.domain.interview.dto.response.QueryDocumentNumbersResponse;
import team.retum.jobis.domain.interview.presentation.dto.DocumentNumberWebRequest;
import team.retum.jobis.domain.interview.usecase.CreateDocumentNumberUseCase;
import team.retum.jobis.domain.interview.usecase.DeleteDocumentNumberUseCase;
import team.retum.jobis.domain.interview.usecase.QueryDocumentNumberDetailUseCase;
import team.retum.jobis.domain.interview.usecase.QueryDocumentNumbersUseCase;
import team.retum.jobis.domain.interview.usecase.UpdateDocumentNumberUseCase;

@RequiredArgsConstructor
@RequestMapping("/document-numbers")
@RestController
public class DocumentNumberWebAdapter {

    private final CreateDocumentNumberUseCase createDocumentNumberUseCase;
    private final QueryDocumentNumbersUseCase queryDocumentNumbersUseCase;
    private final QueryDocumentNumberDetailUseCase queryDocumentNumberDetailUseCase;
    private final UpdateDocumentNumberUseCase updateDocumentNumberUseCase;
    private final DeleteDocumentNumberUseCase deleteDocumentNumberUseCase;

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping
    public void create(@RequestBody @Valid DocumentNumberWebRequest request) {
        createDocumentNumberUseCase.execute(request.documentNumber(), request.interviewIds());
    }

    @GetMapping
    public QueryDocumentNumbersResponse getAll() {
        return queryDocumentNumbersUseCase.execute();
    }

    @GetMapping("/{document-number-id}")
    public QueryDocumentNumberDetailResponse getDetail(
        @PathVariable("document-number-id") Long documentNumberId
    ) {
        return queryDocumentNumberDetailUseCase.execute(documentNumberId);
    }

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @PatchMapping("/{document-number-id}")
    public void update(
        @PathVariable("document-number-id") Long documentNumberId,
        @RequestBody @Valid DocumentNumberWebRequest request
    ) {
        updateDocumentNumberUseCase.execute(documentNumberId, request.documentNumber(), request.interviewIds());
    }

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping("/{document-number-id}")
    public void delete(@PathVariable("document-number-id") Long documentNumberId) {
        deleteDocumentNumberUseCase.execute(documentNumberId);
    }
}
