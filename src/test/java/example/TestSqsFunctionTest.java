package example;

import io.micronaut.test.annotation.MicronautTest;
import org.junit.jupiter.api.Test;
import javax.inject.Inject;

import static org.junit.jupiter.api.Assertions.assertEquals;

@MicronautTest
public class TestSqsFunctionTest {

    @Inject
    TestSqsClient client;

    @Test
    public void testFunction() throws Exception {
    	TestSqs body = new TestSqs();
    	body.setName("test-sqs");
        assertEquals("test-sqs", client.apply(body).blockingGet().getName());
    }
}
