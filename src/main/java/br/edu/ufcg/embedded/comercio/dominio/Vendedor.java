package br.edu.ufcg.embedded.comercio.dominio;
import java.util.Date;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.roo.addon.javabean.RooJavaBean;
import org.springframework.roo.addon.jpa.activerecord.RooJpaActiveRecord;
import org.springframework.roo.addon.tostring.RooToString;
import java.util.HashSet;
import java.util.Set;
import javax.persistence.CascadeType;
import javax.persistence.ManyToMany;

@RooJavaBean
@RooToString
@RooJpaActiveRecord
public class Vendedor {

    /**
     */
    @NotNull
    @Size(min = 2)
    private String nome;

    /**
     */
    @NotNull
    @Pattern(regexp = "/^d{3}.d{3}.d{3}-d{2}$/")
    private String cpf;

    /**
     */
    @Temporal(TemporalType.TIMESTAMP)
    @DateTimeFormat(style = "M-")
    private Date nascimento;

    /**
     */
    private String email;

    /**
     */
    @NotNull
    private String matricula;

    /**
     */
    @ManyToMany(cascade = CascadeType.ALL, mappedBy = "vendedor")
    private Set<Pedido> vendas = new HashSet<Pedido>();
}
