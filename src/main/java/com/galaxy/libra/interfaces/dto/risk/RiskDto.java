package com.galaxy.libra.interfaces.dto.risk;

import com.galaxy.libra.dom.biz.event.RiskInsuredAmntEvent;
import com.galaxy.libra.dom.biz.vo.http.RiskAmntRequstBody;
import com.galaxy.libra.infra.bus.Bus;
import com.galaxy.libra.interfaces.dto.EventDto;
import com.google.common.eventbus.EventBus;
import org.springframework.http.HttpEntity;
import org.springframework.stereotype.Component;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @author caesar
 * @title
 * @description
 * @package com.galaxy.libra.interfaces.dto.risk
 * @date 2019-09-08
 * @time 15:55
 * @p_name spring
 */
@Component
public class RiskDto implements EventDto<RiskAmntRequstBody, RiskInsuredAmntEvent> {
    @Override
    public RiskInsuredAmntEvent genAndPostEvent(HttpEntity<RiskAmntRequstBody> httpEntityHttpEntity, Bus bus) {
        final RiskAmntRequstBody body = httpEntityHttpEntity.getBody();
        final String submitTime = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date());
        final RiskInsuredAmntEvent event = new RiskInsuredAmntEvent(body.getPolNo(), body.getInsuredNo(), body.getRiskCode(),
                body.getContno(), submitTime, 0.0);
        bus.getBus().post(event);
        return event;
    }

}
