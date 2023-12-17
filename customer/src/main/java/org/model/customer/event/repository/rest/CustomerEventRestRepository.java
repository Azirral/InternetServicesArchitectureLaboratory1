package org.model.customer.event.repository.rest;

import org.model.customer.event.repository.api.CustomerEventRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.web.client.RestTemplate;


import java.util.UUID;

@Repository
public class CustomerEventRestRepository implements CustomerEventRepository {
    private final RestTemplate restTemplate;

    @Autowired
    public CustomerEventRestRepository(RestTemplate template) {this.restTemplate = template;}

    @Override
    public void delete(UUID customerId) {restTemplate.delete("/api/customers/{id}", customerId);}
}
