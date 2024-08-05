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
import med.voll.api.dto.DadosListagemPacienteDTO;
import med.voll.api.dto.PacienteDTO;
import med.voll.api.entidade.Paciente;
import med.voll.api.repository.PacienteRepository;

@RestController
@RequestMapping("pacientes")
public class PaciController {

    @Autowired
    private PacienteRepository pacienteRepository;

    @PostMapping
    @Transactional
    public PacienteDTO savePaciente(@RequestBody @Valid PacienteDTO dados) {
        pacienteRepository.save(new Paciente(dados));
        return dados;
    }

    @GetMapping
    public Page<DadosListagemPacienteDTO> list(@PageableDefault(page = 0, size = 10, sort = {"nome"}) Pageable paginacao) {
        return pacienteRepository.findAll(paginacao).map(DadosListagemPacienteDTO::new);
    }

    
}
