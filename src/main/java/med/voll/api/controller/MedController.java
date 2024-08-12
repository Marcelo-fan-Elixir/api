package med.voll.api.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriBuilder;
import org.springframework.web.util.UriComponentsBuilder;

import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import jakarta.websocket.server.PathParam;
import med.voll.api.dto.DadosAtualizarMedicoDTO;
import med.voll.api.dto.DadosDetalhamentoMedicoDTO;
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
    public ResponseEntity save(@RequestBody @Valid MedicoDTO dados, UriComponentsBuilder uriBuiler){
        var medico = new Medico(dados);
        repository.save(medico);
        //criando a uri para devolver o local onde pode ser acessado o novo elemento criado
        var uri = uriBuiler.path("/medicos/{id}").buildAndExpand(medico.getId()).toUri();
        return ResponseEntity.created(uri).body(new DadosDetalhamentoMedicoDTO(medico));
    }

    @GetMapping
    public ResponseEntity<Page<DadosListagemMedicoDTO>> list(@PageableDefault(size = 10, sort = {"nome"}) Pageable paginacao){
        //return repository.findAll(paginacao).stream().map(DadosListagemMedicoDTO::new).toList();
        //Com o Page, nao precisamos passar para stream e usar o toList porque ele ja faz isso automaticamente
        var page = repository.findAllByAtivoTrue(paginacao).map(DadosListagemMedicoDTO::new);
        return ResponseEntity.ok(page);
    }

    @GetMapping("/{id}")
    public ResponseEntity getMedico(@PathVariable Long id){
        var medico = repository.getReferenceById(id);
        return ResponseEntity.ok(new DadosDetalhamentoMedicoDTO(medico));
    }

    @PutMapping
    @Transactional
    public ResponseEntity atualizar(@RequestBody @Valid DadosAtualizarMedicoDTO dados){
        var medico = repository.getReferenceById(dados.id());
        medico.atualizarInformacao(dados);
        return ResponseEntity.ok(new DadosDetalhamentoMedicoDTO(medico));
    }

    @DeleteMapping("/{id}")
    @Transactional
    public ResponseEntity excluir(@PathVariable Long id){
        var medico = repository.getReferenceById(id);
        medico.excluir();
        return ResponseEntity.noContent().build();
    }


}
