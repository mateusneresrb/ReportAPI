package dev.mateusneres.report.exceptions;

import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;

@AllArgsConstructor
public class ApiKeyUnauthorizedException extends CustomException {

    private final int errorCode;
    private final String errorMessage;

    @Override
    public int getStatusCode() {
        return HttpStatus.UNAUTHORIZED.value();
    }

    @Override
    public int getErrorCode() {
        return this.errorCode;
    }

    @Override
    public String getErrorMessage() {
        return this.errorMessage;
    }

}

