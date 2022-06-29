package szkolenie2022czerwiec

class FuelType {
    String name
    Double price
    Date dateOfLastUpdate

    static constraints = {
        price nullable: true
        dateOfLastUpdate nullable: true
    }

    public String toString(){
        return name
    }
}
