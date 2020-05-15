import data.Company;
import utils.FileOperations;

import java.io.IOException;

public class Tester {

    public static void main(String[] args) throws IOException {

        Company company = new Company("Tesla");
        company.getCompanyStatistics("userList.csv");

        FileOperations.savaDataToFile("CompanyReport.txt", company);
    }
}
