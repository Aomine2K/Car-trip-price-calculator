package szkolenie2022czerwiec

import grails.gorm.services.Service

@Service(Model)
interface ModelService {

    Model get(Serializable id)

    List<Model> list(Map args)

    Long count()

    void delete(Serializable id)

    Model save(Model model)

}