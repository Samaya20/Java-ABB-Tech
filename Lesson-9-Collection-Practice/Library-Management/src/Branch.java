import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Branch {
    String branchId, name, address;
    Map<String, List<BookCopy>> copiesByBookId = new HashMap<>();

    public Branch(String branchId, String name, String address) {
        this.branchId = branchId;
        this.name = name;
        this.address = address;
    }
}