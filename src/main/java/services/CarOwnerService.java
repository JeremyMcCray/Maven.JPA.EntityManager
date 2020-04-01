package services;

import entities.CarModel;
import entities.CarOwnerModel;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.util.LinkedList;
import java.util.List;

public class CarOwnerService {


    EntityManagerFactory emf;
    EntityManager em;
    List<CarOwnerModel> carOwnerModelList;

    CarOwnerService(){
        emf = Persistence.createEntityManagerFactory("Database");
        em = emf.createEntityManager();
        carOwnerModelList = new LinkedList<CarOwnerModel>();
    }

    public List<CarOwnerModel> findAll(){return em.createQuery("SELECT o FROM Car_Owners o", CarOwnerModel.class).getResultList();}


    public CarOwnerModel findById(Long id){return em.find(CarOwnerModel.class,id);}

    public CarOwnerModel create(CarOwnerModel carOwnerModel){
        em.getTransaction().begin();
        em.persist(carOwnerModel);
        em.getTransaction().commit();
        return carOwnerModel;
    }

    public CarOwnerModel create(String fn, String ln, String email, String gender){
        CarOwnerModel carOwnerModel = new CarOwnerModel();

        em.getTransaction().begin();
        carOwnerModel.setEmail(email);
        carOwnerModel.setGender(gender);
        carOwnerModel.setFirst_name(fn);
        carOwnerModel.setLast_name(ln);
        em.persist(carOwnerModel);
        em.getTransaction().commit();

        return carOwnerModel;

    }

    public void update(Long id,  CarOwnerModel carOwnerModel){
        em.getTransaction().begin();

        CarOwnerModel persistCarOwner = em.find(CarOwnerModel.class,id);
        persistCarOwner.setLast_name(carOwnerModel.getLast_name());
        persistCarOwner.setFirst_name(carOwnerModel.getFirst_name());
        persistCarOwner.setEmail(carOwnerModel.getEmail());
        persistCarOwner.setGender(carOwnerModel.getGender());
        em.merge(persistCarOwner);
        em.getTransaction().commit();

    }

    public void delete(Long id){
        CarOwnerModel carOwnerModel = em.find(CarOwnerModel.class,id);
            if(carOwnerModel != null){
                em.getTransaction().begin();
                em.remove(carOwnerModel);
                em.getTransaction().commit();
            }

    }

}
