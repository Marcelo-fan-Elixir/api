package med.voll.api.dto;

import jakarta.validation.constraints.NotBlank;

public record DadosAtualizarMedicoDTO(
    @NotBlank
    Long id, 
    String nome,
    String 
    telefone, 
    EnderecoDTO endereco) {
    
}
