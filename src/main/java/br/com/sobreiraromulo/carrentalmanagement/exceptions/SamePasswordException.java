package br.com.sobreiraromulo.carrentalmanagement.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ProblemDetail;

public class SamePasswordException extends CartRentalManagementException {
    private final String detail;

    public SamePasswordException(String detail) {
        this.detail = detail;
    }

    @Override
    public ProblemDetail toProblemDetail() {
        ProblemDetail pb = ProblemDetail.forStatus(HttpStatus.CONFLICT);
        pb.setTitle("Cannot use the same password as last one.");
        pb.setDetail(detail);
        return pb;
    }
}
