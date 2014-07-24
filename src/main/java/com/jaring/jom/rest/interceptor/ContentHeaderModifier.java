package com.jaring.jom.rest.interceptor;

import static com.jaring.jom.rest.interceptor.HeaderInterface.*;

import java.io.IOException;

import javax.ws.rs.HeaderParam;
import javax.ws.rs.OPTIONS;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.ResponseBuilder;
import javax.ws.rs.ext.Provider;

import org.jboss.resteasy.annotations.interception.ServerInterceptor;
import org.jboss.resteasy.spi.interception.MessageBodyWriterContext;
import org.jboss.resteasy.spi.interception.MessageBodyWriterInterceptor;

@Provider
@ServerInterceptor
public class ContentHeaderModifier  implements MessageBodyWriterInterceptor{
	@Override
	public void write(final MessageBodyWriterContext context) throws IOException,
			WebApplicationException {
		context.getHeaders().add(ACCESS_CONTROL_ALLOW_ORIGIN , "*");
		context.proceed();
	}
	
	@OPTIONS
    public Response handleCORSRequest(
        @HeaderParam(ACCESS_CONTROL_REQUEST_METHOD) final String requestMethod,
        @HeaderParam(ACCESS_CONTROL_REQUEST_HEADERS) final String requestHeaders)
    {
        final ResponseBuilder retValue = Response.ok();

        if (requestHeaders != null)
            retValue.header(ACCESS_CONTROL_ALLOW_HEADERS, requestHeaders);

        if (requestMethod != null)
            retValue.header(ACCESS_CONTROL_ALLOW_METHODS, requestMethod);

        retValue.header(ACCESS_CONTROL_ALLOW_ORIGIN, "*");

        return retValue.build();
    }
}
