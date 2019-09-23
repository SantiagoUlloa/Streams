package Week4Homework5;

import com.sun.xml.internal.ws.api.model.wsdl.WSDLOutput;
import org.junit.Test;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Lab {
    private List<Employee> employees = Arrays.asList(
            new Employee("Bezos, Jeff", LocalDate.of(2004, 4, 2), 68_109.00, "Male"),
            new Employee("Sheryl Sandberg", LocalDate.of(2014, 7, 1), 87_846.00, "Female"),
            new Employee("Buffet, Warren", LocalDate.of(2011, 7, 23), 95_035.00, "Male"),
            new Employee("Susan Wojcick", LocalDate.of(2015, 6, 1), 37_210.00, "Female"),
            new Employee("Zuckerberg, Mark", LocalDate.of(2016, 5, 12), 48_450.00, "Male"),
            new Employee("Brin, Sergey", LocalDate.of(2016, 8, 5), 74_416.00, "Male")
    );


    private <T> void printList(List<T> list) {
        list.forEach(System.out::print);
    }


    //print a list of all Employees that earn 50K or more.
    @Test
    public void getEmployeesOver50k() {
        List<Employee> employeesOver50k =
                employees.stream()
                        .filter(e -> e.getSalary() >= 50_000)
                        .collect(Collectors.toList());

        printList(employeesOver50k);
    }


    @Test
    public void getEmployeeNamesHiredAfter2012() {
        List<Employee> employeesAfter2012 =
                employees.stream()
                        .filter(e -> e.getHireDate().isAfter(LocalDate.of(2012, 1, 1)))
                        .collect(Collectors.toList());

        System.out.println("Employees hired after 2012: ");
        printList(employeesAfter2012);
    }

    @Test
    public void getMaxSalary() {
        Employee maxBySalary = employees
                .stream()
                .max(Comparator.comparing(Employee::getSalary))
                .orElseThrow(NoSuchElementException::new);
        System.out.println("Max salary: " + maxBySalary.getSalary());
    }

    @Test
    public void getMinSalary() {
        Employee minBySalary = employees
                .stream()
                .min(Comparator.comparing(Employee::getSalary))
                .orElseThrow(NoSuchElementException::new);
        System.out.println("Min salary: " + minBySalary.getSalary());
    }

    @Test
    public void getAverageSalaries() {
        double averageMale = employees
                .stream()
                .filter(e -> e.getGender() == "Male")
                .mapToDouble(Employee::getSalary)
                .average()
                .orElseThrow(NoSuchElementException::new);

        double averageFemale = employees
                .stream()
                .filter(e -> e.getGender() == "Female")
                .mapToDouble(Employee::getSalary)
                .average()
                .orElseThrow(NoSuchElementException::new);
        System.out.println("Averages: Male:" + averageMale + " Female:" + averageFemale);
    }

    @Test
    public void getMaximumPaidEmployee() {
        Employee maxBySalary = employees
                .stream()
                .max(Comparator.comparing(Employee::getSalary))
                .orElseThrow(NoSuchElementException::new);
        System.out.println("Highest paid employee: " + maxBySalary.getName());
    }
}
