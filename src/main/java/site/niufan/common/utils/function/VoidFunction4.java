package site.niufan.common.utils.function;

/**
 * @author Fan Niu
 * @since 2018/8/18
 */
@FunctionalInterface
public interface VoidFunction4<T1, T2, T3, T4> {

    void apply(T1 t1, T2 t2, T3 t3, T4 t4);
}
