package com.example.springsecurityapplication.controllers.admin;

import com.example.springsecurityapplication.models.Image;
import com.example.springsecurityapplication.models.Order;
import com.example.springsecurityapplication.models.Person;
import com.example.springsecurityapplication.models.Product;
import com.example.springsecurityapplication.repositories.CategoryRepository;
import com.example.springsecurityapplication.repositories.OrderRepository;
import com.example.springsecurityapplication.repositories.PersonRepository;
import com.example.springsecurityapplication.services.OrderService;
import com.example.springsecurityapplication.services.PersonService;
import com.example.springsecurityapplication.services.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;


import javax.validation.Valid;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Controller
@RequestMapping(path="/admin")

//@PreAuthorize("hasRole('ROLE_ADMIN') or hasRole('ROLE_USER')")
public class AdminController {

    @Value("//home/tulen/Downloads/FinalprojectEliseeva/FinalProjectApplication/src/main/resources/static/img")
    private String uploadPath;
    private final ProductService productService;

    private final OrderService orderService;

    private final CategoryRepository categoryRepository;

    private final OrderRepository orderRepository;

    private final PersonRepository personRepository;

    private final PersonService personService;

    @Autowired
    public AdminController(ProductService productService, OrderService orderService, CategoryRepository categoryRepository, OrderRepository orderRepository, PersonRepository personRepository, PersonService personService){
        this.productService = productService;
        this.orderService = orderService;
        this.categoryRepository = categoryRepository;
        this.orderRepository = orderRepository;
        this.personRepository = personRepository;
        this.personService = personService;
    }





//    @PreAuthorize("hasRole('ROLE_ADMIN') or hasRole('ROLE_USER')")
    @GetMapping("")
    public String admin(Model model){
        model.addAttribute("products", productService.getAllProduct());
        return "admin/admin";
    }

    //http:8080/localhost/admin/product/add
    //Метод по отображению страницы для добавления товара
    @GetMapping("/product/add")
    public String addProduct(Model model){
        model.addAttribute("product", new Product());
        model.addAttribute("category", categoryRepository.findAll());
        return "product/addProduct";
    }



    // Метод по добавлению продукта в БД через сервис->репозиторий
    @PostMapping("/product/add")
    public String addProduct(@ModelAttribute("product") @Valid Product product, BindingResult bindingResult, @RequestParam("file_one") MultipartFile file_one, @RequestParam("file_two")MultipartFile file_two, @RequestParam("file_three")MultipartFile file_three, @RequestParam("file_four")MultipartFile file_four, @RequestParam("file_five") MultipartFile file_five) throws IOException {
        if(bindingResult.hasErrors())
        {
            return "product/addProduct";
        }

        if(file_one != null)
        {
            File uploadDir = new File(uploadPath);
            if(!uploadDir.exists()){
                uploadDir.mkdir();
            }
            String uuidFile = UUID.randomUUID().toString();
            String resultFileName = uuidFile + "." + file_one.getOriginalFilename();
            file_one.transferTo(new File(uploadPath + "/" + resultFileName));
            Image image = new Image();
            image.setProduct(product);
            image.setFileName(resultFileName);
            product.addImageToProduct(image);
        }

        if(file_two != null)
        {
            File uploadDir = new File(uploadPath);
            if(!uploadDir.exists()){
                uploadDir.mkdir();
            }
            String uuidFile = UUID.randomUUID().toString();
            String resultFileName = uuidFile + "." + file_two.getOriginalFilename();
            file_two.transferTo(new File(uploadPath + "/" + resultFileName));
            Image image = new Image();
            image.setProduct(product);
            image.setFileName(resultFileName);
            product.addImageToProduct(image);
        }

        if(file_three != null)
        {
            File uploadDir = new File(uploadPath);
            if(!uploadDir.exists()){
                uploadDir.mkdir();
            }
            String uuidFile = UUID.randomUUID().toString();
            String resultFileName = uuidFile + "." + file_three.getOriginalFilename();
            file_three.transferTo(new File(uploadPath + "/" + resultFileName));
            Image image = new Image();
            image.setProduct(product);
            image.setFileName(resultFileName);
            product.addImageToProduct(image);
        }

        if(file_four != null)
        {
            File uploadDir = new File(uploadPath);
            if(!uploadDir.exists()){
                uploadDir.mkdir();
            }
            String uuidFile = UUID.randomUUID().toString();
            String resultFileName = uuidFile + "." + file_four.getOriginalFilename();
            file_four.transferTo(new File(uploadPath + "/" + resultFileName));
            Image image = new Image();
            image.setProduct(product);
            image.setFileName(resultFileName);
            product.addImageToProduct(image);
        }

        if(file_five != null)
        {
            File uploadDir = new File(uploadPath);
            if(!uploadDir.exists()){
                uploadDir.mkdir();
            }
            String uuidFile = UUID.randomUUID().toString();
            String resultFileName = uuidFile + "." + file_five.getOriginalFilename();
            file_five.transferTo(new File(uploadPath + "/" + resultFileName));
            Image image = new Image();
            image.setProduct(product);
            image.setFileName(resultFileName);
            product.addImageToProduct(image);
        }

        productService.saveProduct(product);
        return "redirect:/admin";
    }


