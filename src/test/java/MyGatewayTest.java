import org.junit.Test;
import static org.junit.Assert.*;

public class MyGatewayTest {

    public int MAX_RETRIES = 3;

    @Test
    public void getThing_shouldReturnThing_whenSuccessful() {
        MyGateway gateway = new MyGateway(MAX_RETRIES);

        String result = gateway.getThing("Thing");

        assertEquals("Thing", result);
    }
}
