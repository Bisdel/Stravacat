package fr.formation.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import fr.formation.exception.AnimalNotFoundException;
import fr.formation.model.Parcours;
import fr.formation.repo.IParcoursRepository;
import jakarta.validation.Valid;

@Controller
public class ParcoursController {
    @Autowired
    private IParcoursRepository repoParcours;

    @GetMapping("/parcours")
    public String findAll(Model model){
        model.addAttribute("animaux", this.repoParcours.findAll());

        return "parcours/index";
    }

    @GetMapping("/parcours/ajouter")
    public String add(){
        return "parcours/form";
    }

    @PostMapping({"/parcours/ajouter", "/parcours/modifier/{id}"})
    public String add(@Valid Parcours parcours, BindingResult result, Model model){
        if (result.hasErrors()){
            model.addAttribute("erreurs", result);
            return "parcours/form";
        }
        this.repoParcours.save(parcours);

        return "redirect:/index";
    }

    @GetMapping("/parcours/modifier/{id}")
    public String edit(@PathVariable int id, Model model){
        Parcours parcours = this.repoParcours.findById(id).orElseThrow(AnimalNotFoundException ::new);
        model.addAttribute("parcours", parcours);
        return "parcours/form";
    }

    @PostMapping("/parcours/modifier/{id}")
    public String edit(@Valid Parcours parcours, BindingResult result, Model model){
        System.out.println(parcours.getId());
        if (result.hasErrors()){
            model.addAttribute("erreurs", result);
            return "parcours/form";
        }
        this.repoParcours.save(parcours);

        return "redirect:/index";
    }

    @GetMapping("/parcours/supprimer/{id}")
    public String deleteById(@PathVariable int id){
        try{
            this.repoParcours.deleteById(id);
        }
        catch(Exception ex){
            return "redirect:/parcours?erreur";
        }
        return "redirect:/parcours";
    }
    
}
