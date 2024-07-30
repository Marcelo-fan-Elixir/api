package med.voll.api.dto;

import med.voll.api.entidade.Medico;
import med.voll.api.enumMedic.Especialidade;

public record DadosListagemMedicoDTO(String nome, String email, String crm, Especialidade especialidade) {
    public DadosListagemMedicoDTO(Medico medico){
        this(medico.getNome(), medico.getEmail(), medico.getCrm(), medico.getEspecialidade());
    }
}
