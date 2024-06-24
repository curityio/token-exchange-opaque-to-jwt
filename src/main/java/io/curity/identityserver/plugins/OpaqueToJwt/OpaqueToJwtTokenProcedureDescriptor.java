package io.curity.identityserver.plugins.OpaqueToJwt;

import se.curity.identityserver.sdk.plugin.descriptor.TokenProcedurePluginDescriptor;
import se.curity.identityserver.sdk.procedure.token.AssertionTokenProcedure;
import se.curity.identityserver.sdk.procedure.token.AssistedTokenProcedure;
import se.curity.identityserver.sdk.procedure.token.AuthorizationCodeTokenProcedure;
import se.curity.identityserver.sdk.procedure.token.AuthorizeCodeTokenProcedure;
import se.curity.identityserver.sdk.procedure.token.AuthorizeImplicitTokenProcedure;
import se.curity.identityserver.sdk.procedure.token.BackchannelAuthenticationTokenProcedure;
import se.curity.identityserver.sdk.procedure.token.ClientCredentialsTokenProcedure;
import se.curity.identityserver.sdk.procedure.token.DeviceAuthorizationTokenProcedure;
import se.curity.identityserver.sdk.procedure.token.DeviceCodeTokenProcedure;
import se.curity.identityserver.sdk.procedure.token.IntrospectionApplicationJwtTokenProcedure;
import se.curity.identityserver.sdk.procedure.token.IntrospectionTokenProcedure;
import se.curity.identityserver.sdk.procedure.token.OAuthTokenExchangeTokenProcedure;
import se.curity.identityserver.sdk.procedure.token.OpenIdAuthorizeEndpointHybridTokenProcedure;
import se.curity.identityserver.sdk.procedure.token.OpenIdUserInfoTokenProcedure;
import se.curity.identityserver.sdk.procedure.token.RefreshTokenProcedure;
import se.curity.identityserver.sdk.procedure.token.RopcTokenProcedure;
import se.curity.identityserver.sdk.procedure.token.TokenExchangeTokenProcedure;

public final class OpaqueToJwtTokenProcedureDescriptor implements TokenProcedurePluginDescriptor<OpaqueToJwtTokenProcedureConfig>
{
    @Override
    public Class<? extends OAuthTokenExchangeTokenProcedure> getOAuthTokenEndpointOAuthTokenExchangeTokenProcedure()
    {
        return OpaqueToJwtTokenExchangeTokenProcedure.class;
    }

    @Override
    public String getPluginImplementationType()
    {
        return "opaquetojwt";
    }

    @Override
    public Class<? extends OpaqueToJwtTokenProcedureConfig> getConfigurationType()
    {
        return OpaqueToJwtTokenProcedureConfig.class;
    }    
}
