package p2p.epubvalidator.services;

import com.adobe.epubcheck.api.EpubCheck;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import p2p.epubvalidator.services.util.CheckingReport;

import java.io.IOException;
import java.io.InputStream;

@Service
@Scope(value = "request", proxyMode= ScopedProxyMode.TARGET_CLASS)
public class EpubServiceImpl implements EpubService{

    public String validate(MultipartFile file) throws IOException {
        CheckingReport report = new CheckingReport("webstream");
        EpubCheck ec = new EpubCheck(file.getInputStream(), report, "webstream");
        boolean f = ec.validate();
        try {
            return report.getJsonReport();
        } catch (IOException e) {
            e.printStackTrace();
            return e.getMessage();
        }
        finally {
            ec = null;
            report = null;
        }
    }
}
