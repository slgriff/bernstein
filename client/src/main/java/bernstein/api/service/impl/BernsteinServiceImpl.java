package bernstein.api.service.impl;

import bernstein.api.service.BernsteinService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
@Slf4j
public class BernsteinServiceImpl implements BernsteinService {

    private final RestTemplate restTemplate;

    public BernsteinServiceImpl(RestTemplateBuilder restTemplateBuilder) {
        restTemplate = restTemplateBuilder.build();
    }
}
