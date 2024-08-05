package med.voll.api.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import med.voll.api.dto.DadosAtualizarMedicoDTO;
import med.voll.api.dto.DadosListagemMedicoDTO;
import med.voll.api.dto.MedicoDTO;
import med.voll.api.entidade.Medico;
import med.voll.api.repository.MedicoRepository;


@RestController
@RequestMapping("medicos")
public class MedController {
    
    @Autowired
    private MedicoRepository repository;

    @PostMapping
    @Transactional
    public MedicoDTO save(@RequestBody @Valid MedicoDTO dados){
        repository.save(new Medico(dados));
        return dados;
    }

    @GetMapping
    public Page<DadosListagemMedicoDTO> list(@PageableDefault(size = 10, sort = {"nome"}) Pageable paginacao){
        //return repository.findAll(paginacao).stream().map(DadosListagemMedicoDTO::new).toList();
        //Com o Page, nao precisamos passar para stream e usar o toList porque ele ja faz isso automaticamente
        return repository.findAll(paginacao).map(DadosListagemMedicoDTO::new);
    }



    @PutMapping
    @Transactional
    public void atualizar(@RequestBody @Valid DadosAtualizarMedicoDTO dados){
        var medico = repository.getReferenceById(dados.id());
        medico.atualizarInformacao(dados);
    }
}
