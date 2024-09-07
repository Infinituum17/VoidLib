package infinituum.void_lib.examples.annotated_classes;

import infinituum.void_lib.examples.annotations.NoParam;
import infinituum.void_lib.examples.annotations.OneString;

public class AnnotationInnerClass {
    @NoParam
    public static class AnnotatedInnerClass {
        @OneString(str = "hello")
        public static class AnnotatedNestedInnerClass {

        }
    }
}
