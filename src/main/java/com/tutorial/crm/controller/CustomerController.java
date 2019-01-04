package com.tutorial.crm.controller;

import com.tutorial.crm.dao.CustomerDAO;
import com.tutorial.crm.entity.Customer;
import com.tutorial.crm.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/customer")
public class CustomerController {

    //injecting customer service
    @Autowired
    private CustomerService customerService;

    @GetMapping("/list")
    public String listCustomers(Model model){
        List<Customer> customers = customerService.getCustomers();
        model.addAttribute("customers", customers);
        return "list-customers";
    }

    @GetMapping("/showFormForAdd")
    public String showFormForAdd(Model model){
        Customer customer = new Customer();
        model.addAttribute("customer", customer);
        return "customer-form";
    }

    @PostMapping("/saveCustomer")
    public String saveCustomer(@ModelAttribute(name = "customer") Customer customer){
        customerService.saveCustomer(customer);
        return "redirect:/customer/list";
    }

    @GetMapping("/showFormForUpdate")
    public String showFormForUpdate(@RequestParam("customerId") int customerId,
                                    Model model){
        Customer customer = customerService.getCustomer(customerId);
        model.addAttribute(customer);
        return "customer-form";
    }

    @GetMapping("/delete")
    public String deleteCustomer(@RequestParam("customerId") int id){
        customerService.deleteCustomer(id);
        return "redirect:/customer/list";
    }

    @PostMapping("/search")
    public String search(@RequestParam("searchName") String searchName,
                         Model model){
        List<Customer> customers = customerService.getCustomers(searchName);
        model.addAttribute("customers", customers);
        return "list-customers";
    }

}
