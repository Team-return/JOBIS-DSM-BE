package team.retum.jobis.thirdparty.s3;

import com.amazonaws.HttpMethod;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.Headers;
import com.amazonaws.services.s3.model.CannedAccessControlList;
import com.amazonaws.services.s3.model.GeneratePresignedUrlRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import team.retum.jobis.domain.file.spi.FilePort;

import java.net.URLDecoder;
import java.nio.charset.StandardCharsets;
import java.util.Date;

@Component
@RequiredArgsConstructor
public class S3Adapter implements FilePort {

    private final AmazonS3 amazonS3;
    private final S3Properties s3Properties;

    @Override
    public String generateFileUploadUrl(String fullFileName) {
        return URLDecoder.decode(
            amazonS3.generatePresignedUrl(getPreSignedUrlRequest(fullFileName)).toString(), StandardCharsets.UTF_8
        );
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
