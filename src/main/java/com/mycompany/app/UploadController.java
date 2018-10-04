 package com.mycompany.app;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

@Controller
public class UploadController {

    private DetermineExtention extention = new DetermineExtention();

    //String filename = "";

    //Save the uploaded file to this folder
    private static String UPLOADED_FOLDER = "src/main/resources/temp/";
    private static String fileName = "";

    @GetMapping("/")
    public String index() {
        return "upload";
    }

    @PostMapping("/upload") // //new annotation since 4.3
    public String singleFileUpload(@RequestParam("file") MultipartFile file,
                                   RedirectAttributes redirectAttributes) {


       // filename = file.toString();

        if (file.isEmpty()) {
            redirectAttributes.addFlashAttribute("message", "Please select a file to upload");
            return "redirect:uploadStatus";
        }

        try {

            //Get the file and save it somewhere
            byte[] bytes = file.getBytes();
            Path path = Paths.get(UPLOADED_FOLDER + file.getOriginalFilename());
            Files.write(path, bytes);
            fileName = file.getOriginalFilename();

            redirectAttributes.addFlashAttribute("message",
                    extention.displayData(file.getOriginalFilename()));
            //redirectAttributes.addFlashAttribute("Type", extention.getExtentionByString(file.getOriginalFilename()));

        } catch (IOException e) {
            e.printStackTrace();
        }

        return "redirect:/uploadStatus";
    }

    @GetMapping("/uploadStatus")
    public String uploadStatus() {
        return "uploadStatus";
    }

    @GetMapping("/data")
    public String returnData(){
        return fileName;
    }

    /*public String getFileName(){
        return filename;
    }*/

}