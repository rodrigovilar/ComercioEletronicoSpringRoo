package br.edu.ufcg.embedded.comercio.dominio;
import java.util.Date;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.Digits;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.roo.addon.javabean.RooJavaBean;
import org.springframework.roo.addon.jpa.activerecord.RooJpaActiveRecord;
import org.springframework.roo.addon.tostring.RooToString;
import java.util.HashSet;
import java.util.Set;
import javax.persistence.CascadeType;
import javax.persistence.ManyToMany;
import org.springframework.roo.addon.json.RooJson;

@RooJavaBean
@RooToString
@RooJpaActiveRecord
@RooJson(deepSerialize = true)
public class Cliente {

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
    @Value("1000")
    @Digits(integer = 6, fraction = 2)
    private float credito;

    /**
     */
    @ManyToMany(cascade = CascadeType.ALL, mappedBy = "cliente")
    private Set<Pedido> pedidos = new HashSet<Pedido>();
}
