package utils;

import data.Company;
import data.Department;
import data.Employee;
import java.io.*;
import java.util.ArrayList;

public class FileOperations {
    private final String fileName;
    private Department nullDept = new Department("nullDepartment");

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
            for (Department department : departments) {
                if (department.getName().equals(lineValues[3])) {
                    Employee employee = new Employee(lineValues[0], lineValues[1], Long.parseLong(lineValues[2]), Double.parseDouble(lineValues[4]));
                    department.addEmployee(employee);
                    return nullDept;
                }
            }
            Employee employee = new Employee(lineValues[0], lineValues[1], Long.parseLong(lineValues[2]), Double.parseDouble(lineValues[4]));
            return new Department(lineValues[3], employee);
        } catch (RuntimeException ex) {
            System.err.println("Invalid input data :");
            ex.printStackTrace();
            return nullDept;
        }
    }
}