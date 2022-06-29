package szkolenie2022czerwiec

import grails.gorm.services.Service

@Service(FuelType)
interface FuelTypeService {

    FuelType get(Serializable id)

    List<FuelType> list(Map args)

    Long count()

    void delete(Serializable id)

    FuelType save(FuelType fuelType)

}