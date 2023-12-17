import com.calanco.todolist.adapters.DataAdapter;
import com.calanco.todolist.model.ListItem;
import org.junit.jupiter.api.Test;

import java.io.IOException;

public class DataAdapterTests {
    @Test
    public void test1() throws IOException {
        DataAdapter adapter = new DataAdapter("/ToDoList-1.0-SNAPSHOT");
        adapter.clear();
        System.out.println(System.getProperty("user.dir"));
        ListItem elem = new ListItem("root", -1, 0, null);
        adapter.storeNew(elem);
        ListItem result = adapter.getLists();
        assert result.equals(elem);
    }
    @Test
    public void test2() throws IOException {
        DataAdapter adapter = new DataAdapter("/ToDoList-1.0-SNAPSHOT");
        adapter.clear();
        System.out.println(System.getProperty("user.dir"));
        ListItem elem = new ListItem("root", -1, 0, null);
        adapter.storeNew(elem);
        elem.getArrayList().add(new ListItem("a", elem));
        ListItem result = adapter.getLists();
        assert result.equals(elem);
    }
}
