package p2p.epubvalidator.services.util;

import java.io.*;

import com.adobe.epubcheck.api.EpubCheck;
import com.adobe.epubcheck.api.Report;
import org.springframework.web.multipart.MultipartFile;

public class P2PReportTester {
    public static void main(String[] args) throws IOException {

        // Test types
        MultipartFile mpfile;

        String file="/tmp/geom.epub";
        CheckingReport report = new CheckingReport("webstream");
        File epubFile = new File(file);
        EpubCheck epubcheck = new EpubCheck(epubFile, report);
        boolean result = epubcheck.validate();
        //System.out.println(report.getErrorCount());
        //System.out.println(report.getEpubFileName());
        String a = report.getJsonReport();
        // System.out.println(a);
    }

    public static void testtypes(MultipartFile mf) {
        Report report = new CheckingReport("webstream");
        EpubCheck epubcheck = new EpubCheck((InputStream) mf, report, "file");
    }
}
