package fr.formation.api;

import java.util.ArrayList;
import java.util.List;
import fr.formation.model.Parcours;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.annotation.JsonView;

import fr.formation.api.request.ParcoursRequest;
import fr.formation.api.response.ParcoursResponse;
import fr.formation.exception.AnimalNotFoundException;
import fr.formation.exception.ParcoursNotFoundException;
import fr.formation.exception.ParcoursNotValidException;
import fr.formation.exception.VilleNotFoundException;
import fr.formation.repo.IAnimalRepository;
import fr.formation.repo.IParcoursRepository;
import fr.formation.repo.IVilleRepository;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;


@RestController
@RequestMapping("/api/parcours")
public class ParcoursApiController {
    @Autowired
    private IParcoursRepository repoParcours;
    @Autowired
    private IAnimalRepository repoAnimal;
    @Autowired
    private IVilleRepository repoVille;
    

    @GetMapping
    @JsonView(Views.Parcours.class)
    public List<Parcours> findAll(){
        return this.repoParcours.findAll();
    }

    @GetMapping("/{id}")
    @Transactional
    public ParcoursResponse findById(@PathVariable int id){
        Parcours parcours = this.repoParcours.findById(id).orElseThrow(ParcoursNotFoundException ::new);
        ParcoursResponse response = new ParcoursResponse();

        BeanUtils.copyProperties(parcours, response);

        return response;
    }

    @GetMapping("/animal/{animalId}")
    public List<ParcoursResponse> findAllByAnimalId(@PathVariable int animalId){
        List<Parcours> listeParcours = this.repoParcours.findByAnimal(this.repoAnimal.findById(animalId).get());
        List<ParcoursResponse> listeParcoursResponses = new ArrayList<>();

        for (Parcours p : listeParcours){
            listeParcoursResponses.add(ParcoursResponse.convert(p));
        }
        return listeParcoursResponses;
    }

    @PostMapping
    @JsonView(Views.Parcours.class)
    public ParcoursResponse add(@Valid @RequestBody ParcoursRequest parcoursRequest, BindingResult result ){
        if (result.hasErrors()){
            throw new ParcoursNotValidException();
        }
        Parcours parcours = new Parcours();
        BeanUtils.copyProperties(parcoursRequest, parcours);
        System.out.println(parcoursRequest.getTempsParcours());
        System.out.println(parcours.getTempsParcours());
        parcours.setVille(this.repoVille.findByNom(parcoursRequest.getVilleParcours()).orElseThrow(VilleNotFoundException::new));
        parcours.setAnimal(this.repoAnimal.findById(parcoursRequest.getAnimalId()).orElseThrow(AnimalNotFoundException::new));
        this.repoParcours.save(parcours);
        return ParcoursResponse.convert(parcours);
    }

    @PutMapping("/{id}")
    @JsonView(Views.Parcours.class)
    public Parcours edit(@PathVariable int id, @Valid @RequestBody ParcoursRequest parcoursRequest, BindingResult result){
        if (result.hasErrors()){
            throw new ParcoursNotValidException();
        }

        Parcours parcours = this.repoParcours.findById(id).orElseThrow(ParcoursNotFoundException :: new);

        BeanUtils.copyProperties(parcoursRequest, parcours);

        return this.repoParcours.save(parcours);
    }
    
    @DeleteMapping("/{id}")
    public void deleteById(@PathVariable int id){
        this.repoParcours.deleteById(id);
    }

}
