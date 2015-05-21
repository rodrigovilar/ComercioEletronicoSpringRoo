package br.edu.ufcg.embedded.comercio.dominio;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityManager;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.PersistenceContext;
import javax.persistence.Version;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import org.apache.commons.lang3.builder.ReflectionToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.roo.addon.javabean.RooJavaBean;
import org.springframework.roo.addon.jpa.activerecord.RooJpaActiveRecord;
import org.springframework.roo.addon.json.RooJson;
import org.springframework.roo.addon.tostring.RooToString;
import org.springframework.transaction.annotation.Transactional;

import flexjson.JSONDeserializer;
import flexjson.JSONSerializer;

@Entity
@Configurable
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
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "fornecedor")
    private Set<Produto> produtos = new HashSet<Produto>();

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

	public String getNomeFantasia() {
        return this.nomeFantasia;
    }

	public void setNomeFantasia(String nomeFantasia) {
        this.nomeFantasia = nomeFantasia;
    }

	public String getRazaoSocial() {
        return this.razaoSocial;
    }

	public void setRazaoSocial(String razaoSocial) {
        this.razaoSocial = razaoSocial;
    }

	public String getCnpj() {
        return this.cnpj;
    }

	public void setCnpj(String cnpj) {
        this.cnpj = cnpj;
    }

	public String getEndereco() {
        return this.endereco;
    }

	public void setEndereco(String endereco) {
        this.endereco = endereco;
    }

	public String getCep() {
        return this.cep;
    }

	public void setCep(String cep) {
        this.cep = cep;
    }

	public Set<Produto> getProdutos() {
        return this.produtos;
    }

	public void setProdutos(Set<Produto> produtos) {
        this.produtos = produtos;
    }

	@PersistenceContext
    transient EntityManager entityManager;

	public static final List<String> fieldNames4OrderClauseFilter = java.util.Arrays.asList("nomeFantasia", "razaoSocial", "cnpj", "endereco", "cep", "produtos");

	public static final EntityManager entityManager() {
        EntityManager em = new Fornecedor().entityManager;
        if (em == null) throw new IllegalStateException("Entity manager has not been injected (is the Spring Aspects JAR configured as an AJC/AJDT aspects library?)");
        return em;
    }

	public static long countFornecedors() {
        return entityManager().createQuery("SELECT COUNT(o) FROM Fornecedor o", Long.class).getSingleResult();
    }

	public static List<Fornecedor> findAllFornecedors() {
        return entityManager().createQuery("SELECT o FROM Fornecedor o", Fornecedor.class).getResultList();
    }

	public static List<Fornecedor> findAllFornecedors(String sortFieldName, String sortOrder) {
        String jpaQuery = "SELECT o FROM Fornecedor o";
        if (fieldNames4OrderClauseFilter.contains(sortFieldName)) {
            jpaQuery = jpaQuery + " ORDER BY " + sortFieldName;
            if ("ASC".equalsIgnoreCase(sortOrder) || "DESC".equalsIgnoreCase(sortOrder)) {
                jpaQuery = jpaQuery + " " + sortOrder;
            }
        }
        return entityManager().createQuery(jpaQuery, Fornecedor.class).getResultList();
    }

	public static Fornecedor findFornecedor(Long id) {
        if (id == null) return null;
        return entityManager().find(Fornecedor.class, id);
    }

	public static List<Fornecedor> findFornecedorEntries(int firstResult, int maxResults) {
        return entityManager().createQuery("SELECT o FROM Fornecedor o", Fornecedor.class).setFirstResult(firstResult).setMaxResults(maxResults).getResultList();
    }

	public static List<Fornecedor> findFornecedorEntries(int firstResult, int maxResults, String sortFieldName, String sortOrder) {
        String jpaQuery = "SELECT o FROM Fornecedor o";
        if (fieldNames4OrderClauseFilter.contains(sortFieldName)) {
            jpaQuery = jpaQuery + " ORDER BY " + sortFieldName;
            if ("ASC".equalsIgnoreCase(sortOrder) || "DESC".equalsIgnoreCase(sortOrder)) {
                jpaQuery = jpaQuery + " " + sortOrder;
            }
        }
        return entityManager().createQuery(jpaQuery, Fornecedor.class).setFirstResult(firstResult).setMaxResults(maxResults).getResultList();
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
            Fornecedor attached = Fornecedor.findFornecedor(this.id);
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
    public Fornecedor merge() {
        if (this.entityManager == null) this.entityManager = entityManager();
        Fornecedor merged = this.entityManager.merge(this);
        this.entityManager.flush();
        return merged;
    }

	public String toString() {
        return ReflectionToStringBuilder.toString(this, ToStringStyle.SHORT_PREFIX_STYLE);
    }

	public String toJson() {
        return new JSONSerializer()
        .exclude("*.class").deepSerialize(this);
    }

	public String toJson(String[] fields) {
        return new JSONSerializer()
        .include(fields).exclude("*.class").deepSerialize(this);
    }

	public static Fornecedor fromJsonToFornecedor(String json) {
        return new JSONDeserializer<Fornecedor>()
        .use(null, Fornecedor.class).deserialize(json);
    }

	public static String toJsonArray(Collection<Fornecedor> collection) {
        return new JSONSerializer()
        .exclude("*.class").deepSerialize(collection);
    }

	public static String toJsonArray(Collection<Fornecedor> collection, String[] fields) {
        return new JSONSerializer()
        .include(fields).exclude("*.class").deepSerialize(collection);
    }

	public static Collection<Fornecedor> fromJsonArrayToFornecedors(String json) {
        return new JSONDeserializer<List<Fornecedor>>()
        .use("values", Fornecedor.class).deserialize(json);
    }
}
