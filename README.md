<div align="center">

[![](https://jitpack.io/v/VirtualPlaygroundDE/VirtualAPI.svg)](https://jitpack.io/#VirtualPlaygroundDE/VirtualAPI)
[![CodeFactor](https://www.codefactor.io/repository/github/virtualplaygroundde/virtualapi/badge)](https://www.codefactor.io/repository/github/virtualplaygroundde/virtualapi)

<div>
    <h1>VirtualAPI</h1>
</div>

</div>

> :warning: Dependencies: [CommandAPI](https://modrinth.com/plugin/commandapi), [NBTAPI](https://modrinth.com/plugin/nbtapi)

## Features
- Create Configuration Files
- Create Language Files ([MiniMessage](https://docs.advntr.dev/minimessage/format.html) Format)
- Create Custom Guis and Villager Trades
- Built-in Regions
- Custom Items and Recipes
- Global Economy System
- MySQL-Support

## Implementation

### Maven
```xml
<repository>
    <id>jitpack.io</id>
    <url>https://jitpack.io</url>
</repository>
```
```xml
<dependency>
    <groupId>com.github.VirtualPlaygroundDE</groupId>
    <artifactId>VirtualAPI</artifactId>
    <version>{VERSION}</version>
    <scope>provided</scope>
</dependency>
```

### Gradle
```gradle
maven { url 'https://jitpack.io' }
```
```gradle
compileOnly 'com.github.VirtualPlaygroundDE:VirtualAPI:{VERSION}'
```
