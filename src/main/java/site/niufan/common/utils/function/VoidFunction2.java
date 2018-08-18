package site.niufan.common.utils.function;

/**
 * @author Fan Niu
 * @since 2018/8/18
 */
@FunctionalInterface
public interface VoidFunction2<T1, T2> {

    void apply(T1 t1, T2 t2);
}
