package com.eroly.filter;

import ch.qos.logback.core.filter.Filter;
import ch.qos.logback.core.spi.FilterReply;
import org.slf4j.event.LoggingEvent;

/**
 * Console日志过滤器，consoleLogEnable=false时不打日志
 */
public class ConsoleLogFilter extends Filter<LoggingEvent> {
    private boolean consoleLogEnable;

    public boolean isConsoleLogEnable() {
        return consoleLogEnable;
    }

    public void setConsoleLogEnable(boolean consoleLogEnable) {
        this.consoleLogEnable = consoleLogEnable;
    }
    public FilterReply decide(LoggingEvent event) {
        if(consoleLogEnable){
            return FilterReply.NEUTRAL;
        }
        return FilterReply.DENY;
    }

}
