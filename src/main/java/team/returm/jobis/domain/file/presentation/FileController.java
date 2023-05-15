package team.returm.jobis.domain.file.presentation;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import team.returm.jobis.domain.file.presentation.dto.response.FileUploadResponse;
import team.returm.jobis.domain.file.presentation.type.FileType;
import team.returm.jobis.domain.file.service.DeleteFileService;
import team.returm.jobis.domain.file.service.FileUploadService;

@RestController
@RequestMapping("/files")
@RequiredArgsConstructor
public class FileController {
    private final FileUploadService fileUploadService;
    private final DeleteFileService deleteFileService;

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping
    public FileUploadResponse uploadFile(
            @RequestParam("file") MultipartFile multipartFile,
            @RequestParam("type") FileType fileType
    ) {
        return new FileUploadResponse(fileUploadService.execute(multipartFile, fileType));
    }

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping
    public void deleteFile(
            @RequestParam("path") String path
    ) {
        deleteFileService.execute(path);
    }
}
