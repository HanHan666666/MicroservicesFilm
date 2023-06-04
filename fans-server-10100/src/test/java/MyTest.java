import com.system.utils.RedisUtil;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
@SpringBootTest
public class MyTest {
    @Autowired
    RedisUtil redisUtil;
    @Test
    public void test() {
        redisUtil.hset("smscode","name", "zhangsan",100);
    }
}
