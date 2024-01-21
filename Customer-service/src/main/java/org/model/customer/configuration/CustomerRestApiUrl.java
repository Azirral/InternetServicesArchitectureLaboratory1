package org.model.customer.configuration;

import lombok.Getter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
@Getter
public class CustomerRestApiUrl {
    @Value("${model.customer.url.delete}")
    private String deleteUrl;

    @Value("${model.customer.url.put}")
    private String putUrl;

    @Value("${model.customer.url.post}")
    private String postUrl;

}
