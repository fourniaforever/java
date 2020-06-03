package DAO;

import Entities.Rate;
import com.fasterxml.jackson.core.JsonProcessingException;

import java.util.List;

public interface IRateDAO {
    void addRate(Rate rate);
    void deleteRate(int id);
    List<Rate> getAllRates();
  //  void printAllRates() throws JsonProcessingException;
    List<Rate> searchRateById(int id) throws JsonProcessingException;
    List<Rate> searchRateByRate(int rate) throws JsonProcessingException;
}
