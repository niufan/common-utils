package site.niufan.common.utils.model;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import java.util.Arrays;
import java.util.List;

/**
 * Payload 测试类
 * @author Fan Niu
 * @since 2018/8/18
 */
@RunWith(JUnit4.class)
public class PayloadTest {

    @Test
    public void testCode() {
        Assert.assertEquals(PayloadBuilder.string().getSuccess().getCode(), PayloadBuilder.string().success().getCode());
        Assert.assertEquals(PayloadBuilder.string().getFailure().getCode(), PayloadBuilder.string().failure().getCode());

        Assert.assertEquals(PayloadBuilder.integer().getSuccess().getCode(), PayloadBuilder.integer().success().getCode());
        Assert.assertEquals(PayloadBuilder.integer().getFailure().getCode(), PayloadBuilder.integer().failure().getCode());
    }

    @Test
    public void testMessage() {
        Assert.assertEquals(PayloadBuilder.string().getSuccess().getMessage(), PayloadBuilder.string().success().getMessage());
        Assert.assertEquals(PayloadBuilder.string().getFailure().getMessage(), PayloadBuilder.string().failure().getMessage());

        Assert.assertEquals(PayloadBuilder.integer().getSuccess().getMessage(), PayloadBuilder.integer().success().getMessage());
        Assert.assertEquals(PayloadBuilder.integer().getFailure().getMessage(), PayloadBuilder.integer().failure().getMessage());
    }

    @Test
    public void testData() {
        PayloadTest payloadTest = new PayloadTest();
        Class<?> t1 = getClass();
        String t2 = "t2";
        Integer t3 = 3;
        Assert.assertEquals(t1, PayloadBuilder.string().success(payloadTest::function0).getData());
        Assert.assertEquals(t1, PayloadBuilder.string().success(payloadTest::function1, t1).getData());
        List list;
        list = PayloadBuilder.<List>string().success(payloadTest::function2, t1, t2).getData();
        Assert.assertEquals(t1, list.get(0));
        Assert.assertEquals(t2, list.get(1));
        list = PayloadBuilder.<List>string().success(payloadTest::function3, t1, t2, t3).getData();
        Assert.assertEquals(t1, list.get(0));
        Assert.assertEquals(t2, list.get(1));
        Assert.assertEquals(t3, list.get(2));
        System.out.println(PayloadBuilder.string().success(payloadTest::voidFunction0));
        System.out.println(PayloadBuilder.string().success(payloadTest::voidFunction1, t1));
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
