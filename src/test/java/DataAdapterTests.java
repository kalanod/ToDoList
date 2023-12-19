import com.calanco.todolist.adapters.DataAdapter;
import com.calanco.todolist.adapters.UserAdapter;
import com.calanco.todolist.model.ListItem;
import com.calanco.todolist.model.User;
import org.junit.jupiter.api.Test;

import java.io.IOException;

public class DataAdapterTests {
    @Test
    public void test1() throws IOException {
        DataAdapter adapter = new DataAdapter("/Gradle___com_calanco___ToDoList_1_0_SNAPSHOT_war", 1);
        adapter.clear();
        System.out.println(System.getProperty("user.dir"));
        ListItem elem = new ListItem("root", -1, 0, null);
        adapter.storeNew(elem);
        ListItem result = adapter.getLists();
        assert result.equals(elem);
    }
    @Test
    public void test2() throws IOException {
        DataAdapter adapter = new DataAdapter("/Gradle___com_calanco___ToDoList_1_0_SNAPSHOT_war", 1);
        adapter.clear();
        System.out.println(System.getProperty("user.dir"));
        ListItem elem = new ListItem("root", -1, 0, null);
        adapter.storeNew(elem);
        elem.getArrayList().add(new ListItem("a", elem));
        ListItem result = adapter.getLists();
        assert !result.equals(elem);
    }
    @Test
    public void test3() throws IOException {
        UserAdapter userAdapter = new UserAdapter();
        User user = new User(987, "test3", "test3");
        userAdapter.add(user);
        assert userAdapter.isAuthorized(user);
    }
    @Test
    public void test4() throws IOException {
        UserAdapter userAdapter = new UserAdapter();
        User user = new User(9872, "test4", "test3");
        assert !userAdapter.isAuthorized(user);
    }
    @Test
    public void test5() throws IOException {
        DataAdapter adapter = new DataAdapter("/Gradle___com_calanco___ToDoList_1_0_SNAPSHOT_war", 1);
        adapter.clear();
        ListItem elem = new ListItem("root", -5, 0, null);
        adapter.storeNew(elem);
        adapter.swich(-5);
        ListItem result = adapter.getItem(adapter.getLists(), -5);
        assert !result.equals(elem);
    }
    @Test
    public void test6() throws IOException {
        DataAdapter adapter = new DataAdapter("/Gradle___com_calanco___ToDoList_1_0_SNAPSHOT_war", 1);
        adapter.clear();
        ListItem elem = new ListItem("root", -5, 0, null);
        adapter.storeNew(elem);
        adapter.swich(-5);
        elem.setIsChecked(1);
        ListItem result = adapter.getItem(adapter.getLists(), -5);
        assert result.equals(elem);
    }
}
