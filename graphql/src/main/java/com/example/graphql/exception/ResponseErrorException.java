package com.example.graphql.exception;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;
@Slf4j
public class ResponseErrorException {
    public void ResponseError(HttpServletResponse response,
                              String headerName, HttpStatus status, Exception exception) throws IOException {
        log.error("Error logging in: {}", exception.getMessage());
        response.setHeader(headerName, exception.getMessage());
        response.setStatus(status.value());
        Map<String, String> error = new HashMap<>();
        error.put(headerName + "_message", exception.getMessage());
        response.setContentType(APPLICATION_JSON_VALUE);
        new ObjectMapper().writeValue(response.getOutputStream(), error);
    }
}
