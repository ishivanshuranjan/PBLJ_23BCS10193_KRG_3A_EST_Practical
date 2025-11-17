import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class Question1 {
    
	public static void main(String[] args) {
		List<String> students = new ArrayList<>();
		students.add("Shivanshu");
		students.add("Ranjan");
		students.add("Singh");
		List<String> sortedlist = students.stream()
				.sorted()
				.collect(Collectors.toList());

		System.out.println(sortedlist);
	}
}
