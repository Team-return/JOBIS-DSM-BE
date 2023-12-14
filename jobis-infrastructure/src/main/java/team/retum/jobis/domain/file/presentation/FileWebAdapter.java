package team.retum.jobis.domain.file.presentation;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import team.retum.jobis.domain.file.dto.response.CreatePreSignedUrlResponse;
import team.retum.jobis.domain.file.dto.response.FileUploadResponse;
import team.retum.jobis.domain.file.exception.FileNotFoundException;
import team.retum.jobis.domain.file.exception.FileUploadFailedException;
import team.retum.jobis.domain.file.model.FileType;
import team.retum.jobis.domain.file.presentation.dto.CreatePreSignedUrlWebRequest;
import team.retum.jobis.domain.file.usecase.CreatePreSignedUrlUseCase;
import team.retum.jobis.domain.file.usecase.FileUploadUseCase;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.List;

@RestController
@RequestMapping("/files")
@RequiredArgsConstructor
public class FileWebAdapter {

    private final FileUploadUseCase fileUploadUseCase;
    private final CreatePreSignedUrlUseCase createPreSignedUrlUseCase;

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping
    public FileUploadResponse uploadFile(
            @RequestParam("file") List<MultipartFile> multipartFiles,
            @RequestParam("type") FileType fileType
    ) {
        return fileUploadUseCase.execute(
                multipartFiles.stream()
                        .map(multipartFile -> {
                            if (multipartFile == null || multipartFile.getOriginalFilename() == null) {
                                throw FileNotFoundException.EXCEPTION;
                            }
                            File file = new File(multipartFile.getOriginalFilename());

                            try (OutputStream outputStream = new FileOutputStream(file)) {
                                outputStream.write(multipartFile.getBytes());
                            } catch (IOException e) {
                                throw FileUploadFailedException.EXCEPTION;
                            }

                            return file;
                        }).toList(),
                fileType
        );
    }

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping("/pre_signed")
    public CreatePreSignedUrlResponse createPreSignedUrl(
            @RequestBody @Valid CreatePreSignedUrlWebRequest request
    ) {
        return createPreSignedUrlUseCase.execute(request.toDomainRequest());
    }
}
