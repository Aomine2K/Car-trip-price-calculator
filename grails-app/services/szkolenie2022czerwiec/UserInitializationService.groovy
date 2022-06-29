package szkolenie2022czerwiec

import grails.gorm.transactions.Transactional
import szkolenie2022czerwiec.security.Person
import szkolenie2022czerwiec.security.PersonRole
import szkolenie2022czerwiec.security.Role

@Transactional
class UserInitializationService {

    def createNewUser() {
        Person kacper = Person.findOrSaveWhere(username: 'kacper',password: 'kacper')
        Role admin = Role.findOrSaveWhere(authority: 'ROLE_ADMIN')
        PersonRole kacperAdmin = PersonRole.findOrSaveWhere(person: kacper, role: admin)
    }
}
