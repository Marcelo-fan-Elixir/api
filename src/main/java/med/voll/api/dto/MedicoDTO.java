package med.voll.api.dto;

import jakarta.validation.Valid;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import med.voll.api.enumMedic.Especialidade;

public record MedicoDTO(
     @NotBlank
     String nome,
     @NotBlank
     @Email
     String email,
     @NotBlank
     @Pattern(regexp = "\\d{11}")
     String telefone,
     @NotBlank
     @Pattern(regexp = "\\d{4,6}")
     String crm,
     @NotNull
     @Valid
     EnderecoDTO endereco,
     @NotNull
     Especialidade especialidade 
     ) {
 
    
}
