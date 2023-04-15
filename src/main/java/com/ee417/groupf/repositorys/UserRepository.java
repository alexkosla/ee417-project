package com.ee417.groupf.repositorys;

import java.util.List;
import java.util.Set;

import org.hibernate.boot.registry.StandardServiceRegistryBuilder;

import com.ee417.groupf.model.UserModel;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.metamodel.EntityType;
import org.hibernate.cfg.Configuration;
import org.springframework.stereotype.Repository;

@Repository
public class UserRepository {

   private static EntityManagerFactory entityManagerFactory = getEntityManageFactory();

   private static EntityManagerFactory getEntityManageFactory() {
       if (entityManagerFactory == null) {
           Configuration configuration = new Configuration().configure("hibernate.cfg.xml")
                   .addAnnotatedClass(UserModel.class);
           StandardServiceRegistryBuilder builder = new StandardServiceRegistryBuilder()
                   .applySettings(configuration.getProperties());
           entityManagerFactory = configuration.buildSessionFactory(builder.build());
       }

       return entityManagerFactory;
   }

   public List<UserModel> getAllUsers() {

       EntityManager entityManager = entityManagerFactory.createEntityManager();
       EntityTransaction transaction = entityManager.getTransaction();
       transaction.begin();
       List<UserModel> users = entityManager.createQuery("select u from UserModel u", UserModel.class).getResultList();
       System.out.println(users);
       Set<EntityType<?>> entities = entityManager.getMetamodel().getEntities();
       entities.forEach(e -> System.out.println(e.getName()));
       transaction.commit();
       entityManager.close();
       return users;
   }

    public UserModel postUsers(UserModel user) {
       EntityManager entityManager = entityManagerFactory.createEntityManager();
       EntityTransaction transaction = entityManager.getTransaction();
       transaction.begin();
       entityManager.persist(user);

       transaction.commit();
       entityManager.close();
        return user;
    }

}
