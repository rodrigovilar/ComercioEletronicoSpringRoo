package br.edu.ufcg.embedded.comercio.dominio;
import org.apache.commons.lang3.builder.ReflectionToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.roo.addon.javabean.RooJavaBean;
import org.springframework.roo.addon.jpa.activerecord.RooJpaActiveRecord;
import org.springframework.roo.addon.tostring.RooToString;
import flexjson.JSONDeserializer;
import flexjson.JSONSerializer;
import java.util.Collection;
import java.util.List;
import javax.validation.constraints.NotNull;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityManager;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.PersistenceContext;
import javax.persistence.Version;
import org.springframework.roo.addon.json.RooJson;
import org.springframework.transaction.annotation.Transactional;

@Entity
@Configurable
@RooJavaBean
@RooToString
@RooJpaActiveRecord
@RooJson(deepSerialize = true)
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

	public String toString() {
        return ReflectionToStringBuilder.toString(this, ToStringStyle.SHORT_PREFIX_STYLE);
    }

	@Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private Long id;

	@Version
    @Column(name = "version")
    private Integer version;

	public Long getId() {
        return this.id;
    }

	public void setId(Long id) {
        this.id = id;
    }

	public Integer getVersion() {
        return this.version;
    }

	public void setVersion(Integer version) {
        this.version = version;
    }

	public float getQuantidade() {
        return this.quantidade;
    }

	public void setQuantidade(float quantidade) {
        this.quantidade = quantidade;
    }

	public Produto getProduto() {
        return this.produto;
    }

	public void setProduto(Produto produto) {
        this.produto = produto;
    }

	public Pedido getPedido() {
        return this.pedido;
    }

	public void setPedido(Pedido pedido) {
        this.pedido = pedido;
    }

	public String toJson() {
        return new JSONSerializer()
        .exclude("*.class").deepSerialize(this);
    }

	public String toJson(String[] fields) {
        return new JSONSerializer()
        .include(fields).exclude("*.class").deepSerialize(this);
    }

	public static ItemPedido fromJsonToItemPedido(String json) {
        return new JSONDeserializer<ItemPedido>()
        .use(null, ItemPedido.class).deserialize(json);
    }

	public static String toJsonArray(Collection<ItemPedido> collection) {
        return new JSONSerializer()
        .exclude("*.class").deepSerialize(collection);
    }

	public static String toJsonArray(Collection<ItemPedido> collection, String[] fields) {
        return new JSONSerializer()
        .include(fields).exclude("*.class").deepSerialize(collection);
    }

	public static Collection<ItemPedido> fromJsonArrayToItemPedidoes(String json) {
        return new JSONDeserializer<List<ItemPedido>>()
        .use("values", ItemPedido.class).deserialize(json);
    }

	@PersistenceContext
    transient EntityManager entityManager;

	public static final List<String> fieldNames4OrderClauseFilter = java.util.Arrays.asList("quantidade", "produto", "pedido");

	public static final EntityManager entityManager() {
        EntityManager em = new ItemPedido().entityManager;
        if (em == null) throw new IllegalStateException("Entity manager has not been injected (is the Spring Aspects JAR configured as an AJC/AJDT aspects library?)");
        return em;
    }

	public static long countItemPedidoes() {
        return entityManager().createQuery("SELECT COUNT(o) FROM ItemPedido o", Long.class).getSingleResult();
    }

	public static List<ItemPedido> findAllItemPedidoes() {
        return entityManager().createQuery("SELECT o FROM ItemPedido o", ItemPedido.class).getResultList();
    }

	public static List<ItemPedido> findAllItemPedidoes(String sortFieldName, String sortOrder) {
        String jpaQuery = "SELECT o FROM ItemPedido o";
        if (fieldNames4OrderClauseFilter.contains(sortFieldName)) {
            jpaQuery = jpaQuery + " ORDER BY " + sortFieldName;
            if ("ASC".equalsIgnoreCase(sortOrder) || "DESC".equalsIgnoreCase(sortOrder)) {
                jpaQuery = jpaQuery + " " + sortOrder;
            }
        }
        return entityManager().createQuery(jpaQuery, ItemPedido.class).getResultList();
    }

	public static ItemPedido findItemPedido(Long id) {
        if (id == null) return null;
        return entityManager().find(ItemPedido.class, id);
    }

	public static List<ItemPedido> findItemPedidoEntries(int firstResult, int maxResults) {
        return entityManager().createQuery("SELECT o FROM ItemPedido o", ItemPedido.class).setFirstResult(firstResult).setMaxResults(maxResults).getResultList();
    }

	public static List<ItemPedido> findItemPedidoEntries(int firstResult, int maxResults, String sortFieldName, String sortOrder) {
        String jpaQuery = "SELECT o FROM ItemPedido o";
        if (fieldNames4OrderClauseFilter.contains(sortFieldName)) {
            jpaQuery = jpaQuery + " ORDER BY " + sortFieldName;
            if ("ASC".equalsIgnoreCase(sortOrder) || "DESC".equalsIgnoreCase(sortOrder)) {
                jpaQuery = jpaQuery + " " + sortOrder;
            }
        }
        return entityManager().createQuery(jpaQuery, ItemPedido.class).setFirstResult(firstResult).setMaxResults(maxResults).getResultList();
    }

	@Transactional
    public void persist() {
        if (this.entityManager == null) this.entityManager = entityManager();
        this.entityManager.persist(this);
    }

	@Transactional
    public void remove() {
        if (this.entityManager == null) this.entityManager = entityManager();
        if (this.entityManager.contains(this)) {
            this.entityManager.remove(this);
        } else {
            ItemPedido attached = ItemPedido.findItemPedido(this.id);
            this.entityManager.remove(attached);
        }
    }

	@Transactional
    public void flush() {
        if (this.entityManager == null) this.entityManager = entityManager();
        this.entityManager.flush();
    }

	@Transactional
    public void clear() {
        if (this.entityManager == null) this.entityManager = entityManager();
        this.entityManager.clear();
    }

	@Transactional
    public ItemPedido merge() {
        if (this.entityManager == null) this.entityManager = entityManager();
        ItemPedido merged = this.entityManager.merge(this);
        this.entityManager.flush();
        return merged;
    }
}
