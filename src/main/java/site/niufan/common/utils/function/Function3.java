package site.niufan.common.utils.function;

/**
 * @author Fan Niu
 * @since 2018/8/18
 */
@FunctionalInterface
public interface Function3<T1, T2, T3, R> {

    R apply(T1 t1, T2 t2, T3 t3);
}
