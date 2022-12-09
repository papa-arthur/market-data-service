package io.turntabl.mds.controller;

import io.turntabl.mds.config.RedisConfig;
import io.turntabl.mds.model.ProductDTO;
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

    public ProductDTO save(@RequestBody ProductDTO productDTO) {
        System.out.println(productDTO);
        return dao.save("TEST_EXCHANGE_HASH",productDTO);


    }

    @GetMapping
    public List<ProductDTO> getAllProducts() {
        return dao.findAll();
    }

    @GetMapping("/{id}")
    @Cacheable(key = "#id",value = "ProductDTO")
    public ProductDTO findProduct(@PathVariable int id) {
        return dao.findProductById(id);
    }

    @DeleteMapping("/{id}")
    public String remove(@PathVariable int id) {
        return dao.deleteProduct(id);
    }
}
