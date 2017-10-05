package p2p.epubvalidator.services.util;

import com.adobe.epubcheck.api.Report;
import com.adobe.epubcheck.messages.Message;
import com.adobe.epubcheck.messages.MessageDictionary;
import com.adobe.epubcheck.messages.MessageId;

import java.io.File;

public class P2PMessageDictionary extends MessageDictionary{

    public P2PMessageDictionary(File overrideFile, Report report)
    {
        super(overrideFile,report);
    }

    private class Messages {
        String id;
        String severity;
        String message;
        String suggestion;
    }

    public void jsonMessages()
    {
        String json = "[\n";

        for (MessageId id : MessageId.values())
        {
            json += "{\"id:\" \"" + id.toString() + "\",";
            Message message = this.getMessage(id);
            if (message != null)
            {
                json += "{\"severity:\" \"" + message.getSeverity() + "\",";
                json += "{\"message:\" \"" + message.getMessage() + "\",";
                json += "{\"suggestion:\" \"" + message.getSuggestion() + "\",";
            }
            else
            {
                json += "{\"severity:\" \"" + "" + "\",";
                json += "{\"message:\" \"" + "" + "\",";
                json += "{\"suggestion:\" \"" + "" + "\",";
            }
            json += "},\n";
        }
        json += "]\n";
    }
}
