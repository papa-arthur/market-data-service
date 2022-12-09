package io.turntabl.mds.feignclient;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@FeignClient(
        name = "productData",
        url = "https://exchange.matraining.com"
)
public interface Exchange1Client {

    @GetMapping("/pd")
    public List<Object> getProductData();
}
