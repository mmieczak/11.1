package data;

import utils.FileOperations;

import java.io.IOException;
import java.util.ArrayList;

public class Company {
    private final String name;
    private double minimumSalary = Double.MAX_VALUE;
    private double maximumSalary = Double.MIN_VALUE;
    private double averageSalary = 0;
    private double employeeCounter = 0;
    private ArrayList<Department> departments;

    public Company(String name, String fileName) throws IOException {
        this.name = name;
        getCompanyStatistics(fileName);
    }

    public void getCompanyStatistics(String fileName) throws IOException {

        FileOperations fileOperations = new FileOperations(fileName);
        departments = fileOperations.readDatafromFile();

        for (Department department : departments) {
            if (department.getMaximumSalary() > maximumSalary)
                maximumSalary = department.getMaximumSalary();
            if (department.getMinimumSalary() < minimumSalary)
                minimumSalary = department.getMinimumSalary();
            averageSalary += department.getAverageSalary();
            employeeCounter += department.getEmployeesNumber();
        }
        averageSalary = averageSalary / departments.size();
    }

    @Override
    public String toString() {
        return "Company{" +
                "name='" + name + '\'' +
                ", minimumSalary=" + minimumSalary +
                ", maximumSalary=" + maximumSalary +
                ", averageSalary=" + averageSalary +
                ", employeeCounter=" + employeeCounter +
                '}';
    }

    public String getName() {
        return name;
    }

    public double getMinimumSalary() {
        return minimumSalary;
    }

    public double getMaximumSalary() {
        return maximumSalary;
    }

    public double getAverageSalary() {
        return averageSalary;
    }

    public double getEmployeeCounter() {
        return employeeCounter;
    }

    public ArrayList<Department> getDepartments() {
        return departments;
    }
}
