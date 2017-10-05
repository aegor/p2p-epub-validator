package p2p.epubvalidator.services;

import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

public interface EpubService {

    String validate(MultipartFile file) throws IOException;

}
