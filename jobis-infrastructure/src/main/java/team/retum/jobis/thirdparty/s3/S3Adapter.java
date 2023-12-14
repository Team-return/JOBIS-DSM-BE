package team.retum.jobis.thirdparty.s3;

import com.amazonaws.HttpMethod;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.Headers;
import com.amazonaws.services.s3.internal.Mimetypes;
import com.amazonaws.services.s3.model.CannedAccessControlList;
import com.amazonaws.services.s3.model.GeneratePresignedUrlRequest;
import com.amazonaws.services.s3.model.ObjectMetadata;
import com.amazonaws.services.s3.model.PutObjectRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;
import team.retum.jobis.domain.file.exception.FileUploadFailedException;
import team.retum.jobis.domain.file.exception.InvalidExtensionException;
import team.retum.jobis.domain.file.model.FileType;
import team.retum.jobis.domain.file.spi.FilePort;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.net.URLDecoder;
import java.nio.charset.StandardCharsets;
import java.util.Date;
import java.util.UUID;

import static team.retum.jobis.domain.file.model.FileType.EXTENSION_FILE;
import static team.retum.jobis.domain.file.model.FileType.LOGO_IMAGE;

@Component
@RequiredArgsConstructor
public class S3Adapter implements FilePort {

    private final AmazonS3 amazonS3;
    private final S3Properties s3Properties;

    @Override
    @Async("asyncTaskExecutor")
    public void uploadFile(File file, String fileName) {
        try {
            InputStream inputStream = new FileInputStream(file);
            ObjectMetadata objectMetadata = new ObjectMetadata();
            objectMetadata.setContentType(Mimetypes.getInstance().getMimetype(fileName));
            objectMetadata.setContentLength(file.length());

            amazonS3.putObject(
                    new PutObjectRequest(
                            s3Properties.getBucket(),
                            fileName,
                            inputStream,
                            objectMetadata
                    ).withCannedAcl(CannedAccessControlList.PublicRead)
            );
        } catch (Exception e) {
            e.printStackTrace();
            throw FileUploadFailedException.EXCEPTION;
        }
    }

    @Override
    public String getPreSignedUrl(String fullFileName) {
        return URLDecoder.decode(
                amazonS3.generatePresignedUrl(getPreSignedUrlRequest(fullFileName)).toString(), StandardCharsets.UTF_8
        );
    }

    @Override
    public void validateExtension(String fileName, FileType fileType) {
        String extension = fileName.substring(fileName.lastIndexOf("."));

        boolean isValid = switch (fileType) {
            case LOGO_IMAGE -> LOGO_IMAGE.validExtensions.contains(extension);
            case EXTENSION_FILE -> EXTENSION_FILE.validExtensions.contains(extension);
        };

        if (!isValid) {
            throw InvalidExtensionException.EXCEPTION;
        }
    }

    @Override
    public String getFullFileName(FileType fileType, String fileName) {
        return fileType.name() + "/" + UUID.randomUUID() + "-" + fileName;
    }

    private GeneratePresignedUrlRequest getPreSignedUrlRequest(String filename) {
        GeneratePresignedUrlRequest request =
                new GeneratePresignedUrlRequest(s3Properties.getBucket(), filename)
                        .withMethod(HttpMethod.PUT)
                        .withExpiration(getPreSignedUrlExpiration());
        request.addRequestParameter(
                Headers.S3_CANNED_ACL,
                CannedAccessControlList.PublicRead.toString()
        );

        return request;
    }

    private Date getPreSignedUrlExpiration() {
        Date expiration = new Date();
        expiration.setTime(expiration.getTime() + 1000 * 60 * 2);
        return expiration;
    }
}
