package team.retum.jobis.domain.file.presentation;

import com.example.jobisapplication.domain.file.model.FileType;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import com.example.jobisapplication.domain.file.dto.response.FileUploadResponse;
import team.retum.jobis.domain.file.presentation.type.FileType;
import team.retum.jobis.domain.file.service.DeleteFileService;
import com.example.jobisapplication.domain.file.usecase.FileUploadUseCase;

import java.util.List;

@RestController
@RequestMapping("/files")
@RequiredArgsConstructor
public class FileWebAdapter {

    private final FileUploadUseCase fileUploadUseCase;
    private final DeleteFileService deleteFileService;

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping
    public FileUploadResponse uploadFile(
            @RequestParam("file") List<MultipartFile> multipartFiles,
            @RequestParam("type") FileType fileType
    ) {
        return fileUploadUseCase.execute(
                multipartFiles.stream()
                        .map(file -> {

                        }),
                fileType
        )
    }

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping
    public void deleteFile(
            @RequestParam("path") String path
    ) {
        deleteFileService.execute(path);
    }
}
