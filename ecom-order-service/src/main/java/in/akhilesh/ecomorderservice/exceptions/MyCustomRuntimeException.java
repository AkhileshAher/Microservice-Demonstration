package in.akhilesh.ecomorderservice.exceptions;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;

public class MyCustomRuntimeException extends RuntimeException {

    private HttpStatus status;
    private HttpHeaders headers;

    public MyCustomRuntimeException(HttpStatusCode statusCode, HttpHeaders headers) {
    }
}
