package br.com.sobreiraromulo.carrentalmanagement.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ProblemDetail;

public class UserNotFound extends CartRentalManagementException {

    private String detail;

    public UserNotFound(String detail) {
        this.detail = detail;
    }

    @Override
    public ProblemDetail toProblemDetail() {
        var pb = ProblemDetail.forStatus(HttpStatus.NOT_FOUND);

        pb.setTitle("User not found");
        pb.setDetail(detail);

        return pb;
    }
}