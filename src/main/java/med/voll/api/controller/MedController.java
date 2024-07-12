package med.voll.api.controller;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import med.voll.api.entidade.DadosCadastroMedico;


@RestController
@RequestMapping("postMedico")
public class MedController {

    @PostMapping
    public DadosCadastroMedico cadastrar(@RequestBody DadosCadastroMedico dados){
        return dados;
    }

}
