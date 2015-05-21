package br.edu.ufcg.embedded.comercio.dominio;
import org.springframework.roo.addon.javabean.RooJavaBean;
import org.springframework.roo.addon.jpa.activerecord.RooJpaActiveRecord;
import org.springframework.roo.addon.tostring.RooToString;
import javax.validation.constraints.NotNull;
import javax.persistence.ManyToOne;

@RooJavaBean
@RooToString
@RooJpaActiveRecord
public class ItemPedido {

    /**
     */
    @NotNull
    private float quantidade;

    /**
     */
    @NotNull
    @ManyToOne
    private Produto produto;

    /**
     */
    @NotNull
    @ManyToOne
    private Pedido pedido;
}
