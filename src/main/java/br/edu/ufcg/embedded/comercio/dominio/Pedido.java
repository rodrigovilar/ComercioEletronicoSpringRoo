package br.edu.ufcg.embedded.comercio.dominio;
import org.springframework.roo.addon.javabean.RooJavaBean;
import org.springframework.roo.addon.jpa.activerecord.RooJpaActiveRecord;
import org.springframework.roo.addon.tostring.RooToString;
import java.util.Date;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import org.springframework.format.annotation.DateTimeFormat;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotNull;
import java.util.HashSet;
import java.util.Set;
import javax.persistence.CascadeType;
import javax.persistence.ManyToMany;
import org.springframework.roo.addon.json.RooJson;

@RooJavaBean
@RooToString
@RooJpaActiveRecord
@RooJson(deepSerialize = true)
public class Pedido {

    /**
     */
    @Temporal(TemporalType.TIMESTAMP)
    @DateTimeFormat(style = "M-")
    private Date lancamento;

    /**
     */
    @NotNull
    @ManyToOne
    private Cliente cliente;

    /**
     */
    @ManyToOne
    private Vendedor vendedor;

    /**
     */
    @ManyToMany(cascade = CascadeType.ALL, mappedBy = "pedido")
    private Set<ItemPedido> itens = new HashSet<ItemPedido>();
}
