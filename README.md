# embulk-output-salesforce2
[![main](https://github.com/civitaspo/embulk-output-salesforce2/actions/workflows/main.yml/badge.svg)](https://github.com/civitaspo/embulk-output-salesforce2/actions/workflows/main.yml) [![CodeQL](https://github.com/civitaspo/embulk-output-salesforce2/actions/workflows/codeql-analysis.yml/badge.svg)](https://github.com/civitaspo/embulk-output-salesforce2/actions/workflows/codeql-analysis.yml)

## Overview

[Embulk](https://github.com/embulk/embulk/) output plugin to load/insert data into [Salesforce](https://www.salesforce.com/) via [Bulk API 2.0](https://developer.salesforce.com/docs/atlas.en-us.api_asynch.meta/api_asynch/bulk_api_2_0.htm).

* **Plugin type**: output
* **Load all or nothing**: yes
* **Resume supported**: no
* **Cleanup supported**: yes

### NOT IMPLEMENTED

* Salesforce supports lots of authentication methods, but this plugin only supports [OAuth 2.0 Client Credentials Flow](https://help.salesforce.com/s/articleView?id=sf.remoteaccess_oauth_client_credentials_flow.htm).

## Configuration

* **host**: Salesforce host name (string, required)
* **auth_method**: Salesforce authentication method (string, required, allowed values: `oauth2_client_credentials`, `bearer_token`)
* **client_id**: The client id for [Salesforce OAuth 2.0 Client Credentials Flow for Server-to-Server Integration](https://help.salesforce.com/s/articleView?id=sf.remoteaccess_oauth_client_credentials_flow.htm). The client id is called as the "consumer key" in Salesforce. To access the consumer key, from the App Manager, find the connected app and select View from the dropdown. Then click Manage Consumer Details. You're sometimes prompted to verify your identity before you can view the consumer key. (string, required when auth_method is **oauth2_client_credentials**)
* **client_secret**: The client secret for [Salesforce OAuth 2.0 Client Credentials Flow for Server-to-Server Integration](https://help.salesforce.com/s/articleView?id=sf.remoteaccess_oauth_client_credentials_flow.htm). The client secret is called as the "consumer secret" in Salesforce. To access the consumer secret, from the App Manager, find the connected app and select View from the dropdown. Then click Manage Consumer Details. You're sometimes prompted to verify your identity before you can view the consumer secret. (string, required when auth_method is **oauth2_client_credentials**)
* **bearer_token**: The bearer token to use for authentication. This token is obtained from Salesforce and is used to authenticate API requests. (string, required when auth_method is **bearer_token**)
* **api_version**: Salesforce API version. You can find the version according to the document: [Find Salesforce Edition and API version](https://help.salesforce.com/s/articleView?id=000386929&type=1).  (string, default: `v59.0`)
* **call_options_header**: The header value for when calling the APIs. This value is used as the value of the `Sforce-Call-Options` header. See the document: [Call Options Header](https://developer.salesforce.com/docs/atlas.en-us.246.0.api_rest.meta/api_rest/headers_calloptions.htm).  (string, optional)
* **assignment_rule_id**: The ID of an assignment rule to run for a Case or a Lead. The assignment rule can be active or inactive. The ID can be retrieved by using the Lightning Platform SOAP API or the Lightning Platform REST API to query the AssignmentRule object. This property is available in API version 49.0 and later. See the document: [AssignmentRule](https://developer.salesforce.com/docs/atlas.en-us.246.0.object_reference.meta/object_reference/sforce_api_objects_assignmentrule.htm). (string, optional)
* **upsert_key**: The upsert field name in the object being updated. Only needed for upsert operations. Field values must also exist in CSV job data. This value is used as `externalIdFieldName` for the API request: [Create Job](https://developer.salesforce.com/docs/atlas.en-us.api_asynch.meta/api_asynch/create_job.htm). (string, required when operation is **upsert**)
* **operation**: The operation for the job. The default is **upsert**. (string, default: `upsert`, allowed values: `insert`, `update`, `upsert`)
* **object**: The object type for the job. (string, required)
* **maximum_retries**: The maximum number of retries for the HTTP requests. (integer, default: `7`)
* **initial_retry_interval_millis**: The initial retry interval in milliseconds for the HTTP requests. (integer, default: `1000`)
* **maximum_retry_interval_millis**: The maximum retry interval in milliseconds for the HTTP requests. (integer, default: `60000`)
* **default_timezone**: Default timezone. (string, default: `"UTC"`)
* **default_timestamp_format**: Default timestamp format. (string, default: `"%Y-%m-%d %H:%M:%S %z"`)
* **default_date**: Default date. (string, default: `"1970-01-01"`)

### Example

```yaml
out:
  type: salesforce2
  auth_method: oauth2_client_credentials
  client_id: xxxxxxxxxxxxxxxxxxxxxx
  client_secret: xxxxxxxxxxxxxxxxxxxxxx
  host: civitaspo.my.salesforce.com
  api_version: v59.0
  upsert_key: Id
  object: Account
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
