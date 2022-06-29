package szkolenie2022czerwiec

import grails.gorm.services.Service

@Service(Car)
interface CarService {

    Car get(Serializable id)

    List<Car> list(Map args)

    Long count()

    void delete(Serializable id)

    Car save(Car car)

}