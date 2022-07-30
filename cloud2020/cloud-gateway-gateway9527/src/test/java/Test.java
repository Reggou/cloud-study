import lombok.extern.slf4j.Slf4j;

import java.time.ZonedDateTime;

/**
 * @author wzzmm
 * @since 2022/7/29 23:17
 */

@Slf4j
public class Test {
    public static void main(String[] args) {
        ZonedDateTime now = ZonedDateTime.now();
        log.info("time:"+now);
        //2022-07-29T23:21:22.998+08:00[Asia/Shanghai]
    }
}
