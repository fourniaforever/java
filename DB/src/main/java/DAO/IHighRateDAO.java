package DAO;

import Entities.HighRates;
import com.fasterxml.jackson.core.JsonProcessingException;

import java.util.List;

public interface IHighRateDAO {
    void addHighRate(HighRates highRates);
    void deleteHighRate(int id);
    List<HighRates> getAllHighRate();
   //void printAllHighRate() throws JsonProcessingException;

    List<HighRates> searchHighRateId(int id) throws JsonProcessingException;
    List<HighRates> searchHighRateByName(String name) throws JsonProcessingException;
}
