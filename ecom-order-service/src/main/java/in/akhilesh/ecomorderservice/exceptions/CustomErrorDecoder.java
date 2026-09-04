package in.akhilesh.ecomorderservice.exceptions;


import feign.Response;
import feign.codec.ErrorDecoder;

public class CustomErrorDecoder implements ErrorDecoder {
    @Override
    public Exception decode(String s, Response response) {
        if(response.status() == 400) {
            return new RuntimeException("Product Not Found");
        }

        return new Exception("Generic Error: " + response.status());
    }
}
