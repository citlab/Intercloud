package de.tu_berlin.cit.intercloud.webapp;

import de.tu_berlin.cit.intercloud.webapp.pages.BrowserPage;
import de.tu_berlin.cit.intercloud.webapp.pages.DiscoverItemsPage;
import de.tu_berlin.cit.intercloud.xmpp.rest.XmppURI;
import org.apache.wicket.model.Model;
import org.apache.wicket.util.tester.WicketTester;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.net.URISyntaxException;
import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.assertNotNull;

@RunWith(Parameterized.class)
public class BrowserPageTest {
    private static final Logger logger = LoggerFactory.getLogger(BrowserPageTest.class);

    @Parameterized.Parameters
    public static List<Object[]> data() {
        return Arrays.asList(new Object[10][0]);
    }

    private WicketTester tester;
    private long time;

    @Before
    public void setUp() {
        tester = new WicketTester(new IntercloudWebApplication());
        MockHelper.initialize();
        MockHelper.login();
    }

    @After
    public void tearDown() {
        MockHelper.logout();
        time = 0;
    }

    @Test
    public void getWebSession() throws URISyntaxException {
        IntercloudWebSession session = IntercloudWebSession.get();
        assertNotNull(session);
        tester.startPage(DiscoverItemsPage.class);
        tester.assertRenderedPage(DiscoverItemsPage.class);

        System.out.println();
        time = System.currentTimeMillis();
        tester.startPage(new BrowserPage(Model.of(new XmppURI("foo", MockHelper.XWADL_ROOT + "/compute.xml"))));
        logger.info("BrowserPage receiving xwadl: {} ms", System.currentTimeMillis() - time);
        tester.assertRenderedPage(BrowserPage.class);

        // test post method
        time = System.currentTimeMillis();
        tester.clickLink("methodTable:methodList:0:methodLink");
        logger.info("BrowserPage render occi representation: {} ms", System.currentTimeMillis() - time);
        tester.assertRenderedPage(BrowserPage.class);
    }
}