package org.woehlke.logfileloader.eai.pipelines;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.*;

/**
 * Created with IntelliJ IDEA.
 * User: tw
 * Date: 22.09.13
 * Time: 15:35
 * To change this template use File | Settings | File Templates.
 */
public class ImportOneLogfilePipelineTest {

    private final static Logger LOGGER = LoggerFactory.getLogger(ImportOneLogfilePipelineTest.class);

    @Test
    public void getTempDirectoryTest() throws IOException {
        ImportOneLogfilePipeline p = new ImportOneLogfilePipeline();
        String tempDirName = p.getTempDirectory();
        LOGGER.info("tempDirName: "+tempDirName);
        File testFile = new File(tempDirName+"test.txt");
        BufferedWriter out = new BufferedWriter(new FileWriter(testFile));
        out.write("Test-Text Line 1");
        out.newLine();
        out.write("Test-Text Line 2");
        out.newLine();
        out.close();
        testFile.delete();
    }
}
