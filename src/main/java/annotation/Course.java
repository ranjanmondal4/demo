package annotation;

import java.lang.annotation.*;

@Inherited
@Documented
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
public @interface Course {
    String cid() default "C-111";
    String cname() default "C programming";
    int ccost() default 10000;
}
