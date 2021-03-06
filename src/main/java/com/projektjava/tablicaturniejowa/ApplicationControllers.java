package com.projektjava.tablicaturniejowa;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class ApplicationControllers {


    UserJDBCDAO userRepo = new UserJDBCDAO();
    TournamentJDBCDAO tournamentJDBCDAO = new TournamentJDBCDAO();
    GameJDBCDAO gameJDBCDAO = new GameJDBCDAO();

    User currentUser = new User();

    @RequestMapping("/")
    public String get(Model model) {
        model.addAttribute("name","Anonymous");
        return "index";
    }
    @RequestMapping("/logowanie")
    public String loginPage(User user, Model model) {
        User curr = userRepo.findUserName(user.user_name);
        currentUser = curr;
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

    @PostMapping("/rejestracja")
    public String registerPage(User user) {
        if (userRepo.add(user))
            return "index";
        else
            return "nickistnieje";
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
    @RequestMapping("/addResulstByAdmin")
    public String addResulstByAdmin(Model model){
        model.addAttribute("games", gameJDBCDAO.findAllGamesAdmin());
        return "dodajwynikigraczy";
    }

    @RequestMapping("/wynikiostateczne")
    public String addfinalresults(@RequestParam(value="idGame", required=true) int idGame,@RequestParam(value="wynik", required=true) String wynik, Model model){
        int idWygrany;
        Game actualgame=gameJDBCDAO.findGameId(idGame);
        if(wynik.equals("Player1")) {
            idWygrany = actualgame.getIdPlayer1();
        }
        else if(wynik.equals("Player2")) {
            idWygrany = actualgame.getIdPlayer2();
        }
        else
            idWygrany=1;
        gameJDBCDAO.updategameresultAdmin(idGame, idWygrany);
        return "dodajwynikigraczy";
    }
    @RequestMapping("/closeRegister")
    public String closeRegister(@RequestParam(value="id", required=true) String id, @RequestParam(value="action", required=true) String action, Model model){
        if(action.equals("close")) {
            System.out.println("Zamkni??to rejestracj??");
            tournamentJDBCDAO.closeRegister(id);
        }
        else if(action.equals("end")){
            System.out.println("Zako??czono turniej");
            //zako??cz turniej
            tournamentJDBCDAO.endTournament(id);
        }
        model.addAttribute("tournaments", tournamentJDBCDAO.findAll());
        return "turnieje";
    }

    //na stronie zawodnika
    @RequestMapping("/rejestrujnaturniej")
    public String regtotour(@RequestParam(value="id", required=true) int id, Model model){
        currentUser.setIdTournament(id);
        userRepo.updatetournamentforuser(currentUser.getuser_name(), Integer.toString(id));
        return "stronazawodnika";
    }

    @RequestMapping("/dodajwynik")
    public String addResult(@RequestParam(value="id", required=true) int id, @RequestParam(value="wynik", required=true) String wynik, Model model){
        gameJDBCDAO.updategameresult(currentUser.getidUser(), id, wynik);
        return "stronazawodnika";
    }

    @RequestMapping("/zapiszsie")
    public String showtournamentstosign(Model model){
        model.addAttribute("tournaments", tournamentJDBCDAO.findAllOpen(currentUser));
        return "dolaczdoturnieju";
    }
    @RequestMapping("/wyniki")
    public String showgames(Model model){
        model.addAttribute("games", gameJDBCDAO.findAllGames(currentUser.getidUser()));
        return "dodajwynik";
    }
}
