package br.com.sobreiraromulo.carrentalmanagement.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ProblemDetail;

public class UserAlreadyExists extends CartRentalManagementException {

    private String detail;

    public UserAlreadyExists(String detail) {
        this.detail = detail;
    }

    @Override
    public ProblemDetail toProblemDetail() {
        var pb = ProblemDetail.forStatus(HttpStatus.CONFLICT);

        pb.setTitle("User already exists");
        pb.setDetail(detail);

        return pb;
    }
}