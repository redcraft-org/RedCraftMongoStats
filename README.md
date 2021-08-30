
# RedCraftMongoStats

A SpigotMC plugin to synchronize vanilla player stats with a MongoDB server.

It's been built to sync stats from the original json advancement and stats files to a MongoDB server, using the player UUID as the `_id` key:

<img width="1701" alt="Record example" src="https://user-images.githubusercontent.com/2182934/131265080-687fd1d3-f3a8-405b-b0ed-41119f963955.png">

## Downloads

You can download the latest version from the [release page](https://github.com/redcraft-org/RedCraftMongoStats/releases).

## How to compile

Make sure you have a JDK for Java 16 and Maven, and run `maven -B package` to compile it. You'll find the `.jar` in the `target` directory.

## Contributing

You are free to suggest changes by opening an issue ticket.

You can also open PRs, but remember to bump the version in `pom.xml` and `plugin.yml` before opening a pull request.
