package web.dao;

import org.springframework.stereotype.Repository;
import web.model.Car;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Repository
public class CarDaoImpl implements CarDao {
    private final List<Car> carList = new ArrayList<>();

    public CarDaoImpl() {
        carList.add(new Car("Mitsubishi", "Lancer", 1995));
        carList.add(new Car("Mazda", "626", 1991));
        carList.add(new Car("Toyota", "Corolla", 1992));
        carList.add(new Car("VW", "Golf", 1993));
        carList.add(new Car("Opel", "Tigra", 1998));
    }

    @Override
    public List<Car> getCarList(int count) {
        if (count >= carList.size() || count <= 0) {
            count = carList.size();
        }

        return carList.stream().limit(count).collect(Collectors.toList());
    }
}
