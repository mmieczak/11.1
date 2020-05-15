package data;

import java.util.ArrayList;

public class Department {
    private String name;
    private ArrayList<Employee> employees;
    private int employeesNumber;
    private double minimumSalary = Double.MAX_VALUE;
    private double maximumSalary = Double.MIN_VALUE;
    private double averageSalary;

    public Department(String name, Employee employee) {
        this.name = name;
        this.employees = new ArrayList<>();
        this.employees.add(employee);

        calculateSalaryStatistics();
    }

    public Department(String name) {
        this.name = name;
        this.employees = new ArrayList<>();
    }

    public void addEmployee(Employee employee) {
        employees.add(employee);
        calculateSalaryStatistics();
    }

    private void calculateSalaryStatistics() {
        if (employees.size() != 1) {
            averageSalary = 0;
            for (Employee employee : employees) {
                if (employee.getSalary() > maximumSalary) {
                    maximumSalary = employee.getSalary();
                } else if (employee.getSalary() < minimumSalary) {
                    minimumSalary = employee.getSalary();
                }
                averageSalary += employee.getSalary();
            }
            employeesNumber = employees.size();
            averageSalary = averageSalary / employees.size();

        } else {
            try {
                maximumSalary = employees.get(0).getSalary();
            } catch (NumberFormatException ex) {
                maximumSalary = Double.MIN_VALUE;
            }
            if (maximumSalary != Double.MIN_VALUE) {
                minimumSalary = maximumSalary;
                averageSalary = maximumSalary;
                employeesNumber = 1;
            } else {
                minimumSalary = Double.MIN_VALUE;
                averageSalary = 0;
                employeesNumber = 0;
            }
        }
    }

    @Override
    public String toString() {
        return "Department{" +
                "name='" + name + '\'' +
                ", employees=" + employees +
                ", employeesNumber=" + employeesNumber +
                ", minimumSalary=" + minimumSalary +
                ", maximumSalary=" + maximumSalary +
                ", avarageSalary=" + averageSalary +
                '}';
    }

    //Getters & Setters

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public ArrayList<Employee> getEmployees() {
        return employees;
    }

    public void setEmployees(ArrayList<Employee> employees) {
        this.employees = employees;
    }

    public int getEmployeesNumber() {
        return employeesNumber;
    }

    public void setEmployeesNumber(int employeesNumber) {
        this.employeesNumber = employeesNumber;
    }

    public double getMinimumSalary() {
        return minimumSalary;
    }

    public void setMinimumSalary(double minimumSalary) {
        this.minimumSalary = minimumSalary;
    }

    public double getMaximumSalary() {
        return maximumSalary;
    }

    public void setMaximumSalary(double maximumSalary) {
        this.maximumSalary = maximumSalary;
    }

    public double getAverageSalary() {
        return averageSalary;
    }

    public void setAverageSalary(double averageSalary) {
        this.averageSalary = averageSalary;
    }
}
