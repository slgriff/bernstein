package bernstein.handler;

import lombok.NonNull;
import lombok.extern.slf4j.Slf4j;
import org.springframework.aop.interceptor.AsyncUncaughtExceptionHandler;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;

@Component
@Slf4j
public class AsyncExceptionHandler implements AsyncUncaughtExceptionHandler {
    @Override
    public void handleUncaughtException(@NonNull Throwable ex, @NonNull Method method, @NonNull Object... params) {
        log.error("Uncaught asynchronous exception at {}.{}", method.getDeclaringClass().getName(), method.getName(),
                ex);
    }
}
