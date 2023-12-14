package com.bridgelabz.employeepayrollservice.models;
/*
 * @desc :  Represents the address details of an employee, including street, village, city, state, zip code, and country.
 */
public class Address {
    private String employeeId; // @desc Unique identifier for the employee.
    private String street; // @desc The street name in the address.
    private String village; // @desc The village or locality in the address.
    private String city; // @desc The city in the address.
    private String state; // @desc The state in the address.
    private String zip; // @desc The zip code or postal code in the address.
    private String country; // @desc The country in the address.

    /*
     * Default constructor.
     */
    public Address() {
    }

    /*
     * @desc : Parameterized constructor to initialize Address with all details.
     * @param employeeId Unique identifier for the employee.
     * @param street The street name in the address.
     * @param village The village or locality in the address.
     * @param city The city in the address.
     * @param state The state in the address.
     * @param zip The zip code or postal code in the address.
     * @param country The country in the address.
     * @return : no return
     */
    public Address(String employeeId, String street, String village, String city, String state, String zip, String country) {
        this.employeeId = employeeId;
        this.street = street;
        this.village = village;
        this.city = city;
        this.state = state;
        this.zip = zip;
        this.country = country;
    }

    // Getter and Setter methods for each attribute with corresponding comments.

    /*
     * @desc : Getter method for retrieving the employeeId.
     * @params :  no params
     * @return Unique identifier for the employee.
     */
    public String getEmployeeId() {
        return employeeId;
    }

    /*
     * @desc : Setter method for updating the employeeId.
     * @param employeeId New unique identifier for the employee.
     * @return : no return
     */
    public void setEmployeeId(String employeeId) {
        this.employeeId = employeeId;
    }

    /*
     * @desc : Getter method for retrieving the street.
     * @params :  no params
     * @return The street name in the address.
     */
    public String getStreet() {
        return street;
    }

    /*
     * @desc : Setter method for updating the street.
     * @param street New street name in the address.
     * @return : no return
     */
    public void setStreet(String street) {
        this.street = street;
    }

    /*
     * @desc : Getter method for retrieving the village.
     * @params :  no params
     * @return The village or locality in the address.
     */
    public String getVillage() {
        return village;
    }

    /*
     * @desc : Setter method for updating the village.
     * @param village New village or locality in the address.
     * @return : no return
     */
    public void setVillage(String village) {
        this.village = village;
    }

    /*
     * @desc : Getter method for retrieving the city.
     * @params :  no params
     * @return The city in the address.
     */
    public String getCity() {
        return city;
    }

    /*
     * @desc : Setter method for updating the city.
     * @param city New city in the address.
     * @return : no return
     */
    public void setCity(String city) {
        this.city = city;
    }

    /*
     * @desc : Getter method for retrieving the state.
     * @params :  no params
     * @return The state in the address.
     */
    public String getState() {
        return state;
    }

    /*
     * @desc : Setter method for updating the state.
     * @param state New state in the address.
     * @return : no return
     */
    public void setState(String state) {
        this.state = state;
    }

    /*
     * @desc : Getter method for retrieving the zip.
     * @params :  no params
     * @return The zip code or postal code in the address.
     */
    public String getZip() {
        return zip;
    }

    /*
     * @desc : Setter method for updating the zip.
     * @param zip New zip code or postal code in the address.
     * @return : no return
     */
    public void setZip(String zip) {
        this.zip = zip;
    }

    /*
     * @desc : Getter method for retrieving the country.
     * @params :  no params
     * @return The country in the address.
     */
    public String getCountry() {
        return country;
    }

    /*
     * @desc : Setter method for updating the country.
     * @param country New country in the address.
     * @return : no return
     */
    public void setCountry(String country) {
        this.country = country;
    }

    /*
     * @desc : Method to set the Address details using another Address object.
     * @param providedAddress The Address object whose details will be copied.
     * @return : no return
     */
    public void setAddress(Address providedAddress) {
        setEmployeeId(providedAddress.getEmployeeId());
        setStreet(providedAddress.getStreet());
        setVillage(providedAddress.getVillage());
        setCity(providedAddress.getCity());
        setState(providedAddress.getState());
        setZip(providedAddress.getZip());
        setCountry(providedAddress.getCountry());
    }
}
