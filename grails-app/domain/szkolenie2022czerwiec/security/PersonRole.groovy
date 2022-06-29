package szkolenie2022czerwiec.security

import grails.gorm.DetachedCriteria
import groovy.transform.ToString

import org.codehaus.groovy.util.HashCodeHelper
import grails.compiler.GrailsCompileStatic

@GrailsCompileStatic
@ToString(cache=true, includeNames=true, includePackage=false)
class PersonRole implements Serializable {

	private static final long serialVersionUID = 1

	Person person
	Role role

	@Override
	boolean equals(other) {
		if (other instanceof PersonRole) {
			other.personId == person?.id && other.roleId == role?.id
		}
	}

    @Override
	int hashCode() {
	    int hashCode = HashCodeHelper.initHash()
        if (person) {
            hashCode = HashCodeHelper.updateHash(hashCode, person.id)
		}
		if (role) {
		    hashCode = HashCodeHelper.updateHash(hashCode, role.id)
		}
		hashCode
	}

	static PersonRole get(long personId, long roleId) {
		criteriaFor(personId, roleId).get()
	}

	static boolean exists(long personId, long roleId) {
		criteriaFor(personId, roleId).count()
	}

	private static DetachedCriteria criteriaFor(long personId, long roleId) {
		PersonRole.where {
			person == Person.load(personId) &&
			role == Role.load(roleId)
		}
	}

	static PersonRole create(Person person, Role role, boolean flush = false) {
		def instance = new PersonRole(person: person, role: role)
		instance.save(flush: flush)
		instance
	}

	static boolean remove(Person u, Role r) {
		if (u != null && r != null) {
			PersonRole.where { person == u && role == r }.deleteAll()
		}
	}

	static int removeAll(Person u) {
		u == null ? 0 : PersonRole.where { person == u }.deleteAll() as int
	}

	static int removeAll(Role r) {
		r == null ? 0 : PersonRole.where { role == r }.deleteAll() as int
	}

	static constraints = {
	    person nullable: false
		role nullable: false, validator: { Role r, PersonRole ur ->
			if (ur.person?.id) {
				if (PersonRole.exists(ur.person.id, r.id)) {
				    return ['userRole.exists']
				}
			}
		}
	}

	static mapping = {
		id composite: ['person', 'role']
		version false
	}
}
