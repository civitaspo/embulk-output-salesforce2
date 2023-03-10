Embulk::JavaPlugin.register_output(
  "salesforce2", "org.embulk.output.salesforce2.Salesforce2OutputPlugin",
  File.expand_path('../../../../classpath', __FILE__))
