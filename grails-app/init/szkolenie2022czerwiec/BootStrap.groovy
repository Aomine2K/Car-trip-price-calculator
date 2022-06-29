package szkolenie2022czerwiec

import grails.gorm.transactions.Transactional
import szkolenie2022czerwiec.security.Person
import szkolenie2022czerwiec.security.PersonRole
import szkolenie2022czerwiec.security.Role

class BootStrap {

    UserInitializationService initializationService

    def init = { servletContext ->
        //odpalaj przy uruchomieniu aplikacji
        println "dodawanie nowego użytkownika"
        createNewUser()
    }
    def destroy = {
        //odpalaj przy wyłączaniu aplikacji
    }

    @Transactional
    void createNewUser() {
//        Person kacper = Person.findOrSaveWhere(username: 'kacper',password: 'kacper')
//        Role admin = Role.findOrSaveWhere(authority: 'ROLE_ADMIN')
//        PersonRole kacperAdmin = PersonRole.findOrSaveWhere(person: kacper, role: admin)
    }
}
