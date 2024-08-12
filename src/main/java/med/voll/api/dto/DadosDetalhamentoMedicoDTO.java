package med.voll.api.dto;

import med.voll.api.entidade.Endereco;
import med.voll.api.entidade.Medico;
import med.voll.api.enumMedic.Especialidade;

public record DadosDetalhamentoMedicoDTO(Long id, String nome, String email, String crm, String telefone, Especialidade especialidade, Endereco endereco) {
    public DadosDetalhamentoMedicoDTO(Medico medico){
        this(medico.getId(), medico.getNome(), medico.getEmail(), medico.getCrm(), medico.getTelefone(), medico.getEspecialidade(), medico.getEndereco());
    }
}
