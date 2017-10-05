package p2p.epubvalidator.services.util;

import com.adobe.epubcheck.api.EPUBLocation;
import com.adobe.epubcheck.messages.Message;
import com.adobe.epubcheck.messages.Severity;
import com.adobe.epubcheck.util.DefaultReportImpl;
import com.adobe.epubcheck.util.FeatureEnum;
import com.adobe.epubcheck.util.PathUtil;


/**
 * overrides the Adobe epubcheck "Report" class to handle all the epubcheck warnings and errors
 * instead of writing them to stderr, we are appending them to the result textarea on our GUI
 */

public class P2PReport extends DefaultReportImpl {

    private boolean DEBUG = false;
    private String currentEpubVersion;




	/* ********************************************************************************************************** */

    public P2PReport(String ePubName) {
        super(ePubName);
    }




	/* ********************************************************************************************************** */

    // duplicate method fixMessage() since original method is private
/*
    private String fixMessage(String message) {
		if (message == null)
			return "";
		return message.replaceAll("[\\s]+", " ");
	}
*/



	/* ********************************************************************************************************** */

    @Override
    public void info(String resource, FeatureEnum feature, String value) {

        switch (feature) {
            case FORMAT_VERSION:
                // System.out.println(String.format(Messages.VALIDATING_VERSION_MESSAGE, value));

                // "insert at 0" instead of "append" to catch warnings and errors from above
            /*
			String s = String.format(Messages.get("validating_version_message"), value )
			System.out.println(Severity.INFO.toString() + "EGOR 1" +
					        s
						+ "\n" + "(https://github.com/IDPF/epubcheck)"
						+ "\n\n");
                        */
                currentEpubVersion = value;
                break;
            default:
                if (DEBUG) {
                    if (resource == null) {
                        System.out.println("INFO: [" + feature + "]=" + value);
                    } else {
                        System.out.println("INFO: [" + feature + " (" +
                                resource + ")]=" + value);
                    }
                }
                break;
        }
    }

    String __(Object a) {
        return a.toString();
    }


	/* ********************************************************************************************************** */

    String formatMessage(Message message, EPUBLocation location, Object... args) {
        String fileName = (location.getPath() == null ? "" : "/" + location.getPath());
        fileName = PathUtil.removeWorkingDirectory(fileName);

        String s = String.format("%1$s (%2$s) %3$s \"%4$s%5$s\"%6$s:\n   %7$s\n\n",
                __(message.getSeverity()),
                __(message.getID()),
                message.getSeverity().toString() + "_preposition",
                __(PathUtil.removeWorkingDirectory(this.getEpubFileName())),
                __(fileName),
                __(location.getLine() > 0 ? (" (" + "line" + " " + location.getLine() + (location.getColumn() > 0 ? ", " + "col" + " " + location.getColumn() : "")) + ")" : ""),
                __(fixMessage(args != null && args.length > 0 ? message.getMessage(args) : message.getMessage())));
        return s;

/*        println "1 " + message.getSeverity().toString()
        println "2 " + message.getID().toString()
        println "3 " + message.getSeverity().toString() + "_preposition"
        println "4 " + PathUtil.removeWorkingDirectory(this.getEpubFileName())
        println "5 " + fileName
        println
        "6 " + __(location.getLine() > 0 ? (" (" + "line" + " " + location.getLine() + (location.getColumn() > 0 ? ", " + "col" + " " + location.getColumn() : "")) + ")" : "")
        println "7 " + fixMessage(args != null && args.length > 0 ? message.getMessage(args) : message.getMessage())*/
    }

    // duplicate method fixMessage() since original method is private

    private String fixMessage(String message) {
        if (message == null)
            return "";
        return message.replaceAll("[\\s]+", " ");
    }



	/* ********************************************************************************************************** */

    @Override
    public void message(Message message, EPUBLocation location, Object... args) {

        Severity severity = message.getSeverity();

        String text = formatMessage(message, location, args);
        // System.out.println("Message:" + text);

    }


	/* ********************************************************************************************************** */

    public String getCurrentEpubVersion() {
        return currentEpubVersion;
    }


}
