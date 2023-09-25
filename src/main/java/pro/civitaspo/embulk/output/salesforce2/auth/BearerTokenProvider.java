package pro.civitaspo.embulk.output.salesforce2.auth;

public interface BearerTokenProvider {
    void refreshBearerToken();

    String getOrNewBearerToken();
}
