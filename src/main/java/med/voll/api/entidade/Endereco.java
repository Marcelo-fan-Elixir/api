package med.voll.api.entidade;

import jakarta.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import med.voll.api.dto.EnderecoDTO;

@Embeddable
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class Endereco {

    private String logradouro;
    private String bairro;
    private String cep;
    private String cidade;
    private String uf;
    private String numero;
    private String complemento;

    public Endereco(EnderecoDTO dados){
        this.logradouro = dados.logradouro();
        this.bairro = dados.bairro();
        this.cep = dados.cep();
        this.cidade = dados.cidade();
        this.uf = dados.uf();
        this.numero = dados.numero();
        this.complemento = dados.complemento();
    }

    public void atualizarInformacao(EnderecoDTO dados){
        this.logradouro = (dados.logradouro() != null) ? dados.logradouro() : this.logradouro;
        this.bairro = (dados.bairro() != null) ? dados.bairro() : this.bairro;
        this.cep = (dados.cep() != null) ? dados.cep() : this.cep;
        this.cidade = (dados.cidade() != null) ? dados.cidade() : this.cidade;
        this.uf = (dados.uf() != null) ? dados.uf() : this.uf;
        this.numero = (dados.numero() != null) ? dados.numero() : this.numero;
        this.complemento = (dados.complemento() != null) ? dados.complemento() : this.complemento;
    }

}
