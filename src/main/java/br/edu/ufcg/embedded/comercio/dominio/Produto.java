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
import javax.validation.constraints.Size;
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

@Configurable
@Entity
@RooJavaBean
@RooToString
@RooJpaActiveRecord
@RooJson(deepSerialize = true)
public class Produto {

    /**
     */
    @NotNull
    @Size(min = 2)
    private String nome;

    /**
     */
    @NotNull
    @Column(unique = true)
    private int codBarras;

    /**
     */
    private float preco;

    /**
     */
    @NotNull
    @ManyToOne
    private Fornecedor fornecedor;

	public String toJson() {
        return new JSONSerializer()
        .exclude("*.class").deepSerialize(this);
    }

	public String toJson(String[] fields) {
        return new JSONSerializer()
        .include(fields).exclude("*.class").deepSerialize(this);
    }

	public static Produto fromJsonToProduto(String json) {
        return new JSONDeserializer<Produto>()
        .use(null, Produto.class).deserialize(json);
    }

	public static String toJsonArray(Collection<Produto> collection) {
        return new JSONSerializer()
        .exclude("*.class").deepSerialize(collection);
    }

	public static String toJsonArray(Collection<Produto> collection, String[] fields) {
        return new JSONSerializer()
        .include(fields).exclude("*.class").deepSerialize(collection);
    }

	public static Collection<Produto> fromJsonArrayToProdutoes(String json) {
        return new JSONDeserializer<List<Produto>>()
        .use("values", Produto.class).deserialize(json);
    }

	public String getNome() {
        return this.nome;
    }

	public void setNome(String nome) {
        this.nome = nome;
    }

	public int getCodBarras() {
        return this.codBarras;
    }

	public void setCodBarras(int codBarras) {
        this.codBarras = codBarras;
    }

	public float getPreco() {
        return this.preco;
    }

	public void setPreco(float preco) {
        this.preco = preco;
    }

	public Fornecedor getFornecedor() {
        return this.fornecedor;
    }

	public void setFornecedor(Fornecedor fornecedor) {
        this.fornecedor = fornecedor;
    }

	@PersistenceContext
    transient EntityManager entityManager;

	public static final List<String> fieldNames4OrderClauseFilter = java.util.Arrays.asList("nome", "codBarras", "preco", "fornecedor");

	public static final EntityManager entityManager() {
        EntityManager em = new Produto().entityManager;
        if (em == null) throw new IllegalStateException("Entity manager has not been injected (is the Spring Aspects JAR configured as an AJC/AJDT aspects library?)");
        return em;
    }

	public static long countProdutoes() {
        return entityManager().createQuery("SELECT COUNT(o) FROM Produto o", Long.class).getSingleResult();
    }

	public static List<Produto> findAllProdutoes() {
        return entityManager().createQuery("SELECT o FROM Produto o", Produto.class).getResultList();
    }

	public static List<Produto> findAllProdutoes(String sortFieldName, String sortOrder) {
        String jpaQuery = "SELECT o FROM Produto o";
        if (fieldNames4OrderClauseFilter.contains(sortFieldName)) {
            jpaQuery = jpaQuery + " ORDER BY " + sortFieldName;
            if ("ASC".equalsIgnoreCase(sortOrder) || "DESC".equalsIgnoreCase(sortOrder)) {
                jpaQuery = jpaQuery + " " + sortOrder;
            }
        }
        return entityManager().createQuery(jpaQuery, Produto.class).getResultList();
    }

	public static Produto findProduto(Long id) {
        if (id == null) return null;
        return entityManager().find(Produto.class, id);
    }

	public static List<Produto> findProdutoEntries(int firstResult, int maxResults) {
        return entityManager().createQuery("SELECT o FROM Produto o", Produto.class).setFirstResult(firstResult).setMaxResults(maxResults).getResultList();
    }

	public static List<Produto> findProdutoEntries(int firstResult, int maxResults, String sortFieldName, String sortOrder) {
        String jpaQuery = "SELECT o FROM Produto o";
        if (fieldNames4OrderClauseFilter.contains(sortFieldName)) {
            jpaQuery = jpaQuery + " ORDER BY " + sortFieldName;
            if ("ASC".equalsIgnoreCase(sortOrder) || "DESC".equalsIgnoreCase(sortOrder)) {
                jpaQuery = jpaQuery + " " + sortOrder;
            }
        }
        return entityManager().createQuery(jpaQuery, Produto.class).setFirstResult(firstResult).setMaxResults(maxResults).getResultList();
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
            Produto attached = Produto.findProduto(this.id);
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
    public Produto merge() {
        if (this.entityManager == null) this.entityManager = entityManager();
        Produto merged = this.entityManager.merge(this);
        this.entityManager.flush();
        return merged;
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

	public String toString() {
        return ReflectionToStringBuilder.toString(this, ToStringStyle.SHORT_PREFIX_STYLE);
    }
}
