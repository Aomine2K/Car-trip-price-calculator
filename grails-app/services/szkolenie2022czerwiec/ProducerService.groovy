package szkolenie2022czerwiec

import grails.gorm.services.Service

@Service(Producer)
interface ProducerService {

    Producer get(Serializable id)

    List<Producer> list(Map args)

    Long count()

    void delete(Serializable id)

    Producer save(Producer producer)

}