package szkolenie2022czerwiec

import grails.validation.ValidationException
import static org.springframework.http.HttpStatus.*

class ModelController {

    ModelService modelService

    static allowedMethods = [save: "POST", update: "PUT", delete: "DELETE"]

    def index(Integer max) {
        params.max = Math.min(max ?: 10, 100)
        respond modelService.list(params), model:[modelCount: modelService.count()]
    }

    def show(Long id) {
        respond modelService.get(id)
    }

    def create() {
        respond new Model(params)
    }

    def save(Model model) {
        if (model == null) {
            notFound()
            return
        }

        try {
            modelService.save(model)
        } catch (ValidationException e) {
            respond model.errors, view:'create'
            return
        }

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.created.message', args: [message(code: 'model.label', default: 'Model'), model.id])
                redirect model
            }
            '*' { respond model, [status: CREATED] }
        }
    }

    def edit(Long id) {
        respond modelService.get(id)
    }

    def update(Model model) {
        if (model == null) {
            notFound()
            return
        }

        try {
            modelService.save(model)
        } catch (ValidationException e) {
            respond model.errors, view:'edit'
            return
        }

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.updated.message', args: [message(code: 'model.label', default: 'Model'), model.id])
                redirect model
            }
            '*'{ respond model, [status: OK] }
        }
    }

    def delete(Long id) {
        if (id == null) {
            notFound()
            return
        }

        modelService.delete(id)

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.deleted.message', args: [message(code: 'model.label', default: 'Model'), id])
                redirect action:"index", method:"GET"
            }
            '*'{ render status: NO_CONTENT }
        }
    }

    protected void notFound() {
        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.not.found.message', args: [message(code: 'model.label', default: 'Model'), params.id])
                redirect action: "index", method: "GET"
            }
            '*'{ render status: NOT_FOUND }
        }
    }
}
