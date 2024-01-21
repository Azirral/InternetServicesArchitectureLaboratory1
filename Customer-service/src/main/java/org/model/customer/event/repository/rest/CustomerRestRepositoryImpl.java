package org.model.customer.event.repository.rest;

import org.model.customer.configuration.CustomerRestApiUrl;
import org.model.customer.dto.GetCustomerResponse;
import org.model.customer.dto.PutCustomerRequest;
import org.model.customer.event.repository.api.CustomerRestRepository;
import org.springframework.stereotype.Repository;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;


import java.util.UUID;

@Repository
public class CustomerRestRepositoryImpl implements CustomerRestRepository {
    private final RestTemplate restTemplate;
    private final CustomerRestApiUrl restApiUrl;

    public CustomerRestRepositoryImpl(RestTemplate restTemplate, CustomerRestApiUrl restApiUrl) {
        this.restTemplate = restTemplate;
        this.restApiUrl = restApiUrl;
    }

    @Override
    public void delete(UUID id) {restTemplate.delete(restApiUrl.getDeleteUrl(), id);}

    @Override
    public void updateName(UUID id, PutCustomerRequest putCustomerRequest) {
        String url = UriComponentsBuilder.fromUriString(restApiUrl.getPutUrl())
                .pathSegment("{id}")
                .buildAndExpand(id)
                .toUriString();

        restTemplate.put(url, putCustomerRequest);
    }

    @Override
    public void addCustomer(GetCustomerResponse getCustomerResponse) {
        restTemplate.postForLocation(restApiUrl.getPostUrl(), getCustomerResponse);

    }
}
