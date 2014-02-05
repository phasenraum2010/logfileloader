package org.woehlke.logfileloader.eai.events;

import org.springframework.integration.Message;

import java.io.Serializable;
import java.util.List;

public class ImportOneLogfileEvent implements Serializable {

    private String filename;
    private List<String> lines;

    public String getFilename() {
        return filename;
    }

    public void setFilename(String filename) {
        this.filename = filename;
    }

    public List<String> getLines() {
        return lines;
    }

    public void setLines(List<String> lines) {
        this.lines = lines;
    }
}
