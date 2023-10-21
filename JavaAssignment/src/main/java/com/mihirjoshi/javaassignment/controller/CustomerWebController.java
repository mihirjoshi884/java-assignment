package com.mihirjoshi.javaassignment.controller;


import com.mihirjoshi.javaassignment.DTO.Customer;
import com.mihirjoshi.javaassignment.serivces.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.ui.Model;
import java.util.List;

@Controller
@RequestMapping("/Customer-web")
public class CustomerWebController {

    @Autowired
    private CustomerService customerService;
    @GetMapping("/login")
    public String showLoginForm() {
//        return "/WEB-INF/views/login.jsp"; // Maps to login.jsp
        return "login";
    }

    @GetMapping("/add-customer")
    public String showAddCustomerForm() {
//        return "/WEB-INF/views/add-customer.jsp"; // Maps to add-customer.jsp
        return "add-customer";
    }

    @GetMapping("/customer-list")
    public String listCustomers(Model model) {
        // Retrieve the list of customers from your service
        List<Customer> customers = customerService.getAllCustomers();

        // Add the list of customers to the model
        model.addAttribute("customers", customers);

        // Return the name of the JSP view that will display the list of customers
//        return "/WEB-INF/views/customer-list.jsp";
        return "customer-list";
    }
}
