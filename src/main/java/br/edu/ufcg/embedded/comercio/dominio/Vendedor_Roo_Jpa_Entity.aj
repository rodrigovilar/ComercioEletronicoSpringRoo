// WARNING: DO NOT EDIT THIS FILE. THIS FILE IS MANAGED BY SPRING ROO.
// You may push code into the target .java compilation unit if you wish to edit any member(s).

package br.edu.ufcg.embedded.comercio.dominio;

import br.edu.ufcg.embedded.comercio.dominio.Vendedor;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Version;

privileged aspect Vendedor_Roo_Jpa_Entity {
    
    declare @type: Vendedor: @Entity;
    
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private Long Vendedor.id;
    
    @Version
    @Column(name = "version")
    private Integer Vendedor.version;
    
    public Long Vendedor.getId() {
        return this.id;
    }
    
    public void Vendedor.setId(Long id) {
        this.id = id;
    }
    
    public Integer Vendedor.getVersion() {
        return this.version;
    }
    
    public void Vendedor.setVersion(Integer version) {
        this.version = version;
    }
    
}
