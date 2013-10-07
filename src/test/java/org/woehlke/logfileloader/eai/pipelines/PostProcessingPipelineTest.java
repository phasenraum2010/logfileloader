package org.woehlke.logfileloader.eai.pipelines;

import junit.framework.Assert;
import org.junit.Before;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.woehlke.logfileloader.core.entities.LogfileLine;
import org.woehlke.logfileloader.eai.events.ProcessOneLogfileLineEvent;

/**
 * Created with IntelliJ IDEA.
 * User: tw
 * Date: 03.09.13
 * Time: 11:39
 * To change this template use File | Settings | File Templates.
 */
public class PostProcessingPipelineTest {

    private final static Logger LOGGER = LoggerFactory.getLogger(PostProcessingPipelineTest.class);

    private String lines[] = new String[3];
    private ProcessOneLogfileLineEvent events[] = new ProcessOneLogfileLineEvent[3];
    private PostProcessingPipeline postProcessingPipeline = new PostProcessingPipeline();


    @Before
    public void setup(){
        lines[0] = "174.37.213.34 - - [01/Sep/2013:02:22:11 +0200] \"GET /blog HTTP/1.1\" 404 619 www.woehlke.org \"-\" \"Mozilla/5.0 (X11; U; Linux x86_64; en-US; rv:1.9.0.19; aggregator:Spinn3r (Spinn3r 3.1); http://spinn3r.com/robot) Gecko/2010040121 Firefox/3.0.19\" \"-\"";
        lines[1] = "66.249.73.90 - - [01/Sep/2013:02:08:44 +0200] \"GET /p/diffusion-limited-aggregation/cobertura/org.woehlke.simulation.diffusion.limited.aggregation.view.AppMainFrame.html HTTP/1.1\" 200 15243 www.thomas-woehlke.de \"-\" \"Mozilla/5.0 (compatible; Googlebot/2.1; +http://www.google.com/bot.html)\" \"-\" ";
        lines[2] = "144.76.42.76 - - [29/Aug/2013:01:52:21 +0200] \"GET /p/simulated-evolution/ HTTP/1.1\" 200 6799 www.thomas-woehlke.de \"-\" \"Mozilla/5.0 (Macintosh; Intel Mac OS X 10_8_2) AppleWebKit/537.17 (KHTML, like Gecko) Chrome/24.0.1312.52 Safari/537.17\" \"-\"";
        lines[2] = "144.76.42.76 - - [05/Oct/2013:23:47:17 +0200] \"GET /p/simulated-evolution/ HTTP/1.1\" 200 6799 www.thomas-woehlke.de \"-\" \"Mozilla/5.0 (Macintosh; Intel Mac OS X 10_8_2) AppleWebKit/537.17 (KHTML, like Gecko) Chrome/24.0.1312.52 Safari/537.17\" \"-\"";
        for(int i=0;i<3;i++){
            LogfileLine ll = new LogfileLine();
            ll.setLine(lines[i]);
            events[i] = new ProcessOneLogfileLineEvent();
            events[i].setLine(ll);
        }
    }

    @Test
    public void getTimeStampTest(){
        for(int i=0;i<3;i++){
            ProcessOneLogfileLineEvent result = postProcessingPipeline.getTimeStamp(events[i]);
            LOGGER.info(result.toString());
            Assert.assertNotNull(result.getDatetime());
        }
    }

    @Test
    public void getRequestStringTest(){
        for(int i=0;i<3;i++){
            ProcessOneLogfileLineEvent result = postProcessingPipeline.getRequestString(events[i]);
            LOGGER.info(result.toString());
            Assert.assertNotNull(result.getRequestLine());
        }
    }

    @Test
    public void getHttpCodeTest(){
        for(int i=0;i<3;i++){
            ProcessOneLogfileLineEvent result = postProcessingPipeline.getHttpCode(events[i]);
            LOGGER.info(result.toString());
            Assert.assertNotNull(result.getHttpCode());
        }
    }

    @Test
    public void getBrowserTest(){
        for(int i=0;i<3;i++){
            ProcessOneLogfileLineEvent result = postProcessingPipeline.getBrowser(events[i]);
            LOGGER.info(result.toString());
            Assert.assertNotNull(result.getBrowser());
        }
    }
}
