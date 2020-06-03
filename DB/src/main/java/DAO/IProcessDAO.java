package DAO;

import Entities.Process;
import com.fasterxml.jackson.core.JsonProcessingException;

import java.util.List;

public interface IProcessDAO {
    void addProcess(Process process);
    void deleteProcess(int id);
    List<Process> getAllProcesses();
    //void printAllProcess() throws JsonProcessingException;
    List<Process> searchProcessById(int id) throws JsonProcessingException;
    List<Process> searchProcessByName(String name) throws JsonProcessingException;
}
