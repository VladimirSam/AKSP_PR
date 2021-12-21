package ru.krjava.event_agency.controller;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import ru.krjava.event_agency.domain.OrderForm;
import ru.krjava.event_agency.domain.User;
import ru.krjava.event_agency.repo.OrderRepo;
import ru.krjava.event_agency.repo.UserRepo;

@Controller
@RequestMapping("/")
public class HomeController {


    @Autowired
    UserRepo userRepo;
    @Autowired
    OrderRepo orderRepo;
    @GetMapping
    public String index(@AuthenticationPrincipal User user, Model model){
        if (user!= null){
            model.addAttribute("user",user.getUsername());
            return "index";
        }

        model.addAttribute("user","anonymous");
        return "index";
    }

    @GetMapping("/login")
    public String login(){
        return "login";
    }
    @GetMapping("/order")
    public String Addingorder(){

        return "order";
    }


    @PostMapping("/order")
    public String processOrder(@AuthenticationPrincipal User user, OrderForm form){

        orderRepo.save(form.toOrder(user));

        return "redirect:/foruser";
    }

    @PreAuthorize(value = "hasAnyAuthority('ADMIN', 'USER')")
    @GetMapping("/foruser")
    public String forUser() {
        return "foruser";
    }

    @PreAuthorize(value = "hasAuthority('ADMIN')")
    @GetMapping("/foradmin")
    public String forAdmin() {
        return "foradmin";
    }
    @GetMapping("/zakaz")
    public String zakaz() { return "zakaz";}
    @GetMapping("/index")
    public String index() { return "index";}
    }
