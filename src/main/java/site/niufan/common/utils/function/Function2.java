package site.niufan.common.utils.function;

/**
 * @author Fan Niu
 * @since 2018/8/18
 */
@FunctionalInterface
public interface Function2<T1, T2, R> {

    R apply(T1 t, T2 t2);
}
