package szkolenie2022czerwiec

import grails.validation.ValidationException
import static org.springframework.http.HttpStatus.*

class ProducerController {

    ProducerService producerService

    static allowedMethods = [save: "POST", update: "PUT", delete: "DELETE"]

    def index(Integer max) {
        params.max = Math.min(max ?: 10, 100)
        respond producerService.list(params), model:[producerCount: producerService.count()]
    }

    def show(Long id) {
        respond producerService.get(id)
    }

    def create() {
        respond new Producer(params)
    }

    def save(Producer producer) {
        if (producer == null) {
            notFound()
            return
        }

        try {
            producerService.save(producer)
        } catch (ValidationException e) {
            respond producer.errors, view:'create'
            return
        }

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.created.message', args: [message(code: 'producer.label', default: 'Producer'), producer.id])
                redirect producer
            }
            '*' { respond producer, [status: CREATED] }
        }
    }

    def edit(Long id) {
        respond producerService.get(id)
    }

    def update(Producer producer) {
        if (producer == null) {
            notFound()
            return
        }

        try {
            producerService.save(producer)
        } catch (ValidationException e) {
            respond producer.errors, view:'edit'
            return
        }

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.updated.message', args: [message(code: 'producer.label', default: 'Producer'), producer.id])
                redirect producer
            }
            '*'{ respond producer, [status: OK] }
        }
    }

    def delete(Long id) {
        if (id == null) {
            notFound()
            return
        }

        producerService.delete(id)

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.deleted.message', args: [message(code: 'producer.label', default: 'Producer'), id])
                redirect action:"index", method:"GET"
            }
            '*'{ render status: NO_CONTENT }
        }
    }

    protected void notFound() {
        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.not.found.message', args: [message(code: 'producer.label', default: 'Producer'), params.id])
                redirect action: "index", method: "GET"
            }
            '*'{ render status: NOT_FOUND }
        }
    }
}
