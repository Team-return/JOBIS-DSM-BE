package team.returm.jobis.domain.file.presentation;

import team.returm.jobis.domain.file.presentation.dto.request.DeleteFileRequest;
import team.returm.jobis.domain.file.presentation.dto.response.FileUploadResponse;
import team.returm.jobis.domain.file.service.DeleteFileService;
import team.returm.jobis.domain.file.service.FileUploadService;
import team.returm.jobis.domain.file.presentation.type.FileType;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.Valid;

@RestController
@RequestMapping("/files")
@RequiredArgsConstructor
public class FileController {
    private final FileUploadService fileUploadService;
    private final DeleteFileService deleteFileService;

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping
    public FileUploadResponse uploadFile(@RequestParam("file") MultipartFile multipartFile,
                                         @RequestParam("type")FileType fileType) {
        return new FileUploadResponse(fileUploadService.execute(multipartFile, fileType));
    }

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping
    public void deleteFile(@RequestBody @Valid DeleteFileRequest request) {
        deleteFileService.execute(request.getPath());
    }
}
