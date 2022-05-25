/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.ifsul.dao;

import br.edu.ifsul.modelo.Compra;
import java.io.Serializable;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;


@Stateless
public class DAOCompra implements Serializable {
    
    @PersistenceContext(unitName="SW-CORREIO-PU")
    private EntityManager em;

    private List<Compra> compra;

    public DAOCompra() {
    }

    public List<Compra> getOrders() {
        return em.createQuery("from Compra").getResultList();
    }
    
    public void setOrders(List<Compra> orders) {
        this.compra = orders;
    }
    
    public Compra persist(Compra o) throws Exception {
        o.setId(null);
        em.persist(o);
        return o;
    }
    
    public Compra merge(Compra o) throws Exception {
        em.merge(o);
        return o;
    }
    
    public void remove(Object id) throws Exception {
        Compra o = em.find(Compra.class, id);
        em.remove(o);
    }
    
    public Compra find(Object id) throws Exception {
        return em.find(Compra.class, id);
    }
}
