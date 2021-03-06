package services;

import entities.CarModel;


import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.util.ArrayList;
import java.util.List;

public class CarService {

    EntityManagerFactory emf;
    EntityManager em;
    List<CarModel> carModelList;


    CarService(){
        emf = Persistence.createEntityManagerFactory("Database");
        em =emf.createEntityManager();
        carModelList = new ArrayList<CarModel>();
    }

    public List<CarModel> findAll(){
        return em.createQuery("SELECT c FROM cars c", CarModel.class).getResultList();
    }


    public CarModel findById(Long id){
        return em.find(CarModel.class,id);
    }

    public CarModel create(CarModel carModel){
        em.getTransaction().begin();
        em.persist(carModel);
        em.getTransaction().commit();

        return carModel;

    }

    public CarModel create(String make, String model, Long year, String color, String vin){
        CarModel carModel = new CarModel();

        carModel.setMake(make);
        carModel.setColor(color);
        carModel.setModel(model);
        carModel.setYear(year);
        carModel.setVin(vin);
        em.getTransaction().begin();
        em.persist(carModel);
        em.getTransaction().commit();

        return carModel;

    }


    public void update(Long id, CarModel carModel){
        em.getTransaction().begin();

        CarModel persistCar = em.find(CarModel.class,id);
        persistCar.setMake(carModel.getMake());
        persistCar.setColor(carModel.getColor());
        persistCar.setModel(carModel.getModel());
        persistCar.setYear(carModel.getYear());
        persistCar.setVin(carModel.getVin());
        em.merge(persistCar);
        em.getTransaction().commit();

    }


    public void delete(Long id){
        CarModel carModel = em.find(CarModel.class,id);
        if(carModel  != null){
            em.getTransaction().begin();
            em.remove(carModel);
            em.getTransaction().commit();
        }
    }


}
