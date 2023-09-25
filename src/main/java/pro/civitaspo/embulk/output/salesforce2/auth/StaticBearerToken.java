package pro.civitaspo.embulk.output.salesforce2.auth;

public class StaticBearerToken extends AbstractBearerTokenProvider {
    public StaticBearerToken(String bearerToken) {
        this.bearerToken = bearerToken;
    }

    @Override
    public void refreshBearerToken() {}
}
