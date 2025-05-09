package com.sprint.mission.discodeit.exception;

import java.time.Instant;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

public class DiscodeitException extends RuntimeException {
    private final Instant timestamp;
    private final ErrorCode errorCode;
    private final Map<String, Object> details;

    public DiscodeitException(ErrorCode errorCode) {
        super(errorCode.getMessage());
        this.timestamp = Instant.now();
        this.errorCode = errorCode;
        this.details = Collections.emptyMap();
    }

    public DiscodeitException(ErrorCode errorCode, Throwable cause) {
        super(errorCode.getMessage(), cause);
        this.timestamp = Instant.now();
        this.errorCode = errorCode;
        this.details = Collections.emptyMap();
    }

    public DiscodeitException(ErrorCode errorCode, Map<String, Object> details) {
        super(errorCode.getMessage());
        this.timestamp = Instant.now();
        this.errorCode = errorCode;
        this.details = new HashMap<>(details);
    }

    public DiscodeitException(ErrorCode errorCode, Throwable cause, Map<String, Object> details) {
        super(errorCode.getMessage(), cause);
        this.timestamp = Instant.now();
        this.errorCode = errorCode;
        this.details = new HashMap<>(details);
    }

}
