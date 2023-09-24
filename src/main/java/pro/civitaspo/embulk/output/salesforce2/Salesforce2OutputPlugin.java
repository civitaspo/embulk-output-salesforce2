package pro.civitaspo.embulk.output.salesforce2;

import java.util.List;
import org.embulk.config.ConfigDiff;
import org.embulk.config.ConfigSource;
import org.embulk.config.TaskReport;
import org.embulk.config.TaskSource;
import org.embulk.spi.OutputPlugin;
import org.embulk.spi.Schema;
import org.embulk.spi.TransactionalPageOutput;
import pro.civitaspo.embulk.output.salesforce2.config.ConfigLoader;
import pro.civitaspo.embulk.output.salesforce2.config.PluginTask;

public class Salesforce2OutputPlugin implements OutputPlugin {

    @Override
    public ConfigDiff transaction(
            ConfigSource config, Schema schema, int taskCount, OutputPlugin.Control control) {
        final PluginTask task = ConfigLoader.load(config);
        final List<TaskReport> taskReports = control.run(task.toTaskSource());
        return ConfigLoader.getConfigMapperFactory().newConfigDiff();
    }

    @Override
    public ConfigDiff resume(
            TaskSource taskSource, Schema schema, int taskCount, OutputPlugin.Control control) {
        throw new UnsupportedOperationException(
                "salesforce2 output plugin does not support resuming");
    }

    @Override
    public void cleanup(
            TaskSource taskSource,
            Schema schema,
            int taskCount,
            List<TaskReport> successTaskReports) {}

    @Override
    public TransactionalPageOutput open(TaskSource taskSource, Schema schema, int taskIndex) {
        final PluginTask task = ConfigLoader.load(taskSource);
        // Write your code here :)
        throw new UnsupportedOperationException(
                "Salesforce2OutputPlugin.run method is not implemented yet");
    }
}
