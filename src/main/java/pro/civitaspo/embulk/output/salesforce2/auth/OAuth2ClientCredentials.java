package pro.civitaspo.embulk.output.salesforce2.auth;

public class OAuth2ClientCredentials extends AbstractBearerTokenProvider {

    private final String clientId;
    private final String clientSecret;

    public OAuth2ClientCredentials(String clientId, String clientSecret) {
        this.clientId = clientId;
        this.clientSecret = clientSecret;
    }

    @Override
    public void refreshBearerToken() {
        // TODO: Implement this method
        throw new UnsupportedOperationException("Unimplemented method 'refreshBearerToken'");
    }
}
