package com.example.creea.persistence.animal.repo;

import com.example.creea.persistence.animal.entity.Animal;
import com.example.creea.persistence.animal.entity.Breed;
import com.example.creea.persistence.animal.entity.Type;
import com.example.creea.persistence.animal.enums.*;
import com.example.creea.rest.model.request.AnimalTypeRequest;
import com.example.creea.service.criteria.AnimalFilterModel;
import com.example.creea.service.criteria.AnimalPage;
import org.springframework.data.domain.*;
import org.springframework.stereotype.Repository;
import org.springframework.util.StringUtils;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.*;
import java.util.ArrayList;
import java.util.List;

@Repository
public class AnimalCriteriaRepository {
    private final EntityManager entityManager;
    private final CriteriaBuilder criteriaBuilder;


    public AnimalCriteriaRepository(EntityManager entityManager) {
        this.entityManager = entityManager;
        this.criteriaBuilder = entityManager.getCriteriaBuilder();
    }

    public Page<Animal> findAllWithFilters(AnimalPage animalPage,
                                           AnimalFilterModel animalFilterModel) {
        CriteriaQuery<Animal> criteriaQuery = criteriaBuilder.createQuery(Animal.class);
        Root<Animal> animalRoot = criteriaQuery.from(Animal.class);

        Predicate predicate = getPredicate(animalFilterModel, animalRoot);

        criteriaQuery.where(predicate);
        setOrder(animalPage, criteriaQuery, animalRoot);

        TypedQuery<Animal> typedQuery = entityManager.createQuery(criteriaQuery);
        typedQuery.setFirstResult(animalPage.getPageSize() * animalPage.getPageNumber());
        typedQuery.setMaxResults(animalPage.getPageSize());

        Pageable pageable = getPageable(animalPage);

        long animalCount = getAnimalCount(predicate, animalFilterModel);

        return new PageImpl<>(typedQuery.getResultList(), pageable, animalCount);
    }

    private long getAnimalCount(Predicate predicate, AnimalFilterModel animalFilterModel) {
        CriteriaQuery<Long> countQuery = criteriaBuilder.createQuery(Long.class);
        Root<Animal> countRoot = countQuery.from(Animal.class);
        if (StringUtils.hasText(animalFilterModel.getBreed())) {
            countRoot.join("breed");
        }
      /*  if (StringUtils.hasText(animalFilterModel.getType())) {
            countRoot.join("breed").join("type");
        }*/

        countQuery.select(criteriaBuilder.count(countRoot)).where(predicate);
        return entityManager.createQuery(countQuery).getSingleResult();
    }

    private Pageable getPageable(AnimalPage animalPage) {
        Sort sort = Sort.by(animalPage.getSortDirection(), animalPage.getSortField());
        return PageRequest.of(animalPage.getPageNumber(), animalPage.getPageSize(), sort);
    }

    private void setOrder(AnimalPage animalPage, CriteriaQuery<Animal> criteriaQuery, Root<Animal> animalRoot) {
        if (animalPage.getSortDirection().equalsIgnoreCase(Sort.Direction.ASC.name())) {
            criteriaQuery.orderBy(criteriaBuilder.asc(animalRoot.get(animalPage.getSortField())));
        } else {
            criteriaQuery.orderBy(criteriaBuilder.desc(animalRoot.get(animalPage.getSortField())));
        }
    }

    private Predicate getPredicate(AnimalFilterModel animalFilterModel, Root<Animal> animalRoot) {
        List<Predicate> predicates = new ArrayList<>();

        if (StringUtils.hasText(animalFilterModel.getAge())) {
            predicates.add(
                    criteriaBuilder.equal(animalRoot.get("age"),
                            AnimalAge.valueOf(animalFilterModel.getAge())));
        }

        if (StringUtils.hasText(animalFilterModel.getColor())) {
            predicates.add(
                    criteriaBuilder.equal(animalRoot.get("color"),
                            AnimalColor.valueOf(animalFilterModel.getColor())));
        }

        if (StringUtils.hasText(animalFilterModel.getGender())) {
            predicates.add(
                    criteriaBuilder.equal(animalRoot.get("gender"),
                            AnimalGender.valueOf(animalFilterModel.getGender())));
        }


        if (StringUtils.hasText(animalFilterModel.getBreed())) {
            Join<Animal, Breed> breedJoin = animalRoot.join("breed", JoinType.LEFT);
            predicates.add(
                    criteriaBuilder.equal(breedJoin.get("name"), BreedName.valueOf(animalFilterModel.getBreed())));
        }

        /*if (StringUtils.hasText(animalFilterModel.getType())) {
            Join<Animal, Breed> breedJoin = animalRoot.join("breed", JoinType.LEFT);
            Join<Breed, Type> typeJoin = breedJoin.join("type", JoinType.LEFT);

            Expression<String> type = typeJoin.get("type");
            predicates.add(
                    criteriaBuilder.equal(type, TypeName.valueOf(animalFilterModel.getType())));
        }*/
        return criteriaBuilder.and(predicates.toArray(new Predicate[0]));
    }

    public Page<Animal> findAllWithType(AnimalPage animalPage, AnimalTypeRequest animalTypeRequest) {
        CriteriaQuery<Animal> criteriaQuery = criteriaBuilder.createQuery(Animal.class);
        Root<Animal> animalRoot = criteriaQuery.from(Animal.class);

        Predicate predicate = getPredicate(animalTypeRequest, animalRoot);

        criteriaQuery.where(predicate);
        setOrder(animalPage, criteriaQuery, animalRoot);

        TypedQuery<Animal> typedQuery = entityManager.createQuery(criteriaQuery);
        typedQuery.setFirstResult(animalPage.getPageSize() * animalPage.getPageNumber());
        typedQuery.setMaxResults(animalPage.getPageSize());

        Pageable pageable = getPageable(animalPage);

        long animalCount = getAnimalCount(predicate, animalTypeRequest);

        return new PageImpl<>(typedQuery.getResultList(), pageable, animalCount);
    }

    private long getAnimalCount(Predicate predicate, AnimalTypeRequest animalTypeRequest) {
        CriteriaQuery<Long> countQuery = criteriaBuilder.createQuery(Long.class);
        Root<Animal> countRoot = countQuery.from(Animal.class);

        if (StringUtils.hasText(animalTypeRequest.getType())) {
            countRoot.join("breed").join("type");
        }

        countQuery.select(criteriaBuilder.count(countRoot)).where(predicate);
        return entityManager.createQuery(countQuery).getSingleResult();
    }

    private Predicate getPredicate(AnimalTypeRequest animalTypeRequest, Root<Animal> animalRoot) {
        List<Predicate> predicates = new ArrayList<>();
        if (StringUtils.hasText(animalTypeRequest.getType())) {
            Join<Animal, Breed> breedJoin = animalRoot.join("breed", JoinType.LEFT);
            Join<Breed, Type> typeJoin = breedJoin.join("type", JoinType.LEFT);

            Expression<String> type = typeJoin.get("type");
            predicates.add(
                    criteriaBuilder.equal(type, TypeName.valueOf(animalTypeRequest.getType())));
        }
        return criteriaBuilder.and(predicates.toArray(new Predicate[0]));
    }
}
