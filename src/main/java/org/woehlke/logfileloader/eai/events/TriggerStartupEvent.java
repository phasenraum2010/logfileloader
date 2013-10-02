package org.woehlke.logfileloader.eai.events;

import java.io.Serializable;
import java.util.List;

public class TriggerStartupEvent implements Serializable {

    private String directoryContentHtml;

    private List<String> filenames;

    public String getDirectoryContentHtml() {
        return directoryContentHtml;
    }

    public void setDirectoryContentHtml(String directoryContentHtml) {
        this.directoryContentHtml = directoryContentHtml;
    }

    public List<String> getFilenames() {
        return filenames;
    }

    public void setFilenames(List<String> filenames) {
        this.filenames = filenames;
    }

    public boolean isSatisfiedBy(List<String> listOfFilenames) {
        if (filenames.size() != listOfFilenames.size()) {
            return false;
        }
        for (String filename : filenames) {
            if (!listOfFilenames.contains(filename)) {
                return false;
            }
        }
        return true;
    }

    @Override
    public String toString() {
        return "TriggerStartupEvent{" +
                "directoryContentHtml='" + directoryContentHtml + '\'' +
                ", filenames=" + filenames +
                '}';
    }
}
