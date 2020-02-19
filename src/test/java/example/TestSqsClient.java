package example;

import io.micronaut.function.client.FunctionClient;
import io.micronaut.http.annotation.Body;
import io.reactivex.Single;
import javax.inject.Named;

@FunctionClient
public interface TestSqsClient {

    @Named("test-sqs")
    Single<TestSqs> apply(@Body TestSqs body);

}
