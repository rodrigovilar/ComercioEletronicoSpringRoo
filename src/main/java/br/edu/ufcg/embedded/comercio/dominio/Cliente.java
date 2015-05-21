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
import javax.validation.constraints.Digits;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import org.apache.commons.lang3.builder.ReflectionToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.beans.factory.annotation.Value;
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
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "cliente")
    private Set<Pedido> pedidos = new HashSet<Pedido>();

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

	@PersistenceContext
    transient EntityManager entityManager;

	public static final List<String> fieldNames4OrderClauseFilter = java.util.Arrays.asList("nome", "cpf", "nascimento", "credito", "pedidos");

	public static final EntityManager entityManager() {
        EntityManager em = new Cliente().entityManager;
        if (em == null) throw new IllegalStateException("Entity manager has not been injected (is the Spring Aspects JAR configured as an AJC/AJDT aspects library?)");
        return em;
    }

	public static long countClientes() {
        return entityManager().createQuery("SELECT COUNT(o) FROM Cliente o", Long.class).getSingleResult();
    }

	public static List<Cliente> findAllClientes() {
        return entityManager().createQuery("SELECT o FROM Cliente o", Cliente.class).getResultList();
    }

	public static List<Cliente> findAllClientes(String sortFieldName, String sortOrder) {
        String jpaQuery = "SELECT o FROM Cliente o";
        if (fieldNames4OrderClauseFilter.contains(sortFieldName)) {
            jpaQuery = jpaQuery + " ORDER BY " + sortFieldName;
            if ("ASC".equalsIgnoreCase(sortOrder) || "DESC".equalsIgnoreCase(sortOrder)) {
                jpaQuery = jpaQuery + " " + sortOrder;
            }
        }
        return entityManager().createQuery(jpaQuery, Cliente.class).getResultList();
    }

	public static Cliente findCliente(Long id) {
        if (id == null) return null;
        return entityManager().find(Cliente.class, id);
    }

	public static List<Cliente> findClienteEntries(int firstResult, int maxResults) {
        return entityManager().createQuery("SELECT o FROM Cliente o", Cliente.class).setFirstResult(firstResult).setMaxResults(maxResults).getResultList();
    }

	public static List<Cliente> findClienteEntries(int firstResult, int maxResults, String sortFieldName, String sortOrder) {
        String jpaQuery = "SELECT o FROM Cliente o";
        if (fieldNames4OrderClauseFilter.contains(sortFieldName)) {
            jpaQuery = jpaQuery + " ORDER BY " + sortFieldName;
            if ("ASC".equalsIgnoreCase(sortOrder) || "DESC".equalsIgnoreCase(sortOrder)) {
                jpaQuery = jpaQuery + " " + sortOrder;
            }
        }
        return entityManager().createQuery(jpaQuery, Cliente.class).setFirstResult(firstResult).setMaxResults(maxResults).getResultList();
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
            Cliente attached = Cliente.findCliente(this.id);
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
    public Cliente merge() {
        if (this.entityManager == null) this.entityManager = entityManager();
        Cliente merged = this.entityManager.merge(this);
        this.entityManager.flush();
        return merged;
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

	public float getCredito() {
        return this.credito;
    }

	public void setCredito(float credito) {
        this.credito = credito;
    }

	public Set<Pedido> getPedidos() {
        return this.pedidos;
    }

	public void setPedidos(Set<Pedido> pedidos) {
        this.pedidos = pedidos;
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

	public static Cliente fromJsonToCliente(String json) {
        return new JSONDeserializer<Cliente>()
        .use(null, Cliente.class).deserialize(json);
    }

	public static String toJsonArray(Collection<Cliente> collection) {
        return new JSONSerializer()
        .exclude("*.class").deepSerialize(collection);
    }

	public static String toJsonArray(Collection<Cliente> collection, String[] fields) {
        return new JSONSerializer()
        .include(fields).exclude("*.class").deepSerialize(collection);
    }

	public static Collection<Cliente> fromJsonArrayToClientes(String json) {
        return new JSONDeserializer<List<Cliente>>()
        .use("values", Cliente.class).deserialize(json);
    }
}
