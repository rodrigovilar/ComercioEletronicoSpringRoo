// WARNING: DO NOT EDIT THIS FILE. THIS FILE IS MANAGED BY SPRING ROO.
// You may push code into the target .java compilation unit if you wish to edit any member(s).

package br.edu.ufcg.embedded.comercio.dominio;

import br.edu.ufcg.embedded.comercio.dominio.Fornecedor;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Version;

privileged aspect Fornecedor_Roo_Jpa_Entity {
    
    declare @type: Fornecedor: @Entity;
    
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private Long Fornecedor.id;
    
    @Version
    @Column(name = "version")
    private Integer Fornecedor.version;
    
    public Long Fornecedor.getId() {
        return this.id;
    }
    
    public void Fornecedor.setId(Long id) {
        this.id = id;
    }
    
    public Integer Fornecedor.getVersion() {
        return this.version;
    }
    
    public void Fornecedor.setVersion(Integer version) {
        this.version = version;
    }
    
}