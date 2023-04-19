package team.returm.jobis.domain.acceptance.service;

import lombok.RequiredArgsConstructor;
import team.returm.jobis.domain.acceptance.domain.Acceptance;
import team.returm.jobis.domain.acceptance.domain.repository.AcceptanceRepository;
import team.returm.jobis.domain.acceptance.presentation.dto.request.ChangeContractDateRequest;
import team.returm.jobis.global.annotation.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class ChangeContractDateService {

    private final AcceptanceRepository acceptanceRepository;

    public void execute(ChangeContractDateRequest request) {

        List<Acceptance> acceptances = acceptanceRepository.queryByIdIn(request.getAcceptanceIds());

        acceptanceRepository.updateContractDate(
                request.getContractDate(),
                acceptances
        );
    }
}
