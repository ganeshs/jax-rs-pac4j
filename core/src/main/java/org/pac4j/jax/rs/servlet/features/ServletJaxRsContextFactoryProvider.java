package org.pac4j.jax.rs.servlet.features;

import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.ext.Provider;

import org.pac4j.jax.rs.features.JaxRsContextFactoryProvider;
import org.pac4j.jax.rs.servlet.pac4j.ServletJaxRsContext;

/**
 * Extends {@link JaxRsContextFactoryProvider} to support any servlet-based container and its session manager (i.e.,
 * pac4j indirect clients will work, contrary than with {@link JaxRsContextFactoryProvider}).
 * 
 * @see JaxRsContextFactoryProvider
 * @author Victor Noel - Linagora
 * @since 1.0.0
 *
 */
@Provider
public class ServletJaxRsContextFactoryProvider extends JaxRsContextFactoryProvider {

    @Inject
    private javax.inject.Provider<HttpServletRequest> requestProvider;

    @Override
    public JaxRsContextFactory getContext(Class<?> type) {
        return context -> new ServletJaxRsContext(getProviders(), context, getConfig().getSessionStore(), requestProvider.get());
    }
}