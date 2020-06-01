import org.junit.Test;
import static org.junit.Assert.*;

public class RetryCommandTest {

    public String SUCCESS = "success";
    public int MAX_RETRIES = 3;

    @Test
    public void run_shouldNotRetryCommand_whenSuccessful() {
        RetryCommand<String> retryCommand = new RetryCommand<>(MAX_RETRIES);

        String result = retryCommand.run(() -> SUCCESS);

        assertEquals(SUCCESS, result);
        assertEquals(0, retryCommand.getRetryCounter());
    }

    @Test
    public void run_shouldRetryOnceThenSucceed_whenFailsOnFirstCallButSucceedsOnFirstRetry() {
        RetryCommand<String> retryCommand = new RetryCommand<>(MAX_RETRIES);

        String result = retryCommand.run(() -> {
            if (retryCommand.getRetryCounter() == 0) throw new RuntimeException("Command Failed");
            else return SUCCESS;
        });

        assertEquals(SUCCESS, result);
        assertEquals(1, retryCommand.getRetryCounter());
    }

    @Test
    public void run_shouldThrowException_whenMaxRetriesIsReached() {
        RetryCommand<String> retryCommand = new RetryCommand<>(MAX_RETRIES);

        try {
            retryCommand.run(() -> {throw new RuntimeException("Failed");});
            fail("Should throw exception when max retries is reached");
        } catch (Exception ignored) { }
    }
}