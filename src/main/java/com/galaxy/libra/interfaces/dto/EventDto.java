package com.galaxy.libra.interfaces.dto;

import com.galaxy.libra.infra.bus.Bus;
import org.springframework.http.HttpEntity;

/**
 * @author caesar
 * @title
 * @description
 * @package com.galaxy.libra.interfaces.dto
 * @date 2019-09-08
 * @time 15:50
 * @p_name spring
 */
public interface EventDto<RequstBody, Event> {
    public Event genAndPostEvent(HttpEntity<RequstBody> requstBodyHttpEntity, Bus bus);
}
