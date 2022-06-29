import szkolenie2022czerwiec.FuelCostService

class FuelPriceUpdateJob {

    FuelCostService fuelCostService

    static triggers = {
        simple repeatInterval: 100000 //co 1000ms = 1s
    }

    void execute() {
        println "my email: kacper.warda@logintegra.com"
        println "start of prices update process"
        fuelCostService.updateAllFuelTypePrices()
        println "end of prices update process"

    }
}
