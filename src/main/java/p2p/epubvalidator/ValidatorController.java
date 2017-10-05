package p2p.epubvalidator;

import java.io.IOException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import p2p.epubvalidator.services.EpubService;

@Controller
public class ValidatorController {

    private final EpubService epubService;

    @Autowired
    public ValidatorController(EpubService epubService) {
        this.epubService = epubService;
    }

    @PostMapping("/validate")
    public @ResponseBody String handleFileUpload(@RequestParam("file") MultipartFile file) throws IOException {
        return epubService.validate(file);
    }
}
