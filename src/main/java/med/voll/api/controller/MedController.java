package med.voll.api.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import med.voll.api.dto.MedicoDTO;
import med.voll.api.entidade.Medico;
import med.voll.api.repository.MedicoRepository;


@RestController
@RequestMapping("cadastroMedico")
public class MedController {
    
    @Autowired
    private MedicoRepository repository;

    @PostMapping
    @Transactional
    public MedicoDTO cadastrar(@RequestBody @Valid MedicoDTO dados){
        repository.save(new Medico(dados));
        return dados;
    }

}
