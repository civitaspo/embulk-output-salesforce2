package pro.civitaspo.embulk.output.salesforce2.auth;

public abstract class AbstractBearerTokenProvider implements BearerTokenProvider {
    protected String bearerToken = null;

    @Override
    public String getOrNewBearerToken() {
        if (bearerToken == null) {
            refreshBearerToken();
        }
        return bearerToken;
    }
}
