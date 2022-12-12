package controller;

import io.turntabl.mds.model.ProductData;
import io.turntabl.mds.dao.ProductDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/product")
public class ProductController {
    @Autowired
    private ProductDAO dao;

    @PostMapping

    public ProductData save(@RequestBody ProductData productData) {
        System.out.println(productData);
        return dao.save("TEST_EXCHANGE_HASH", productData);


    }

    @GetMapping
    public List<ProductData> getAllProducts() {
        return dao.findAll();
    }

    @GetMapping("/{id}")
    @Cacheable(key = "#id",value = "ProductData")
    public ProductData findProduct(@PathVariable int id) {
        return dao.findProductById(id);
    }

    @DeleteMapping("/{id}")
    public String remove(@PathVariable int id) {
        return dao.deleteProduct(id);
    }
}
