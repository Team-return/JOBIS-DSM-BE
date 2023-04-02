package team.returm.jobis.infrastructure.s3;

import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.model.CannedAccessControlList;
import com.amazonaws.services.s3.model.DeleteObjectRequest;
import com.amazonaws.services.s3.model.ObjectMetadata;
import com.amazonaws.services.s3.model.PutObjectRequest;
import java.io.IOException;
import java.io.InputStream;
import java.util.UUID;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;
import team.returm.jobis.domain.file.exception.FileNotFoundException;
import team.returm.jobis.domain.file.exception.InvalidExtensionException;
import team.returm.jobis.domain.file.presentation.type.FileType;

@Component
@RequiredArgsConstructor
public class S3Util {
    private final AmazonS3 amazonS3;
    private final S3Properties s3Properties;

    public String uploadImg(MultipartFile multipartFile, FileType fileType) {
        if (multipartFile == null || multipartFile.getOriginalFilename() == null) {
            throw FileNotFoundException.EXCEPTION;
        }
        String fileExtension = getExtensionWithValidation(multipartFile.getOriginalFilename(), fileType);
        String fileName = fileType + "/" + UUID.randomUUID() + fileExtension;

        ObjectMetadata objectMetadata = new ObjectMetadata();
        objectMetadata.setContentType(multipartFile.getContentType());
        objectMetadata.setContentLength(multipartFile.getSize());


        try (InputStream inputStream = multipartFile.getInputStream()) {
            amazonS3.putObject(
                    new PutObjectRequest(
                            s3Properties.getBucket(),
                            fileName,
                            inputStream,
                            objectMetadata
                    ).withCannedAcl(CannedAccessControlList.PublicRead)
            );
        } catch (IOException e) {
            throw FileNotFoundException.EXCEPTION;
        }

        return s3Properties.getUrl() + fileName;
    }

    public void deleteFile(String path) {
        if (!amazonS3.doesObjectExist(s3Properties.getBucket(), path)) {
            throw FileNotFoundException.EXCEPTION;
        }
        amazonS3.deleteObject(new DeleteObjectRequest(s3Properties.getBucket(), path));
    }

    private String getExtensionWithValidation(String fileName, FileType fileType) {
        if (fileName == null) {
            throw FileNotFoundException.EXCEPTION;
        }

        String extension = fileName.substring(fileName.lastIndexOf("."));

        if (!(extension.equals(".jpg") || extension.equals(".png") || extension.equals(".svg") || extension.equals(".jpeg"))) {
            if (fileType.equals(FileType.LOGO_IMAGE)) {
                throw InvalidExtensionException.EXCEPTION;
            }
            if (!(extension.equals(".pdf") || extension.equals(".ppt") || extension.equals("pptx")
                    || extension.equals("hwp") || extension.equals(".zip"))) {
                throw InvalidExtensionException.EXCEPTION;
            }
        }

        return extension;
    }
}
