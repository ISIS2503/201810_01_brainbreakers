package co.edu.uniandes.csw.boda.persistence;

import co.edu.uniandes.csw.boda.entities.TarjetaCreditoEntity;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;


@Stateless
public class TarjetaCreditoPersistence {

    private static final Logger LOGGER = Logger.getLogger(TarjetaCreditoPersistence.class.getName());

    @PersistenceContext(unitName = "bodaPU")
    protected EntityManager em;

    /**
     *
     * @param entity objeto TarjetaCredito que se creará en la base de datos
     * @return devuelve la entidad creada con un id dado por la base de datos.
     */
    public TarjetaCreditoEntity create(TarjetaCreditoEntity entity) {
        LOGGER.info("Creando una TarjetaCredito nuevo");
        em.persist(entity);
        LOGGER.info("Termina creacion de una TarjetaCredito nuevo");
        return entity;
    }

    /**
     * Actualiza una TarjetaCredito.
     *
     * @param entity: el TarjetaCredito que viene con los nuevos cambios. Por ejemplo
     * el codigo pudo cambiar. En ese caso, se haria uso del método update.
     * @return un TarjetaCredito con los cambios aplicados.
     */
    public TarjetaCreditoEntity update(TarjetaCreditoEntity entity) {
        LOGGER.log(Level.INFO, "Actualizando TarjetaCredito con id={0}", entity.getNumero());
        return em.merge(entity);
    }

    /**
     *
     * Borra un TarjetaCredito de la base de datos recibiendo como argumento el id
     * de la TarjetaCredito
     *
     * @param id: id correspondiente a la TarjetaCredito a borrar.
     */
    public void delete(Long id) {
        LOGGER.log(Level.INFO, "Borrando TarjetaCredito con id={0}", id);
        TarjetaCreditoEntity entity = em.find(TarjetaCreditoEntity.class, id);
        em.remove(entity);
    }

    /**
     * Busca si hay algun TarjetaCredito con el id que se envía de argumento
     *
     * @param id: id correspondiente a la TarjetaCredito buscada.
     * @return un TarjetaCredito.
     */
    public TarjetaCreditoEntity find(Long id) {
       return em.find(TarjetaCreditoEntity.class, id) ;
    }
    
    /**
     * Busca si hay alguna tarjetaCredito con el numDeSeg (numero de seguridad) que se envía de argumento
     *
     * @param numDeSeg: Numero de Seguridad de la TarjetaCredito que se está buscando
     * @return null si no existe ninguna TarjetaCredito con el numero del argumento.
     * Si existe alguna devuelve la primera.
     */
    public TarjetaCreditoEntity findByNumDeSeg(int numDeSeg) {
        LOGGER.log(Level.INFO, "Consultando tarjetasCredito con numDeSeg= ", numDeSeg);
        TypedQuery<TarjetaCreditoEntity> q = em.createQuery("select u from TarjetaCreditoEntity u where u.numDeSeg = :numDeSeg", TarjetaCreditoEntity.class);
        q = q.setParameter("numDeSeg", numDeSeg);
        List<TarjetaCreditoEntity> results = q.getResultList();
        TarjetaCreditoEntity tarjeta;
        if (results.isEmpty()) {
            tarjeta = null;
        } else {
            tarjeta = results.get(0);
        }
        return tarjeta;
    }
    
    /**
     * Busca si hay alguna tarjetaCredito con el numero que se envía de argumento
     *
     * @param numero: Numero la TarjetaCredito que se está buscando
     * @return null si no existe ninguna TarjetaCredito con el numero del argumento.
     * Si existe alguna devuelve la primera.
     */
    public TarjetaCreditoEntity findByNumero(Long numero) {
        LOGGER.log(Level.INFO, "Consultando tarjetasCredito con numero= ", numero);
        TypedQuery<TarjetaCreditoEntity> q = em.createQuery("select u from TarjetaCreditoEntity u where u.numero = :numero", TarjetaCreditoEntity.class);
        q = q.setParameter("numero", numero);
        List<TarjetaCreditoEntity> results = q.getResultList();
        TarjetaCreditoEntity tarjeta;
        if (results.isEmpty()) {
            tarjeta = null;
        } else {
            tarjeta = results.get(0);
        }
        return tarjeta;
    }
    /**
     * Devuelve todas las TarjetaCredito de la base de datos.
     *
     * @return una lista con todas las TarjetaCredito que encuentre en la base de
     * datos, "select u from TarjetaCreditoEntity u" es como un "select * from
     * DefaultEntity;" - "SELECT * FROM table_codigo" en SQL.
     */
    public List<TarjetaCreditoEntity> findAll() {
        LOGGER.info("Consultando todas las TarjetaCredito");
        // Se crea un query para buscar todas las TarjetaCredito en la base de datos.
        TypedQuery query = em.createQuery("select u from TarjetaCreditoEntity u", TarjetaCreditoEntity.class);
        return query.getResultList();
    }
}
