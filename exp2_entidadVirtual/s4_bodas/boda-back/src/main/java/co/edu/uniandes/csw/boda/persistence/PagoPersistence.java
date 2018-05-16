package co.edu.uniandes.csw.boda.persistence;

import co.edu.uniandes.csw.boda.entities.PagoEntity;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

@Stateless
public class PagoPersistence {

    private static final Logger LOGGER = Logger.getLogger(PagoPersistence.class.getName());

    @PersistenceContext(unitName = "bodaPU")
    protected EntityManager em;

    /**
     *
     * @param entity objeto Pago que se creará en la base de datos
     * @return devuelve la entidad creada con un id dado por la base de datos.
     */
    public PagoEntity create(PagoEntity entity) {
        LOGGER.info("Creando un Pago nuevo");
        em.persist(entity);
        LOGGER.info("Termina proceso de creacion de un Pago nuevo");
        return entity;
    }
    
    /**
     * Actualiza una TarjetaCredito.
     *
     * @param entity: el Pago que viene con los nuevos cambios. Por ejemplo
     * el codigo pudo cambiar. En ese caso, se haria uso del método update.
     * @return un Pago con los cambios aplicados.
     */
    public PagoEntity update(PagoEntity entity) {
        LOGGER.log(Level.INFO, "Actualizando Pago con id={0}", entity.getId());
        return em.merge(entity);
    }

    /**
     * Busca si hay algun Pago con el id que se envía de argumento
     *
     * @param id: id correspondiente al Pago buscado.
     * @return un Pago.
     */
    public PagoEntity find(Long id) {
        LOGGER.log(Level.INFO, "Consultando Pago con id={0}", id);
        return em.find(PagoEntity.class, id);
    }
    
    /**
     * Busca si hay algun pago con el nombre que se envía de argumento
     *
     * @param name: nombre del pago que se está buscando
     * @return null si no existe ningun pago con el nombre del argumento.
     * Si existe alguno devuelve el primero.
     */
    public PagoEntity findByNombre(String name) {
         LOGGER.log(Level.INFO, "Consultando pagos con name= ", name);
        TypedQuery<PagoEntity> q
                = em.createQuery("select u from PagoEntity u where u.name = :name", PagoEntity.class);
        q = q.setParameter("name", name);
        return q.getSingleResult();
    }
    
    /**
     * Devuelve todos los Pagos de la base de datos.
     *
     * @return una lista con todos los Pagos que encuentre en la base de
     * datos, "select u from PagoEntity u" es como un "select * from
     * DefaultEntity;" - "SELECT * FROM table_codigo" en SQL.
     */
    public List<PagoEntity> findAll() {
        LOGGER.info("Consultando todos los Pagos");
        // Se crea un query para buscar todas las Pago en la base de datos.
        TypedQuery query = em.createQuery("select u from PagoEntity u", PagoEntity.class);
        // Note que en el query se hace uso del método getResultList() que obtiene una lista de Pago.
        return query.getResultList();
    }
}
