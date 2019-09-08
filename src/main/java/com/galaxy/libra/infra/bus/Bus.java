package com.galaxy.libra.infra.bus;

import com.google.common.eventbus.EventBus;
import lombok.Data;
import org.springframework.stereotype.Component;

/**
 * @author caesar
 * @title
 * @description
 * @package com.galaxy.libra.infra.bus
 * @date 2019-09-08
 * @time 15:12
 * @p_name spring
 */
@Data
@Component
public class Bus {
    private EventBus bus =new EventBus();
}
