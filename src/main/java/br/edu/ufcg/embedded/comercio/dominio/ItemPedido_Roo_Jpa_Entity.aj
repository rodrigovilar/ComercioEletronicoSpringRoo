// WARNING: DO NOT EDIT THIS FILE. THIS FILE IS MANAGED BY SPRING ROO.
// You may push code into the target .java compilation unit if you wish to edit any member(s).

package br.edu.ufcg.embedded.comercio.dominio;

import br.edu.ufcg.embedded.comercio.dominio.ItemPedido;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Version;

privileged aspect ItemPedido_Roo_Jpa_Entity {
    
    declare @type: ItemPedido: @Entity;
    
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private Long ItemPedido.id;
    
    @Version
    @Column(name = "version")
    private Integer ItemPedido.version;
    
    public Long ItemPedido.getId() {
        return this.id;
    }
    
    public void ItemPedido.setId(Long id) {
        this.id = id;
    }
    
    public Integer ItemPedido.getVersion() {
        return this.version;
    }
    
    public void ItemPedido.setVersion(Integer version) {
        this.version = version;
    }
    
}
