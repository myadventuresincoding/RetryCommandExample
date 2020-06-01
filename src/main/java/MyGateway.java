public class MyGateway {
    private RestClient restClient;
    private RetryCommand<String> retryCommand;

    public MyGateway(int maxRetries) {
        retryCommand = new RetryCommand<>(maxRetries);
        restClient = new RestClient();
    }

    public String getThing(final String id) {
        return retryCommand.run(() -> restClient.getThatThing(id));
    }
}