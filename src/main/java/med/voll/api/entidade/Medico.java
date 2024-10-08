package med.voll.api.entidade;

import jakarta.persistence.Embedded;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import med.voll.api.dto.DadosAtualizarMedicoDTO;
import med.voll.api.dto.MedicoDTO;
import med.voll.api.enumMedic.Especialidade;

@Table(name = "medicos")
@Entity(name = "Medico")
@Getter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode (of="id")
public class Medico {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nome;
    private String email;
    private String crm;
    private String telefone;
    private Boolean ativo;

    @Embedded
    private Endereco endereco;

    @Enumerated(EnumType.STRING)
    private Especialidade especialidade;
    

    public Medico(MedicoDTO dados){
        this.nome = dados.nome();
        this.email = dados.email();
        this.crm = dados.crm();
        this.endereco = new Endereco(dados.endereco());
        this.especialidade = dados.especialidade();
        this.telefone = dados.telefone();
        this.ativo = true;
    }


    public void atualizarInformacao(@Valid DadosAtualizarMedicoDTO dados) {
        this.nome = (dados.nome() != null) ? dados.nome() : this.nome;
        this.telefone = (dados.telefone() != null) ? dados.telefone() : this.telefone;
        if (dados.endereco() != null) {
            this.endereco.atualizarInformacao(dados.endereco());
        }
    }


    public void excluir() {
        this.ativo = false;
    }

}