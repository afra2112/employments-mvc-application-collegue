package com.app.empleos.service;

import com.app.empleos.config.enums.ModalidadEnum;
import com.app.empleos.entity.Vacante;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class ConsultasMulticriterioVacantes {

    @PersistenceContext
    EntityManager entityManager;

    public List<Vacante> filtrarVacantes(
            String nombreEmpresa,
            String tituloVacante,
            String modalidad,
            String ordernarPor
    ){
        System.out.println("RESULTADO: " +tituloVacante);

        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        CriteriaQuery<Vacante> query = criteriaBuilder.createQuery(Vacante.class);
        Root<Vacante> root = query.from(Vacante.class);

        List<Predicate> predicates = new ArrayList<>();

        if(!nombreEmpresa.isBlank()){
            predicates.add(criteriaBuilder.like(criteriaBuilder.lower(root.get("empresa").get("nombre")), "%" + nombreEmpresa.toLowerCase() + "%"));
        }

        if(!tituloVacante.isBlank()){
            predicates.add(criteriaBuilder.like(criteriaBuilder.lower(root.get("titulo")), "%" + tituloVacante.toLowerCase() + "%"));
        }

        if(!modalidad.isBlank()){
            predicates.add(criteriaBuilder.like(root.get("modalidad"), modalidad));
        }

        query.where(criteriaBuilder.and(predicates.toArray(new Predicate[0])));

        if(!ordernarPor.isBlank()){
            switch (ordernarPor){
                case "salario":
                    query.orderBy(criteriaBuilder.desc(root.get("salario")));
                    break;
                case "fechaPublicacion":
                    query.orderBy(criteriaBuilder.desc(root.get("fechaCreacion")));
                    break;
                case "masCerca":
                    break;
                case "menosPostulaciones":
                    query.orderBy(criteriaBuilder.desc(criteriaBuilder.size(root.get("postulaciones"))));
                    break;
            }
        }

        return entityManager.createQuery(query).getResultList();
    }
}
