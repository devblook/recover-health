package me.bryang.recoverhealth.modules.file;

import java.lang.annotation.Target;

;import static java.lang.annotation.ElementType.*;

@Target({FIELD, PARAMETER, METHOD})
public @interface FileAnnotation{

}
