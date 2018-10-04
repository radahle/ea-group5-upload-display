 package com.mycompany.app;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.*;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;


import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

@Controller
public class UploadController {

    private DetermineExtention extention = new DetermineExtention();

    byte[] bytes;

    @GetMapping("/")
    public String index() {
        return "upload";
    }

    @PostMapping("/upload") // //new annotation since 4.3
    public String singleFileUpload(@RequestParam("file") MultipartFile file,
                                   RedirectAttributes redirectAttributes) {
        if (file.isEmpty()) {
            redirectAttributes.addFlashAttribute("message", "Please select a file to upload");
            return "redirect:uploadStatus";
        }

        try {
            bytes = file.getBytes();
            fname = file.getOriginalFilename();

            redirectAttributes.addFlashAttribute("message", extention.displayData(file.getOriginalFilename(), bytes));

        } catch (IOException e) {
            e.printStackTrace();
        }

        return "redirect:/uploadStatus";
    }

    @GetMapping("/uploadStatus")
    public String uploadStatus() {
        return "uploadStatus";
    }

    @RequestMapping(value = "/data", method = RequestMethod.GET)
    public ResponseEntity<byte[]> getImageAsResponseEntity() {
        HttpHeaders headers = new HttpHeaders();

        headers.setCacheControl(CacheControl.noCache().getHeaderValue());
     
        ResponseEntity<byte[]> responseEntity = new ResponseEntity<>(bytes, headers, HttpStatus.OK);
        return responseEntity;
    }
}