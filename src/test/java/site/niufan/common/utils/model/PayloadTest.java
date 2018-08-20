package site.niufan.common.utils.model;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import java.util.Arrays;
import java.util.List;

import static site.niufan.common.utils.model.Payload.PayloadBuilder.integer;
import static site.niufan.common.utils.model.Payload.PayloadBuilder.string;

/**
 * Payload 测试类
 * @author Fan Niu
 * @since 2018/8/18
 */
@RunWith(JUnit4.class)
public class PayloadTest {

    @Test
    public void testCode() {
        Assert.assertEquals(string().getSuccess().getCode(), string().success().getCode());
        Assert.assertEquals(string().getFailure().getCode(), string().failure().getCode());

        Assert.assertEquals(integer().getSuccess().getCode(), integer().success().getCode());
        Assert.assertEquals(integer().getFailure().getCode(), integer().failure().getCode());
    }

    @Test
    public void testMessage() {
        Assert.assertEquals(string().getSuccess().getMessage(), string().success().getMessage());
        Assert.assertEquals(string().getFailure().getMessage(), string().failure().getMessage());

        Assert.assertEquals(integer().getSuccess().getMessage(), integer().success().getMessage());
        Assert.assertEquals(integer().getFailure().getMessage(), integer().failure().getMessage());
    }

    @Test
    public void testData() {
        PayloadTest payloadTest = new PayloadTest();
        Class<?> t1 = getClass();
        String t2 = "t2";
        Integer t3 = 3;
        Assert.assertEquals(t1, string().success(payloadTest::function0).getData());
        Assert.assertEquals(t1, string().success(payloadTest::function1, t1).getData());
        List list;
        list = string(List.class).success(payloadTest::function2, t1, t2).getData();
        Assert.assertEquals(t1, list.get(0));
        Assert.assertEquals(t2, list.get(1));
        list = string(List.class).success(payloadTest::function3, t1, t2, t3).getData();
        Assert.assertEquals(t1, list.get(0));
        Assert.assertEquals(t2, list.get(1));
        Assert.assertEquals(t3, list.get(2));
        System.out.println(string().success(payloadTest::voidFunction0));
        System.out.println(string().success(payloadTest::voidFunction1, t1));
    }

    private Class<?> function0() {
        return getClass();
    }

    private Class<?> function1(Class<?> clazz) {
        return clazz;
    }

    private List function2(Class<?> clazz, String t2) {
        return Arrays.asList(clazz, t2);
    }

    private List function3(Class<?> clazz, String t2, Integer t3) {
        return Arrays.asList(clazz, t2, t3);
    }

    private void voidFunction0() {
        Assert.assertTrue(true);
    }

    private void voidFunction1(Class<?> clazz) {
        Assert.assertEquals(getClass(), clazz);
    }


}
