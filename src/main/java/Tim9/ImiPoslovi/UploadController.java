package Tim9.ImiPoslovi;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;


@Controller
public class UploadController {

    private static String folderPath=System.getProperty("user.dir")+"/files";

    @PostMapping("/upload")
    public String FileUpload(@RequestParam("file") MultipartFile file){

        try {
            byte[] bytes =file.getBytes();
            Path path = Paths.get(folderPath,file.getOriginalFilename());
            Files.write(path,bytes);
            System.out.println("Uspelo");
            return "";
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println("Nije Uspelo");
        return "";

    }
}