    @GetMapping("/product/delete/{id}")
    public String deleteProduct(@PathVariable("id") int id) {
        productService.deleteProduct(id);
        return "redirect:/admin";
    }

    // Метод по отображению страницы с возможностью редактирования товаров
    @GetMapping("/product/edit/{id}")
    public String editProduct(Model model, @PathVariable("id") int id){
        model.addAttribute("product", productService.getProductId(id));
        model.addAttribute("category", categoryRepository.findAll());
        return "product/editProduct";
    }

    // Метод по редактированию товара
    @PostMapping("/product/edit/{id}")
    public String editProduct(@ModelAttribute("product") @Valid Product product, BindingResult bindingResult, @PathVariable("id") int id){
        if(bindingResult.hasErrors())
        {
            return "product/editProduct";
        }
        productService.updateProduct(id, product);
        return "redirect:/admin";
    }





//    @GetMapping("/product/editstatus/{id}")
//    public String editOrder(Model model, @PathVariable("id") int id){
//        //model.addAttribute("order", orderService.getAllOrder());
////        List<Order> orderList = orderRepository.findAll();
////        model.addAttribute("order", orderList);
////
//        List<Order> orderList = orderRepository.findById(id);
//        model.addAttribute("orders", orderList);
//
//        model.addAttribute("order", orderService.getOrderId(id));
//
//        return "product/editStatus";
//    }

////     Метод по редактированию статуса
//    @PostMapping("/product/editstatus/{id}")
//    public String editStatusProduct(@ModelAttribute("order") @Valid Order order, BindingResult bindingResult, @PathVariable("id") int id){
//        if(bindingResult.hasErrors())
//        {
//            return "product/editStatus";
//        }
//        orderService.updateOrder(id, order);
//        return "redirect:/admin/orders";
//
//    }

//    @PostMapping("/admin/search")
//    public String productSearch(@RequestParam("value_search_order") String search, Model model){
//        model.addAttribute("search_order", orderRepository.findByNumberContainingIgnoreCase(search));
//        model.addAttribute("order", orderService.getAllOrder());
//        model.addAttribute("value_search_order", search);
//        return "/admin/search";
//
//    }

    // Метод по отображению страницы со всеми заказами
    @GetMapping("/admin/orders")
    public String orders() {return "admin/orders"; }

    @GetMapping("/orders")
    public String orders(Model model){
        List<Order> orderList = orderRepository.findAll();
        model.addAttribute("orders", orderList);

        return "/admin/orders";
    }

//    Поск по номеру заказа
//    @PostMapping("/admin/orders")
//    public String orders(@RequestParam("search") String search, Model model){
//            model.addAttribute("search_orders",orderService.getOrderSorted(search));
//        return "admin/orders";
//    }

    @PostMapping("/search")
    public String orderSearch(@RequestParam("search") String search, Model model) {
        if (search == null) {
            model.addAttribute("error", "необходимо ввести 4 последние символа заказа");
            return "/admin/orders";
        } else if (!(search.length() == 4)) {
            model.addAttribute("error", "необходимо ввести 4 последние символа заказа");
            return "/admin/orders";
        } else {
            List<Order> resultOrderList = new ArrayList<>();
            char[] chars = search.toCharArray();
            for (char s : chars) {
                System.out.println(s);
            }
            List<Order> orderList = orderRepository.findAll();
            for (Order order : orderList) {
                int k = 0;
                System.out.println(order.getNumber());
                char[] charsOrder = order.getNumber().toCharArray();
                for (int i = 0; i < 4; i++) {
                    System.out.println(chars[i]);
                    System.out.println(charsOrder[charsOrder.length - 4 + i]);
                    if (chars[i] == charsOrder[charsOrder.length - 4 + i]) {
                        k++;
                        System.out.println("Нашли равные символы: " + chars[i]);
                    }
                }
                if (k == 4) {
                    System.out.println("Нашли одного");
                    resultOrderList.add(order);
                }
            }

            model.addAttribute("search_orders", resultOrderList);
            model.addAttribute("orders", orderRepository.findAll());
        }
        return "/admin/orders";
    }

    //страница со всеми пользователями
    @GetMapping("/user/userList")
    public String userList(Model model){
       List<Person> userList = personRepository.findAll();
        model.addAttribute("user_list", userList);

        return "user/userList";
    }

    //метод по отображению формы дл смены роли пользовтетя
    @GetMapping("/userList/edit/{id}")
    public String editUserList(Model model, @PathVariable("id") int id){
        List<Person> userList = personRepository.findAll();
        model.addAttribute("user_list", userList);
        model.addAttribute("person", personService.getPersonId(id));
        return "user/editUserList";
    }



    // Метод по редактированию роли пользователей
    @PostMapping("/userList/edit/{id}")
    public String editUserList(@ModelAttribute("person") @Valid Person person, BindingResult bindingResult, @PathVariable("id") int id){
        if(bindingResult.hasErrors())
        {
            return "user/editUserList";
        }
        personService.updatePerson(id, person);
        return "redirect:/admin";
    }



}
