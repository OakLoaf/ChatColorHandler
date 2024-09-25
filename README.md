# ChatColorHandler
![Version Number](https://repo.lushplugins.org/api/badge/latest/releases/org/lushplugins/ChatColorHandler?color=40c14a&name=Maven)

ChatColorHandler allows you to easily add Hex Colour and MiniMessage support to your Minecraft plugins.
This Library works with all versions of Spigot and also adds [MiniMessage](https://docs.advntr.dev/minimessage/format.html) support for servers running [Paper](https://papermc.io/downloads/paper) (or forks).

## Using ChatColorHandler
The hex color formats added by ChatColorHandler are `&#rrggbb` and `#rrggbb` and does not add gradient support.
ChatColorHandler will automatically setup upon the first use of the library

### Adding Hex Colors to Messages
These are the main methods that you will be wanting to use in ChatColorHandler:

- `translate` - Translates a string
- `sendMessage` - Sends a message to one or more players
- `broadcastMessage` - Sends a message to all online players
- `sendActionBarMessage` - Sends an action bar message to one or more players

### Compatibility
ChatColorHandler provides built in support for:

- PlaceholderAPI - *requires [PlaceholderAPI](https://www.spigotmc.org/resources/placeholderapi.6245/) plugin*
- MiniPlaceholders - *requires [MiniPlaceholders](https://modrinth.com/plugin/miniplaceholders) plugin*
- MiniMessage - *requires server to be running PaperMC (or forks)*

All support built into ChatColorHandler will automatically be applied to all parsed strings, you can also define specific parsers to be used in `#translate`. The order in which you enter the parsers will be the same order that they are parsed.

eg.
```java
ChatColorHandler.translate("&#aaee99Example Message %server_name%", List.of(HexParser.INSTANCE, PlacecholderAPIParser.INSTANCE))
```

<br>

### Moving over from Spigot-API
Sending messages with ChatColorHandler is as easy as doing:

```java
ChatColorHandler.sendMessage(player, "&cThis is an example message");
```

### Adding Hex Colors to the rest of Minecraft
With ChatColorHandler you are able to change gui titles, item names, item lore, etc. All by parsing your text through ChatColorHandler:

```java
ChatColorHandler.translate("&#bbff33Inventory Title");
```

<br>

## Add ChatColorHandler to your project:
You can simply add ChatColorHandler to your project by adding the below into your maven or gradle build file!

![Version Number](https://repo.lushplugins.org/api/badge/latest/releases/org/lushplugins/ChatColorHandler?color=40c14a&name=Maven)

**Javadocs:** https://oakloaf.github.io/ChatColorHandler/org/lushplugins/chatcolorhandler/ChatColorHandler.html

<details open>
<summary>Maven</summary>

**Repository:**
```xml
<repositories>
    <repository>
        <id>lushplugins.org</id>
        <url>https://repo.lushplugins.org/releases/</url>
    </repository>
</repositories>
```
**Artifact:**
```xml
<dependencies>
    <dependency>
        <groupId>org.lushplugins</groupId>
        <artifactId>ChatColorHandler</artifactId>
        <version>4.0.0</version>
    </dependency>
</dependencies>
```
</details>

<details>
<summary>Gradle</summary>

**Repository:**
```gradle
repositories {
    mavenCentral()
    maven { url = "https://repo.lushplugins.org/releases/" }
}
```
**Artifact:**
```gradle
dependencies {
    compileOnly "org.lushplugins:ChatColorHandler:4.0.0"
}
```
</details>
