package example;

import com.amazonaws.services.lambda.runtime.events.SQSEvent;
import io.micronaut.core.annotation.TypeHint;
import io.micronaut.function.executor.FunctionInitializer;
import io.micronaut.function.FunctionBean;
import javax.inject.*;
import java.io.IOException;
import java.util.function.Function;

@FunctionBean("test-sqs")
@TypeHint(
        value = {SQSEvent.class, SQSEvent.SQSMessage.class, SQSEvent.MessageAttribute.class},
        accessType = TypeHint.AccessType.ALL_PUBLIC)
public class TestSqsFunction extends FunctionInitializer implements Function<SQSEvent, TestSqs> {

    @Override
    public TestSqs apply(final SQSEvent msg) {
         TestSqs testSqs=new TestSqs();
         Integer queueCount = msg.getRecords().size();
         testSqs.setName(queueCount.toString());
        return testSqs;
    }

    /**
     * This main method allows running the function as a CLI application using: echo '{}' | java -jar function.jar 
     * where the argument to echo is the JSON to be parsed.
     */
    public static void main(String...args) throws IOException {
        TestSqsFunction function = new TestSqsFunction();
        function.run(args, (context)-> function.apply(context.get(SQSEvent.class)));
    }    
}

