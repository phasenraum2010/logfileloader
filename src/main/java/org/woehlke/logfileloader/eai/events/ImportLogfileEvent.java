package org.woehlke.logfileloader.eai.events;

import java.io.Serializable;
import java.util.List;

public class ImportLogfileEvent implements Serializable {

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

    public boolean isSatisfiedBy(List<String> listOfLines) {
        if ( lines.size()!=listOfLines.size() ){
            return false;
        }
        for(String line:lines){
            if(!listOfLines.contains(line)){
                return false;
            }
        }
        return true;
    }
}
