package szkolenie2022czerwiec

import grails.converters.JSON
import grails.converters.XML

class FuelUsageController {

    FuelCostService fuelCostService

    def index() {
        render "Litwo ojczyzno moja itp"
    }

    def updateFuelPrices() {
        render fuelCostService.updateAllFuelTypePrices()
    }

    def newCarForm() {
        render view: 'carForm', model: [listOfProducers: Producer.list()]
    }

    def saveNewCar() {
        fuelCostService.saveNewCar(params.model, params.producer, params.fuelTypeMyszjolen, 6.2, 1.6)
        redirect action: 'showAllCars'
    }

    def showAllCars() {
        render view: 'showAllCars', model: [carList: Car.list()]
    }

}
