package com.example.springactuator;

import org.springframework.boot.actuate.endpoint.web.annotation.RestControllerEndpoint;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.*;

@Component
@RestControllerEndpoint(id="myEndpoint")
public class CustomEndpointController {

    @GetMapping("/")
    public @ResponseBody ResponseEntity<String> customGetEndpoint(){
        return new ResponseEntity<String>("REST end point _ GET", HttpStatus.OK);
    }

    @GetMapping("/{selector}")
    public @ResponseBody ResponseEntity<String> customGetEndpoint(@PathVariable String selector){
        return new ResponseEntity<String>("REST end point _ GET" + selector, HttpStatus.OK);
    }

    @PostMapping("/{selector}")
    public @ResponseBody ResponseEntity<String> customPostEndpoint(@PathVariable String selector){
        return new ResponseEntity<String>("REST end point _ POST" + selector, HttpStatus.OK);
    }

    @DeleteMapping("/{selector}")
    public @ResponseBody ResponseEntity<String> customDeleteEndpoint(@PathVariable String selector){
        return new ResponseEntity<String>("REST end point _ DELETE" + selector, HttpStatus.OK);
    }

    @PutMapping("/{selector}")
    @ResponseBody
    public ResponseEntity<String> customPutEndpoint(@PathVariable String selector){
        return new ResponseEntity<String>("REST end point _ PUT" + selector, HttpStatus.OK);
    }


}
