import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collector;

public class NameCollector {

    public static Collector<String, List<String>, String> create() {

        return Collector.of(
                ArrayList::new,

                List::add,

                (list1, list2) -> {
                    list1.addAll(list2);
                    return list1;
                },

                list -> {
                    String result = "";

                    for (int i = 0; i < list.size(); i++) {
                        if (i > 0) {
                            result += ", ";
                        }

                        result += list.get(i);
                    }

                    return result;
                }
        );
    }
}