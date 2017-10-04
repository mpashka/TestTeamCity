package my;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.net.URL;
import java.net.URLClassLoader;
import java.util.Enumeration;

/**
 * Created by pmoukhataev on 20.09.17.
 */
public class ClasspathTest {

    private static final Logger log = LoggerFactory.getLogger(ClasspathTest.class);

    @Test
    public void showClasspath() throws IOException, InterruptedException {
        debugClassloder(getClass().getClassLoader());
        debugClassloder(ClassLoader.getSystemClassLoader());
//        log.info("This classloader: ");


        log.info("---");
        Enumeration<URL> resources = getClass().getClassLoader().getResources(".");
        while (resources.hasMoreElements()) {
            URL url = resources.nextElement();
            log.info("    {}", url);
        }

        Thread.sleep(10000000000L);
    }

    private void debugClassloder(ClassLoader classLoader) {
        if (classLoader == null) return;
        debugClassloder(classLoader.getParent());
        boolean isUrlClassLoader = classLoader instanceof URLClassLoader;
        log.info("Class loader {}, url:{}", classLoader, isUrlClassLoader);
        if (isUrlClassLoader) {
            URLClassLoader urlClassLoader = (URLClassLoader) classLoader;
            for (URL url : urlClassLoader.getURLs()) {
                log.info("    URL: {}", url);
            }
        }
    }
}
