package steps;

import org.junit.Assert;
import utilities.JDBCUtils;
import java.util.List;
import java.util.Map;

public class JDBCUtils_test {
    public static void main(String[] args) throws Exception {

        JDBCUtils.establishConnection();

        List<Map<String, Object>> data = JDBCUtils.runQuery("select * from employees");

        Assert.assertEquals(data.get(0).get("first_name").toString(), "Neena");
        Assert.assertEquals(data.get(0).get("last_name").toString(), "Kochhar");
        Assert.assertEquals(data.get(0).get("department_id").toString(), "9");

        JDBCUtils.closeConnection();
        //data.clear();
        //Assert.assertEquals(data.get(0).get("first_name").toString(), "name");



    }

}
