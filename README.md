# embulk-output-salesforce2
[![main](https://github.com/civitaspo/embulk-output-salesforce2/actions/workflows/main.yml/badge.svg)](https://github.com/civitaspo/embulk-output-salesforce2/actions/workflows/main.yml) [![CodeQL](https://github.com/civitaspo/embulk-output-salesforce2/actions/workflows/codeql-analysis.yml/badge.svg)](https://github.com/civitaspo/embulk-output-salesforce2/actions/workflows/codeql-analysis.yml)

## Overview

[Embulk](https://github.com/embulk/embulk/) output plugin to load/insert data into [Salesforce](https://www.salesforce.com/) via [Bulk API 2.0](https://developer.salesforce.com/docs/atlas.en-us.api_asynch.meta/api_asynch/bulk_api_2_0.htm).

* **Plugin type**: output
* **Load all or nothing**: yes
* **Resume supported**: no
* **Cleanup supported**: yes

### NOT IMPLEMENTED

* Salesforce supports lots of authentication methods, but this plugin supports only [OAuth 2.0 Client Credentials Flow](https://help.salesforce.com/s/articleView?id=sf.remoteaccess_oauth_client_credentials_flow.htm).

## Configuration

* **host**: 
* **auth_method**: 


operation: insert, update, upsert
externalIdFieldName: primary key?

https://developer.salesforce.com/docs/atlas.en-us.api_asynch.meta/api_asynch/create_job.htm

### Example

```yaml
out:
  type: salesforce2
  auth_method: client_credentials
  client_id: xxxxxxxxxxxxxxxxxxxxxx
  client_secret: xxxxxxxxxxxxxxxxxxxxxx
  host: layerx--dpf.sandbox.my.salesforce.com
  api_version: v59.0
```


## Development

### Run example

```
$ ./gradlew gem
$ embulk run -X page_size=1 -X min_output_tasks=1 -Ibuild/gemContents/lib -l trace example/config.yml
```

### Run test

```
$ ./gradlew test
```

### Update dependencies locks

```shell
$ ./gradlew dependencies --write-locks
```

### Run the formatter

```shell
## Just check the format violations
$ ./gradlew spotlessCheck

## Fix the all format violations
$ ./gradlew spotlessApply
```

### Release gem

A new tag is pushed, then a new gem will be released. See [the Github Action CI Setting](./.github/workflows/main.yml).

## ChangeLog

[CHANGELOG.md](CHANGELOG.md)

## License

[MIT LICENSE](./LICENSE.txt)
