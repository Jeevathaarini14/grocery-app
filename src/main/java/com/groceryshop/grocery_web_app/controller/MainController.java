package com.groceryshop.grocery_web_app.controller;

import com.groceryshop.grocery_web_app.model.Cart;
import com.groceryshop.grocery_web_app.model.CheckOut;
import com.groceryshop.grocery_web_app.model.Product;
import com.groceryshop.grocery_web_app.model.User;
import com.groceryshop.grocery_web_app.repository.CartRepo;
import com.groceryshop.grocery_web_app.repository.CheckOutRepo;
import com.groceryshop.grocery_web_app.repository.ProductRepo;
import com.groceryshop.grocery_web_app.repository.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.List;

@Controller
public class MainController {

    @GetMapping("/")
    public String index_page() {
        return "index";
    }

    @GetMapping("/signup")
    public String signup_page() {
        return "sign_up";
    }

    @GetMapping("/login")
    public String login_page() {
        return "login";
    }

    @Autowired
    private UserRepo userRepo;

    @PostMapping("/sign-up")
    public String signup(@RequestParam("email") String email,
                         @RequestParam("password") String password, Model m) {
        User auth = userRepo.findByEmail(email);
        if (auth == null) {
            User u = new User(email, password);
            userRepo.save(u);
            return "login";

        } else {
            String errorMsg = "User already exists";
            m.addAttribute("error", errorMsg);
            return "sign_up";
        }
    }

    @PostMapping("/log-in")
    public String login(@RequestParam("email") String email,
                        @RequestParam("password") String password, Model m) {
        User auth = userRepo.findByEmail(email);
        if (auth != null && auth.getPassword().equals(password)) {
            return "redirect:/main";
        } else {
            String errorMsg = "Email & Password are incorrect";
            m.addAttribute("error", errorMsg);
            return "login";

        }
    }

    @GetMapping("/admin")
    public String admin_panel(Model prods) {
        List<Product> products = prodRepo.findAll();
        prods.addAttribute("prods", products);
        return "admin";
    }

    @GetMapping("/add")
    public String add_product() {
        return "add_product";
    }

    @Autowired
    private ProductRepo prodRepo;
    String path = "C:\\Users\\DELL\\Downloads\\grocery_web_app\\grocery_web_app\\src\\main\\resources\\static";

    @PostMapping("/add_product")
    public String add_product(@RequestParam("productName") String name,
                              @RequestParam("product_price") Integer price,
                              @RequestParam("product_description") String description,
                              @RequestParam("product_image") MultipartFile image) throws IOException {
        String image_name = image.getOriginalFilename();
        Product p = new Product(name, price, description, image_name);
        prodRepo.save(p);
        assert image_name != null;
        File f = new File(path, image_name);
        image.transferTo(f);
        return "redirect:/admin";
    }

    @GetMapping("/delete_product/{id}")
    public String delete_product(@PathVariable Long id) {
        Product product = prodRepo.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Invalid Id"));
        Cart c = cartRepo.findByProduct(product);
        if (c != null) {
            cartRepo.delete(c);
        }
        prodRepo.delete(product);
        return "redirect:/admin";


    }

    @GetMapping("/edit_product/{id}")
    public String editProduct(@PathVariable Long id, Model m) {
        Product p = prodRepo.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Invalid Id"));
        m.addAttribute("product", p);
        return "edit_product";

    }

    @PostMapping("/update_product/{id}")
    public String updateProduct(@PathVariable Long id,
                                @RequestParam("productName") String name,
                                @RequestParam("product_price") Integer price,
                                @RequestParam("product_description") String description,
                                @RequestParam("product_image") MultipartFile image) throws IOException {
        Product p = prodRepo.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Invalid id"));
        String image_name = image.getOriginalFilename();
        if (!image.isEmpty()) {
            p.setProduct_image(image_name);
            assert image_name != null;
            File f = new File(path, image_name);
            image.transferTo(f);

        }
        p.setProductName(name);
        p.setProduct_price(price);
        p.setProduct_description(description);
        prodRepo.save(p);
        return "redirect:/admin";

    }

    @GetMapping("/main")
    public String main_page(Model prods) {
        List<Product> products = prodRepo.findAll();
        prods.addAttribute("prods", products);
        return "main";
    }

    @Autowired
    private CartRepo cartRepo;

    @GetMapping("/addtocart/{id}")
    public String add_To_Cart(@PathVariable Long id) {
        Product p = prodRepo.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("invalid Id"));
        Cart cart_item = cartRepo.findByProduct(p);
        if (cart_item != null) {
            cart_item.setQuantity(cart_item.getQuantity() + 1);
        } else {
            cart_item = new Cart(1, p);

        }
        cartRepo.save(cart_item);
        return "redirect:/viewCart";

    }

    @GetMapping("/viewCart")
    public String view_cart(Model m) {
        List<Cart> cartItems = cartRepo.findAll();
        double total_price = 0;
        for (Cart i : cartItems) {
            total_price += i.getProduct().getProduct_price() * i.getQuantity();

        }
        m.addAttribute("total_price", total_price);
        m.addAttribute("cartitems", cartItems);
        return "cart";
    }

    @GetMapping("/InQty/{id}")
    public String inOty(@PathVariable Long id){
        Cart c= cartRepo.findById(id).orElseThrow(()->new IllegalArgumentException("Invalid id"));
        c.setQuantity(c.getQuantity()+1);
        cartRepo.save(c);
        return "redirect:/viewCart";
    }

    @GetMapping("/DeQty/{id}")
    public String deOty(@PathVariable Long id){
        Cart c= cartRepo.findById(id).orElseThrow(()->new IllegalArgumentException("Invalid id"));
        if (c.getQuantity()>1){
            c.setQuantity(c.getQuantity()-1);
            cartRepo.save(c);
        }

        return "redirect:/viewCart";
    }
    @GetMapping("/delete-cart/{id}")
    public String deleteCart(@PathVariable Long id) {
        Cart c = cartRepo.findById(id).orElseThrow(() -> new IllegalArgumentException("Invalid id"));
        cartRepo.delete(c);
        return "redirect:/viewCart";
    }
    @PostMapping("/check")
    public String check(@RequestParam("total") String total, Model m) {
        m.addAttribute("total", total);
        return "checkout";
    }

    @Autowired
    private CheckOutRepo checkOutRepo;

    @PostMapping("/checkout")
    public String CheckOut(@RequestParam("username") String name,
                           @RequestParam("email") String email,
                           @RequestParam("address") String address,
                           @RequestParam("country") String country,
                           @RequestParam("state") String state,
                           @RequestParam("pincode") String pincode,
                           @RequestParam("payment") String payment,
                           @RequestParam("totalamount") String totalamount) {
        CheckOut c = new CheckOut(name, email, address, country, state, pincode, payment, totalamount);
        checkOutRepo.save(c);
        return "redirect:/main";
    }
    @GetMapping("/search")
    public String searchResults(@RequestParam("query") String query, Model model) {
        List<Product> q = prodRepo.findByProductNameContainingIgnoreCase(query);
        model.addAttribute("prods", q);
        return "search_results";
    }



}