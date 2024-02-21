package team.retum.jobis.domain.banner.exception;

import team.retum.jobis.common.error.JobisException;
import team.retum.jobis.domain.banner.exception.error.BannerErrorCode;

public class BannerNotFoundException extends JobisException {

    public static final JobisException EXCEPTION = new BannerNotFoundException();

    private BannerNotFoundException() {
        super(BannerErrorCode.BANNER_NOT_FOUND);
    }
}
