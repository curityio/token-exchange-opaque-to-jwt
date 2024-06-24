package io.curity.identityserver.plugins.OpaqueToJwt;

import se.curity.identityserver.sdk.config.Configuration;
import se.curity.identityserver.sdk.config.annotation.DefaultService;
import se.curity.identityserver.sdk.service.ExceptionFactory;
import se.curity.identityserver.sdk.service.issuer.DefaultJwtAccessTokenIssuerProvider;

public interface OpaqueToJwtTokenProcedureConfig extends Configuration
{
    ExceptionFactory getExceptionFactory();

    @DefaultService
    DefaultJwtAccessTokenIssuerProvider getJwtAccessTokenIssuerProvider();

}
