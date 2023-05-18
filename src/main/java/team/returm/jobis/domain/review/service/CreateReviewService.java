package team.returm.jobis.domain.review.service;

import lombok.RequiredArgsConstructor;
import team.returm.jobis.domain.application.domain.enums.ApplicationStatus;
import team.returm.jobis.domain.application.domain.repository.ApplicationRepository;
import team.returm.jobis.domain.application.exception.ApplicationNotFoundException;
import team.returm.jobis.domain.code.domain.Code;
import team.returm.jobis.domain.code.domain.repository.CodeRepository;
import team.returm.jobis.domain.code.exception.CodeNotFoundException;
import team.returm.jobis.domain.code.facade.CodeFacade;
import team.returm.jobis.domain.company.domain.repository.CompanyRepository;
import team.returm.jobis.domain.company.exception.CompanyNotFoundException;
import team.returm.jobis.domain.review.domain.QnAElement;
import team.returm.jobis.domain.review.domain.Review;
import team.returm.jobis.domain.review.domain.repository.ReviewRepository;
import team.returm.jobis.domain.review.exception.ReviewAlreadyExistsException;
import team.returm.jobis.domain.review.presentation.dto.CreateReviewRequest;
import team.returm.jobis.domain.student.domain.Student;
import team.returm.jobis.domain.student.domain.repository.StudentRepository;
import team.returm.jobis.domain.student.exception.StudentNotFoundException;
import team.returm.jobis.domain.user.facade.UserFacade;
import team.returm.jobis.global.annotation.Service;

import java.time.Year;
import java.util.List;

@RequiredArgsConstructor
@Service
public class CreateReviewService {

    private final UserFacade userFacade;
    private final StudentRepository studentRepository;
    private final ApplicationRepository applicationRepository;
    private final ReviewRepository reviewRepository;
    private final CompanyRepository companyRepository;
    private final CodeRepository codeRepository;
    private final CodeFacade codeFacade;

    public void execute(CreateReviewRequest request) {

        if (!companyRepository.existsCompanyById(request.getCompanyId())) {
            throw CompanyNotFoundException.EXCEPTION;
        }

        if (applicationRepository.existsApplicationByApplicationIdAndApplicationStatus(
                request.getApplicationId(), ApplicationStatus.REQUESTED)) {
            throw ApplicationNotFoundException.EXCEPTION;
        }

        List<Long> codeIds = request.getQnaElements().stream()
                .map(QnAElement::getCodeId)
                .toList();

        codeFacade.queryCodesByIdIn(codeIds);

        Student student =  studentRepository.queryStudentById(userFacade.getCurrentUserId())
                .orElseThrow(() -> StudentNotFoundException.EXCEPTION);

        if (reviewRepository.existsByCompanyIdAndStudentName(request.getCompanyId(), student.getName())) {
            throw ReviewAlreadyExistsException.EXCEPTION;
        }

        reviewRepository.save(
                Review.builder()
                        .companyId(request.getCompanyId())
                        .qnAElements(request.getQnaElements())
                        .studentName(student.getName())
                        .year(Year.now().getValue())
                        .build()
        );
    }
}
