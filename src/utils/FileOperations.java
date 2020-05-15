package utils;

import data.Company;
import data.Department;
import data.Employee;

import java.io.*;
import java.util.ArrayList;

public class FileOperations {
    public final String fileName;
    private final Department nullDept = new Department("nullDepartment");

    public FileOperations(String fileName) {
        this.fileName = fileName;
    }

    public ArrayList<Department> readDatafromFile() throws IOException {

        File file = new File(fileName);
        String[] lineValues;
        ArrayList<Department> departments = new ArrayList<>();

        try (
                var fileReader = new FileReader(fileName);
                var reader = new BufferedReader(fileReader)
        ) {
            String nextLine;
            int lines = 0;
            while ((nextLine = reader.readLine()) != null) {
                lineValues = nextLine.split(";");
                Department department = createDepartment(lineValues, departments);
                if (department != nullDept)
                    departments.add(department);
            }
        }
        return departments;
    }

    public static void savaDataToFile(String fileName, Company company) {
        try (
                var fileWriter = new FileWriter(fileName);
                var writer = new BufferedWriter(fileWriter)
        ) {
            writer.write("Average salary per Department: " + company.getAverageSalary());
            writer.newLine();
            writer.write("Lowest salary: " + company.getMinimumSalary());
            writer.newLine();
            writer.write("Highest salary: " + company.getMaximumSalary());
            writer.newLine();
            writer.write("Total employees: " + company.getEmployeeCounter());
            writer.newLine();

            for (int i = 0; i < company.getDepartments().size(); i++) {
                writer.write("Total employees in " + company.getDepartments().get(i).getName() + ": " + company.getDepartments().get(i).getEmployeesNumber());
                writer.newLine();
            }
            System.out.println("Report created");
        } catch (IOException e) {
            System.err.println("Unable to save the file " + fileName);
        }
    }

    private Department createDepartment(String[] lineValues, ArrayList<Department> departments) {

        try {
            String departmentName = lineValues[3];
            String employeeName = lineValues[0];
            String employeeSecondName = lineValues[1];
            String pesel = lineValues[2];
            for (Department department : departments) {
                if (department.getName().equals(departmentName)) {
                    try {
                        Employee employee = new Employee(employeeName, employeeSecondName, Long.parseLong(pesel), Double.parseDouble(lineValues[4]));
                        department.addEmployee(employee);
                    } catch (NumberFormatException ex) {
                        System.err.println("Employee data are wrong. Unable to add employee to existing department");
                    }
                    return nullDept;
                }
            }
            try {
                Employee employee = new Employee(employeeName, employeeSecondName, Long.parseLong(pesel), Double.parseDouble(lineValues[4]));
            } catch (NumberFormatException ex) {
                System.err.println("Employee data are wrong. Unable to add employee to new department : " + departmentName);
            } finally {
                System.out.println("Added new department: " + departmentName);
            }
            return new Department(departmentName);

            //return new Department(lineValues[3], employee);
        } catch (RuntimeException ex) {
            System.err.println("Invalid input data :");
            ex.printStackTrace();
            return nullDept;
        }
    }
}