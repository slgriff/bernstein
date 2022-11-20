package bernstein.api.service.impl;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.client.RestClientTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.client.MockRestServiceServer;

@RestClientTest(BernsteinServiceImpl.class)
@RunWith(SpringRunner.class)
public class BernsteinServiceImplTest {

    @Autowired
    BernsteinServiceImpl bernsteinService;

    @Autowired
    MockRestServiceServer mockServer;

    @Test
    public void shouldWork() {

    }
}
