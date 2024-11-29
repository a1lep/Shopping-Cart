package com.example.shoppingcart.controller;

import com.example.shoppingcart.model.Product;
import com.example.shoppingcart.service.CartService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/cart")
public class CartController {

    private final CartService cartService;
    public CartController(CartService cartService) {
        this.cartService = cartService;
    }

    @PostMapping("/add-product")
    public Product addProduct(@RequestBody Product product) {
        return cartService.addProduct(product);
    }
    @GetMapping("/getCartItems")
    public List<Product> getCartItems() {
        return cartService.getCartItems();
    }
    @DeleteMapping("/remove-product/{name}")
    public String removeProduct(@PathVariable String name) {
        return cartService.removeProductByName(name);
    }
    @GetMapping("/totalPrice")
    public double totalPrice() {
        return cartService.calculateCartTotal();
    }
    @GetMapping("/tax")
    public double tax() {
        return cartService.tax();
    }
    @GetMapping("/calculate-tax-total")
    public double calculateTaxTotal() {
        return cartService.calculateTaxTotal();
    }
    @GetMapping("/apply-discount")
    public double applyDiscount() {
        return cartService.applyDiscount();
    }

}
