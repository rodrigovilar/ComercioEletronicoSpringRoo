package br.edu.ufcg.embedded.comercio.dominio;
import java.util.Collection;
import java.util.Date;
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
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Version;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import org.apache.commons.lang3.builder.ReflectionToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.format.annotation.DateTimeFormat;
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
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "vendedor")
    private Set<Pedido> vendas = new HashSet<Pedido>();

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

	public static Vendedor fromJsonToVendedor(String json) {
        return new JSONDeserializer<Vendedor>()
        .use(null, Vendedor.class).deserialize(json);
    }

	public static String toJsonArray(Collection<Vendedor> collection) {
        return new JSONSerializer()
        .exclude("*.class").deepSerialize(collection);
    }

	public static String toJsonArray(Collection<Vendedor> collection, String[] fields) {
        return new JSONSerializer()
        .include(fields).exclude("*.class").deepSerialize(collection);
    }

	public static Collection<Vendedor> fromJsonArrayToVendedors(String json) {
        return new JSONDeserializer<List<Vendedor>>()
        .use("values", Vendedor.class).deserialize(json);
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

	public String getNome() {
        return this.nome;
    }

	public void setNome(String nome) {
        this.nome = nome;
    }

	public String getCpf() {
        return this.cpf;
    }

	public void setCpf(String cpf) {
        this.cpf = cpf;
    }

	public Date getNascimento() {
        return this.nascimento;
    }

	public void setNascimento(Date nascimento) {
        this.nascimento = nascimento;
    }

	public String getEmail() {
        return this.email;
    }

	public void setEmail(String email) {
        this.email = email;
    }

	public String getMatricula() {
        return this.matricula;
    }

	public void setMatricula(String matricula) {
        this.matricula = matricula;
    }

	public Set<Pedido> getVendas() {
        return this.vendas;
    }

	public void setVendas(Set<Pedido> vendas) {
        this.vendas = vendas;
    }

	@PersistenceContext
    transient EntityManager entityManager;

	public static final List<String> fieldNames4OrderClauseFilter = java.util.Arrays.asList("nome", "cpf", "nascimento", "email", "matricula", "vendas");

	public static final EntityManager entityManager() {
        EntityManager em = new Vendedor().entityManager;
        if (em == null) throw new IllegalStateException("Entity manager has not been injected (is the Spring Aspects JAR configured as an AJC/AJDT aspects library?)");
        return em;
    }

	public static long countVendedors() {
        return entityManager().createQuery("SELECT COUNT(o) FROM Vendedor o", Long.class).getSingleResult();
    }

	public static List<Vendedor> findAllVendedors() {
        return entityManager().createQuery("SELECT o FROM Vendedor o", Vendedor.class).getResultList();
    }

	public static List<Vendedor> findAllVendedors(String sortFieldName, String sortOrder) {
        String jpaQuery = "SELECT o FROM Vendedor o";
        if (fieldNames4OrderClauseFilter.contains(sortFieldName)) {
            jpaQuery = jpaQuery + " ORDER BY " + sortFieldName;
            if ("ASC".equalsIgnoreCase(sortOrder) || "DESC".equalsIgnoreCase(sortOrder)) {
                jpaQuery = jpaQuery + " " + sortOrder;
            }
        }
        return entityManager().createQuery(jpaQuery, Vendedor.class).getResultList();
    }

	public static Vendedor findVendedor(Long id) {
        if (id == null) return null;
        return entityManager().find(Vendedor.class, id);
    }

	public static List<Vendedor> findVendedorEntries(int firstResult, int maxResults) {
        return entityManager().createQuery("SELECT o FROM Vendedor o", Vendedor.class).setFirstResult(firstResult).setMaxResults(maxResults).getResultList();
    }

	public static List<Vendedor> findVendedorEntries(int firstResult, int maxResults, String sortFieldName, String sortOrder) {
        String jpaQuery = "SELECT o FROM Vendedor o";
        if (fieldNames4OrderClauseFilter.contains(sortFieldName)) {
            jpaQuery = jpaQuery + " ORDER BY " + sortFieldName;
            if ("ASC".equalsIgnoreCase(sortOrder) || "DESC".equalsIgnoreCase(sortOrder)) {
                jpaQuery = jpaQuery + " " + sortOrder;
            }
        }
        return entityManager().createQuery(jpaQuery, Vendedor.class).setFirstResult(firstResult).setMaxResults(maxResults).getResultList();
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
            Vendedor attached = Vendedor.findVendedor(this.id);
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
    public Vendedor merge() {
        if (this.entityManager == null) this.entityManager = entityManager();
        Vendedor merged = this.entityManager.merge(this);
        this.entityManager.flush();
        return merged;
    }
}
