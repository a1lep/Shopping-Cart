package com.example.shoppingcart.service;

import com.example.shoppingcart.model.Product;
import com.example.shoppingcart.repository.ProductRepository;
import jakarta.transaction.Transactional;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Transactional
public class CartService {

    private final ProductRepository productRepository;

    public CartService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    public Product addProduct(Product product) {
        productRepository.save(product);
        return product ;
    }

    public List<Product> getCartItems() {
        return productRepository.findAll();
    }

    public String removeProductByName(String name) {
        for (Product product : productRepository.findAll()) {
            if (product.getName().equals(name)) {
                productRepository.delete(product);
                return "Product " + name + " removed successfully";
            }
        }
        return "No product found with name " + name;
    }

    public double calculateCartTotal() {
        List<Product> products = getCartItems();
        double total = 0;
        for (Product product : products) {
            total += product.getPrice() * product.getQuantity();
        }
        return total;
    }
    public double tax () {
        double tax = 0.22;
        double total = calculateCartTotal();

        total = calculateCartTotal() * tax;
        return total;
    }

    public double calculateTaxTotal() {
        double tax = 0.22;
        double totalPrice = calculateCartTotal();
        double totalWithTax = 0;

        totalWithTax = (calculateCartTotal() * tax) + totalPrice;
        return totalWithTax;
    }
    public double applyDiscount() {
        boolean isMember = true;
        double discount = 0.1;
        double totalWithDiscount = 0;
        if (isMember) {
            totalWithDiscount = calculateTaxTotal() - (calculateTaxTotal()*discount);
            return totalWithDiscount;
        }
        else {
            return calculateTaxTotal();
        }

    }

}
