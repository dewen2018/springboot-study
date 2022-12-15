// package com.dewen;
//
// import org.springframework.web.servlet.mvc.condition.PathPatternsRequestCondition;
// import org.springframework.web.servlet.mvc.condition.PatternsRequestCondition;
//
// import java.util.Set;
// import java.util.stream.Collectors;
//
// import static springfox.documentation.spring.web.paths.Paths.maybeChompLeadingSlash;
// import static springfox.documentation.spring.web.paths.Paths.maybeChompTrailingSlash;
//
// /**
//  * swagger3.0兼容springBoot2.6.3处理
//  */
// public class WebMvcPatternsRequestConditionWrapper implements springfox.documentation.spring.wrapper.PatternsRequestCondition<PatternsRequestCondition> {
//
//     private final String contextPath;
//     private final PathPatternsRequestCondition condition;
//
//     public WebMvcPatternsRequestConditionWrapper(
//             String contextPath,
//             PathPatternsRequestCondition condition) {
//
//         this.contextPath = contextPath;
//         this.condition = condition;
//     }
//
//     @Override
//     public springfox.documentation.spring.wrapper.PatternsRequestCondition combine(
//             springfox.documentation.spring.wrapper.PatternsRequestCondition<PatternsRequestCondition> other) {
//         if (other instanceof WebMvcPatternsRequestConditionWrapper && !this.equals(other)) {
//             return new WebMvcPatternsRequestConditionWrapper(
//                     contextPath,
//                     condition.combine(((WebMvcPatternsRequestConditionWrapper) other).condition));
//         }
//         return this;
//     }
//
//     @Override
//     public Set<String> getPatterns() {
//         return this.condition.getPatternValues().stream()
//                 .map(p -> String.format("%s/%s", maybeChompTrailingSlash(contextPath),
//                         maybeChompLeadingSlash(p)))
//                 .collect(Collectors.toSet());
//     }
//
//
//     @Override
//     public boolean equals(Object o) {
//         if (o instanceof WebMvcPatternsRequestConditionWrapper) {
//             return this.condition.equals(((WebMvcPatternsRequestConditionWrapper) o).condition);
//         }
//         return false;
//     }
//
//     @Override
//     public int hashCode() {
//         return this.condition.hashCode();
//     }
//
//
//     @Override
//     public String toString() {
//         return this.condition.toString();
//     }
// }