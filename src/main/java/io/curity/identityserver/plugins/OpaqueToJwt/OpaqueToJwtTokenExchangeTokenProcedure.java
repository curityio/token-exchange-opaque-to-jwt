package io.curity.identityserver.plugins.OpaqueToJwt;

import se.curity.identityserver.sdk.Nullable;
import se.curity.identityserver.sdk.attribute.token.AccessTokenAttributes;
import se.curity.identityserver.sdk.data.authorization.Delegation;
import se.curity.identityserver.sdk.data.tokens.TokenIssuerException;
import se.curity.identityserver.sdk.errors.ErrorCode;
import se.curity.identityserver.sdk.procedure.token.OAuthTokenExchangeTokenProcedure;
import se.curity.identityserver.sdk.procedure.token.context.OAuthTokenExchangeUnInitializedTokenProcedurePluginContext;
import se.curity.identityserver.sdk.procedure.token.context.PresentedToken;
import se.curity.identityserver.sdk.service.issuer.AccessTokenIssuer;
import se.curity.identityserver.sdk.service.issuer.DefaultJwtAccessTokenIssuerProvider;
import se.curity.identityserver.sdk.web.ResponseModel;

import java.time.Instant;
import java.util.HashMap;

public final class OpaqueToJwtTokenExchangeTokenProcedure implements OAuthTokenExchangeTokenProcedure
{
    private final OpaqueToJwtTokenProcedureConfig _configuration;

    public OpaqueToJwtTokenExchangeTokenProcedure(OpaqueToJwtTokenProcedureConfig configuration)
    {
        _configuration = configuration;
    }

    @Override
    public ResponseModel run(OAuthTokenExchangeUnInitializedTokenProcedurePluginContext context)
    {
        PresentedToken presentedSubjectToken = context.getPresentedSubjectToken();
        Delegation presentedDelegation = context.getPresentedSubjectTokenDelegation();
        DefaultJwtAccessTokenIssuerProvider provider = _configuration.getJwtAccessTokenIssuerProvider();

        if (presentedSubjectToken == null) {
            throw _configuration.getExceptionFactory().badRequestException(ErrorCode.TOKEN_ISSUANCE_ERROR, "Invalid subject token");
        }

        try
        {
            context.getInitializedContext(presentedDelegation.getAuthenticationAttributes(), presentedSubjectToken);
            AccessTokenAttributes tokenAttributes = AccessTokenAttributes.of(presentedSubjectToken.getTokenData());

            @Nullable AccessTokenIssuer issuer = provider.getDefaultJwtAccessTokenIssuer();
            if (issuer == null) {
                throw _configuration.getExceptionFactory().badRequestException(ErrorCode.TOKEN_ISSUANCE_ERROR, "JSON Web Tokens not enabled.");
            }
            @Nullable String issuedAccessToken = issuer.issue(tokenAttributes,presentedDelegation);
            if (issuedAccessToken == null) {
                throw _configuration.getExceptionFactory().badRequestException(ErrorCode.TOKEN_ISSUANCE_ERROR, "No access token issued.");
            }

            HashMap<String, Object> responseData = new HashMap<>();
            responseData.put("scope", tokenAttributes.getScope());
            responseData.put("access_token", issuedAccessToken);
            responseData.put("token_type", "bearer");
            responseData.put("expires_in", tokenAttributes.getExpires().getEpochSecond() - Instant.now().getEpochSecond());
            responseData.put("issued_token_type","urn:ietf:params:oauth:token-type:access_token");

            return ResponseModel.mapResponseModel(responseData);

        }
        catch (TokenIssuerException e)
        {
            return ResponseModel.problemResponseModel("token_issuer_exception", "Could not issue new tokens");
        }
    }
    }

