package com.projektjava.tablicaturniejowa;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class ApplicationControllers {

    /*@Autowired
    private UserRepository userRepo;*/

    UserJDBCDAO userRepo = new UserJDBCDAO();

    @RequestMapping("/")
    public String get(Model model) {
        model.addAttribute("name","Anonymous");
        return "index";
    }
    @RequestMapping("/logowanie")
    public String loginPage(User user, Model model) {
        User curr = userRepo.findUserName(user.user_name);
        if (curr.getpassword().equals(user.password)){
            if (curr.getadmin() == 1){
                model.addAttribute("administrator", curr);
                return "stronaadmina";
            }
            else{
                model.addAttribute("zawodnik", curr);
                return "stronazawodnika";
            }
        }
        else
            return "bladlogowania";
    }

    /*@GetMapping("/rejestracja")
    public String registerStart(Model model){
        model.addAttribute("user", new User());
        return "rejestracja";
    }*/

    @PostMapping("/rejestracja")
    public String registerPage(User user) {
        userRepo.add(user);
        System.out.println("Siema");
        return "index";
    }

    @GetMapping("/loginOrRegister")
    public String loginOrRegister(@RequestParam(value="action", required=true) String action, Model model) {
        if (action.equals("login")) {
            model.addAttribute("user", new User());
            return "redirect:/logowanie";
        } else if (action.equals("register")) {
            model.addAttribute("user", new User());
            return "redirect:/rejestracja";
        }
        return "loginOrRegister";
    }

    @GetMapping("/register")
    public String registerStart(Model model){
        model.addAttribute("user", new User());
        return "rejestracja";
    }

    @GetMapping("/login")
    public String loginStart(Model model){
        model.addAttribute("user", new User());
        return "logowanie";
    }

    //akcja do przycisku "Zaloguj"
//    @RequestMapping("/login")
//    public String login(@RequestParam(value="action", required=true) String action) {
//        if (action.equals("login")) {
//            return "redirect:/logowanie";
//        } else if (action.equals("register")) {
//            return "redirect:/rejestracja";
//        }
//        return "login";
//    }

    //akcja do przycisku "Zarejestruj się"
//    @RequestMapping("/login")
//    public String login(@RequestParam(value="action", required=true) String action) {
//        if (action.equals("login")) {
//            return "redirect:/logowanie";
//        } else if (action.equals("register")) {
//            return "redirect:/rejestracja";
//        }
//        return "login";
//    }

}
