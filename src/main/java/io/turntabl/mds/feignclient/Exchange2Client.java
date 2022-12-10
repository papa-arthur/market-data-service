package io.turntabl.mds.feignclient;


import io.turntabl.mds.model.ProductData;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@FeignClient(
        name = "productData2",
        url = "https://exchange2.matraining.com"
)
public interface Exchange2Client {
    @GetMapping("/pd")
    public List<ProductData> getProductData();
}
