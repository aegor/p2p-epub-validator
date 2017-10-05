package p2p.epubvalidator.services;

import com.adobe.epubcheck.api.EpubCheck;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import p2p.epubvalidator.services.util.CheckingReport;

import java.io.IOException;
import java.io.InputStream;

@Service
public class EpubServiceImpl implements EpubService{

    CheckingReport report;

    @Autowired
    public EpubServiceImpl(){
        this.report = new CheckingReport("webstream");

    }

    public String validate(MultipartFile file) throws IOException {
        EpubCheck ec = new EpubCheck(file.getInputStream(), report, "webstream");
        boolean f = ec.validate();
        try {
            return report.getJsonReport();
        } catch (IOException e) {
            e.printStackTrace();
            return e.getMessage();
        }
    }
}
