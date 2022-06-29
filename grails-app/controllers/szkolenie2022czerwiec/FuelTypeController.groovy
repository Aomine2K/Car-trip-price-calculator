package szkolenie2022czerwiec

import grails.validation.ValidationException
import static org.springframework.http.HttpStatus.*

class FuelTypeController {

    FuelTypeService fuelTypeService

    static allowedMethods = [save: "POST", update: "PUT", delete: "DELETE"]

    def index(Integer max) {
        params.max = Math.min(max ?: 10, 100)
        respond fuelTypeService.list(params), model:[fuelTypeCount: fuelTypeService.count()]
    }

    def show(Long id) {
        respond fuelTypeService.get(id)
    }

    def create() {
        respond new FuelType(params)
    }

    def save(FuelType fuelType) {
        if (fuelType == null) {
            notFound()
            return
        }

        try {
            fuelTypeService.save(fuelType)
        } catch (ValidationException e) {
            respond fuelType.errors, view:'create'
            return
        }

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.created.message', args: [message(code: 'fuelType.label', default: 'FuelType'), fuelType.id])
                redirect fuelType
            }
            '*' { respond fuelType, [status: CREATED] }
        }
    }

    def edit(Long id) {
        respond fuelTypeService.get(id)
    }

    def update(FuelType fuelType) {
        if (fuelType == null) {
            notFound()
            return
        }

        try {
            fuelTypeService.save(fuelType)
        } catch (ValidationException e) {
            respond fuelType.errors, view:'edit'
            return
        }

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.updated.message', args: [message(code: 'fuelType.label', default: 'FuelType'), fuelType.id])
                redirect fuelType
            }
            '*'{ respond fuelType, [status: OK] }
        }
    }

    def delete(Long id) {
        if (id == null) {
            notFound()
            return
        }

        fuelTypeService.delete(id)

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.deleted.message', args: [message(code: 'fuelType.label', default: 'FuelType'), id])
                redirect action:"index", method:"GET"
            }
            '*'{ render status: NO_CONTENT }
        }
    }

    protected void notFound() {
        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.not.found.message', args: [message(code: 'fuelType.label', default: 'FuelType'), params.id])
                redirect action: "index", method: "GET"
            }
            '*'{ render status: NOT_FOUND }
        }
    }
}
