/* Copyright 2004, 2005, 2006 Acegi Technology Pty Limited
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.hw.hwsafe.security.session.listener;

import org.apache.log4j.Logger;
import org.springframework.context.ApplicationContext;

import org.springframework.security.web.session.HttpSessionCreatedEvent;
import org.springframework.security.web.session.HttpSessionDestroyedEvent;
import org.springframework.web.context.support.WebApplicationContextUtils;

import com.hw.hwsafe.platform.session.recorder.SessionRecorder;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;


/**
 * Declared in web.xml as
 * <pre>
 * &lt;listener&gt;
 *     &lt;listener-class&gt;org.springframework.security.web.session.HttpSessionEventPublisher&lt;/listener-class&gt;
 * &lt;/listener&gt;
 * </pre>
 *
 * Publishes <code>HttpSessionApplicationEvent</code>s to the Spring Root WebApplicationContext. Maps
 * javax.servlet.http.HttpSessionListener.sessionCreated() to {@link HttpSessionCreatedEvent}. Maps
 * javax.servlet.http.HttpSessionListener.sessionDestroyed() to {@link HttpSessionDestroyedEvent}.
 *
 * @author Ray Krueger
 */
public class CustomHttpSessionEventPublisher implements HttpSessionListener {
    //~ Static fields/initializers =====================================================================================

    private static final String LOGGER_NAME = CustomHttpSessionEventPublisher.class.getName();

    //~ Methods ========================================================================================================

    ApplicationContext getContext(ServletContext servletContext) {
        return WebApplicationContextUtils.getWebApplicationContext(servletContext);
    }

    /**
     * Handles the HttpSessionEvent by publishing a {@link HttpSessionCreatedEvent} to the application
     * appContext.
     *
     * @param event HttpSessionEvent passed in by the container
     */
    public void sessionCreated(HttpSessionEvent event) {
        HttpSessionCreatedEvent e = new HttpSessionCreatedEvent(event.getSession());
        Logger log = Logger.getLogger(LOGGER_NAME);

        if (log.isDebugEnabled()) {
            log.debug("Publishing event: " + e);
        }

        getContext(event.getSession().getServletContext()).publishEvent(e);
    }

    /**
     * Handles the HttpSessionEvent by publishing a {@link HttpSessionDestroyedEvent} to the application
     * appContext.
     *
     * @param event The HttpSessionEvent pass in by the container
     */
    public void sessionDestroyed(HttpSessionEvent event) {
        HttpSessionDestroyedEvent e = new HttpSessionDestroyedEvent(event.getSession());
        Logger log = Logger.getLogger(LOGGER_NAME);
        
        if (log.isDebugEnabled()) {
            log.debug("Publishing event: " + e);
        }

        // 移除session记录
        String sessionId = e.getSession().getId();
        if(SessionRecorder.isSessionLawful(sessionId)){
        	SessionRecorder.remove(sessionId);
        }
        
        getContext(event.getSession().getServletContext()).publishEvent(e);
    }
}
