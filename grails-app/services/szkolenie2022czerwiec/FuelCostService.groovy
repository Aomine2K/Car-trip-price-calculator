package szkolenie2022czerwiec

import grails.gorm.transactions.Transactional
import org.jsoup.Jsoup
import org.jsoup.nodes.Document
import org.jsoup.select.Elements

@Transactional
class FuelCostService {

    static final String FUEL_SERVICE_ADDRESS = "https://www.autocentrum.pl/paliwa/ceny-paliw/";


    def calculateCostByFuelUsage(Double fuelUsage) {
        Document doc = Jsoup.connect(FUEL_SERVICE_ADDRESS).get()
        String priceText = doc.select('div.price').first().text()
        Double price = Double.parseDouble(priceText.split(" ")[0].replace(",", "."))
        return price * fuelUsage

    }

    Double getFuelPrice(FuelType fuelType){
        Document doc = Jsoup.connect(FUEL_SERVICE_ADDRESS).get()
        Elements prices = doc.select('div.price')

        String priceString

        switch (fuelType.name){
            case 'Diesel': priceString = prices.get(2).text(); break;
            case 'PB': priceString = prices.get(0).text(); break;
            case 'LPG': priceString = prices.get(4).text(); break;
            default: priceString = "0,00 zł"
        }

        Double price = Double.parseDouble(priceString.split(" ")[0].replace(",", "."))
        return price

    }

    def updateAllFuelTypePrices(){
        FuelType.list().each{
            it.price = this.getFuelPrice(it)
            it.dateOfLastUpdate = new Date()
            it.save()
        }
    }


    def calculateCostByModel(String model) {
        if (model == "ford") {
            return calculateCostByFuelUsage(7.6)
        } else if (model == "vw") {
            return calculateCostByFuelUsage(6.9)
        } else {
            return 0
        }
    }

    Car saveNewCar(String modelName, String producerName,
                   String fuelTypeName, Double fuelConsumption, Double engineVolume) {

        Model model = Model.findOrSaveByName(modelName)
        Producer producer = Producer.findOrSaveByName(producerName)
        FuelType fuelType = FuelType.findOrSaveByName(fuelTypeName)

        return Car.findOrSaveWhere(model: model,
                producer: producer,
                fuelType: fuelType,
                fuelConsumption: fuelConsumption,
                engineVolume: engineVolume)
    }
}
