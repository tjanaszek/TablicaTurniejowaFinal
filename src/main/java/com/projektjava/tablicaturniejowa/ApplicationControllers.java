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
    TournamentJDBCDAO tournamentJDBCDAO = new TournamentJDBCDAO();

    @RequestMapping("/")
    public String get(Model model) {
        model.addAttribute("name","Anonymous");
        return "index";
    }
    @RequestMapping("/logowanie")
    public String loginPage(User user, Model model) {
        User curr = userRepo.findUserName(user.user_name);
        if (curr.getuser_name() == null){
            return "nieznanyuser";
        }
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
        if (userRepo.add(user))
            return "index";
        else
            return "nickistnieje";
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

    @RequestMapping("/zledane")
    public String backtologin(Model model){
        model.addAttribute("user", new User());
        return "logowanie";
    }

    @RequestMapping("/unknownuser")
    public String backtoindex(){
        return "index";
    }

    @RequestMapping("/nicktaken")
    public String backtoreg(){
        return "index";
    }

    //Akcje na stronie admina
    @GetMapping("/registerTournament")
    public String registerTournament(Model model) {
        model.addAttribute("tournament", new TournamentBean());
        return "rejestracjaTurnieju";
    }

    @RequestMapping("/dodajTurniej")
    public String addTournament(TournamentBean tournament){
        tournamentJDBCDAO.add(tournament);
        return "stronaadmina";
    }
    @RequestMapping("/allTournaments")
    public String allTournaments(Model model){
        model.addAttribute("tournaments", tournamentJDBCDAO.findAll());
        return "turnieje";
    }
    @RequestMapping("/closeRegister")
    public String closeRegister(@RequestParam(value="id", required=true) String id, Model model){
        System.out.println(id);
        tournamentJDBCDAO.closeRegister(id);
        model.addAttribute("tournaments", tournamentJDBCDAO.findAll());
        return "turnieje";
    }

}
