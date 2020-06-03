package DAO;

import Entities.Countries;
import com.fasterxml.jackson.core.JsonProcessingException;

import java.util.List;

public interface ICountryDAO {
    void addCountry(Countries country);
    void deleteCountry(int id);
    List<Countries> getAllCountries();
  //void printAllCountries() throws JsonProcessingException;
    List<Countries> searchCountryId(int id) throws JsonProcessingException;
    List<Countries> searchCountriesByName(String name) throws JsonProcessingException;
}
