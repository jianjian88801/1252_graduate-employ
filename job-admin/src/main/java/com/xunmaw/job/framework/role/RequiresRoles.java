package com.xunmaw.job.framework.role;

import java.lang.annotation.*;

@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface RequiresRoles {

    Role type();
}
