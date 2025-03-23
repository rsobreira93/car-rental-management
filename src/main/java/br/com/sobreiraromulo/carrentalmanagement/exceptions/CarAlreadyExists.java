package br.com.sobreiraromulo.carrentalmanagement.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ProblemDetail;

public class CarAlreadyExists extends CartRentalManagementException {

    private String detail;

    public CarAlreadyExists(String detail) {
        this.detail = detail;
    }

    @Override
    public ProblemDetail toProblemDetail() {
        var pb = ProblemDetail.forStatus(HttpStatus.CONFLICT);

        pb.setTitle("car already exists");
        pb.setDetail(detail);

        return pb;
    }
}