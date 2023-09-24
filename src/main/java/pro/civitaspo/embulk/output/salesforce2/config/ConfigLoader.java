package pro.civitaspo.embulk.output.salesforce2.config;

import org.embulk.config.ConfigSource;
import org.embulk.config.TaskSource;
import org.embulk.util.config.ConfigMapper;
import org.embulk.util.config.ConfigMapperFactory;
import org.embulk.util.config.TaskMapper;
import pro.civitaspo.embulk.output.salesforce2.config.validation.BeanValidator;

public class ConfigLoader {
    private static final ConfigMapperFactory CONFIG_MAPPER_FACTORY =
            ConfigMapperFactory.builder().addDefaultModules().build();

    public static ConfigMapperFactory getConfigMapperFactory() {
        return CONFIG_MAPPER_FACTORY;
    }

    public static PluginTask load(ConfigSource configSource) {
        return load(getConfigMapperFactory(), configSource);
    }

    public static PluginTask load(TaskSource taskSource) {
        return load(getConfigMapperFactory(), taskSource);
    }

    public static PluginTask load(ConfigMapperFactory factory, TaskSource taskSource) {
        return load(factory.createTaskMapper(), taskSource);
    }

    public static PluginTask load(TaskMapper mapper, TaskSource taskSource) {
        return initialize(mapper.map(taskSource, PluginTask.class));
    }

    public static PluginTask load(ConfigMapperFactory factory, ConfigSource configSource) {
        return load(factory.createConfigMapper(), configSource);
    }

    public static PluginTask load(ConfigMapper magger, ConfigSource configSource) {
        return initialize(magger.map(configSource, PluginTask.class));
    }

    public static PluginTask initialize(PluginTask task) {
        validate(task);
        PluginTask.ConditionalRequiredOptionsValidator.validate(task);
        return task;
    }

    public static void validate(PluginTask task) {
        BeanValidator.validate(task);
    }
}
