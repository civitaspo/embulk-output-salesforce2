package pro.civitaspo.embulk.output.salesforce2.config;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.PositiveOrZero;
import java.util.Optional;
import org.embulk.config.ConfigException;
import org.embulk.util.config.Config;
import org.embulk.util.config.ConfigDefault;
import org.embulk.util.config.Task;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import pro.civitaspo.embulk.output.salesforce2.config.validation.annotation.NotBlankIfPresent;
import pro.civitaspo.embulk.output.salesforce2.config.validation.annotation.ValidZoneId;
import pro.civitaspo.embulk.output.salesforce2.config.validation.annotation.ValueOfEnum;

public interface PluginTask extends Task {
    public static final class ConditionalRequiredOptionsValidator {
        public static void validate(PluginTask task) {
            AuthMethod.of(task.getAuthMethod()).validateTask(task);
            Operation.of(task.getOperation()).validateTask(task);
        }
    }

    public static enum AuthMethod {
        oauth2_client_credentials,
        bearer_token;

        private static final Logger logger = LoggerFactory.getLogger(AuthMethod.class);

        public static AuthMethod of(String value) {
            return AuthMethod.valueOf(value);
        }

        public void validateTask(PluginTask task) {
            switch (this) {
                case oauth2_client_credentials:
                    if (!task.getClientId().isPresent())
                        throw new ConfigException(
                                "'client_id' is required for auth_method: " + this.name());
                    if (!task.getClientSecret().isPresent())
                        throw new ConfigException(
                                "'client_secret' is required for auth_method: " + this.name());
                    if (task.getBearerToken().isPresent())
                        logger.warn(
                                "'bearer_token' is overwritten for auth_method: " + this.name());
                    break;
                case bearer_token:
                    if (!task.getBearerToken().isPresent())
                        throw new ConfigException(
                                "'bearer_token' is required for auth_method: " + this.name());
                    if (task.getClientId().isPresent())
                        logger.warn("'client_id' is not used for auth_method: " + this.name());
                    if (task.getClientSecret().isPresent())
                        logger.warn("'client_secret' is not used for auth_method: " + this.name());
                    break;
                default:
                    throw new ConfigException("Unknown auth_method: " + this.name());
            }
        }
    }

    public static enum Operation {
        insert,
        update,
        upsert;

        private static final Logger logger = LoggerFactory.getLogger(Operation.class);

        public static Operation of(String value) {
            return Operation.valueOf(value);
        }

        public void validateTask(PluginTask task) {
            switch (this) {
                case insert:
                    if (task.getUpsertKey().isPresent())
                        logger.warn("'upsert_key' is not used for operation: " + this.name());
                    break;
                case update:
                    if (task.getUpsertKey().isPresent())
                        logger.warn("'upsert_key' is not used for operation: " + this.name());
                    break;
                case upsert:
                    if (!task.getUpsertKey().isPresent())
                        throw new ConfigException(
                                "'upsert_key' is required for operation: " + this.name());
                    break;
                default:
                    throw new ConfigException("Unknown operation: " + this.name());
            }
        }
    }

    @Config("host")
    @NotBlank
    public String getHost();

    @Config("auth_method")
    @NotBlank
    @ValueOfEnum(enumClass = AuthMethod.class)
    public String getAuthMethod();

    @Config("client_id")
    @ConfigDefault("null")
    @NotBlankIfPresent
    public Optional<String> getClientId();

    @Config("client_secret")
    @ConfigDefault("null")
    @NotBlankIfPresent
    public Optional<String> getClientSecret();

    @Config("bearer_token")
    @ConfigDefault("null")
    @NotBlankIfPresent
    public Optional<String> getBearerToken();

    public void setBearerToken(Optional<String> bearerToken);

    @Config("api_version")
    @ConfigDefault("\"v59.0\"")
    @NotBlank
    @Pattern(regexp = "^v[0-9]+\\.[0-9]+$")
    public String getApiVersion();

    @Config("call_options_header")
    @ConfigDefault("null")
    @NotBlankIfPresent
    public Optional<String> getCallOptionsHeader();

    @Config("assignment_rule_id")
    @ConfigDefault("null")
    @NotBlankIfPresent
    public Optional<String> getAssignmentRuleId();

    @Config("upsert_key")
    @ConfigDefault("null")
    @NotBlankIfPresent
    public Optional<String> getUpsertKey();

    @Config("operation")
    @ConfigDefault("\"upsert\"")
    @NotBlank
    @ValueOfEnum(enumClass = Operation.class)
    public String getOperation();

    @Config("object")
    @NotBlank
    public String getObject();

    @Config("maximum_retries")
    @ConfigDefault("7")
    @PositiveOrZero
    public int getMaximumRetries();

    @Config("initial_retry_interval_millis")
    @ConfigDefault("1000")
    @PositiveOrZero
    public int getInitialRetryIntervalMillis();

    @Config("maximum_retry_interval_millis")
    @ConfigDefault("60000")
    @PositiveOrZero
    public int getMaximumRetryIntervalMillis();

    @Config("default_timezone")
    @ConfigDefault("\"UTC\"")
    @NotBlank
    @ValidZoneId
    public String getDefaultTimeZoneId();

    @Config("default_timestamp_format")
    @ConfigDefault("\"%Y-%m-%d %H:%M:%S.%N %z\"")
    @NotNull
    public String getDefaultTimestampFormat();

    @Config("default_date")
    @ConfigDefault("\"1970-01-01\"")
    @Pattern(regexp = "^\\d{4}-\\d{2}-\\d{2}$")
    @NotBlank
    public String getDefaultDate();
}
