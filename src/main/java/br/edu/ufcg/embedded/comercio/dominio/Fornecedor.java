package br.edu.ufcg.embedded.comercio.dominio;
import org.springframework.roo.addon.javabean.RooJavaBean;
import org.springframework.roo.addon.jpa.activerecord.RooJpaActiveRecord;
import org.springframework.roo.addon.tostring.RooToString;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.validation.constraints.Pattern;
import java.util.HashSet;
import java.util.Set;
import javax.persistence.CascadeType;
import javax.persistence.ManyToMany;
import org.springframework.roo.addon.json.RooJson;

@RooJavaBean
@RooToString
@RooJpaActiveRecord
@RooJson(deepSerialize = true)
public class Fornecedor {

    /**
     */
    @NotNull
    @Size(min = 2)
    private String nomeFantasia;

    /**
     */
    @Size(min = 2)
    private String razaoSocial;

    /**
     */
    @NotNull
    @Pattern(regexp = "/^d{2}.d{3}.d{3}/d{4}-d{2}$/")
    private String cnpj;

    /**
     */
    private String endereco;

    /**
     */
    @Pattern(regexp = "/^d{5}-d{3}$/")
    private String cep;

    /**
     */
    @ManyToMany(cascade = CascadeType.ALL, mappedBy = "fornecedor")
    private Set<Produto> produtos = new HashSet<Produto>();
}
