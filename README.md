# ChatColorHandler
![Version Number](https://repo.smrt-1.com/api/badge/latest/releases/me/dave/ChatColorHandler?color=40c14a&name=Maven)

ChatColorHandler allows you to easily add Hex Colour and MiniMessage support to your Minecraft plugins.
This Library works with all versions of Spigot and also adds [MiniMessage](https://docs.advntr.dev/minimessage/format.html) support for servers running [Paper](https://papermc.io/downloads/paper) (or forks).

## Using ChatColorHandler
The hex color format added by ChatColorHandler is `&#rrggbb` and does not add gradient support.
ChatColorHandler will automatically setup upon the first use of the library

### Adding Hex Colors to Messages
These are the main methods that you will be wanting to use in ChatColorHandler:

- `sendMessage` - Sends a message to one or more players
- `broadcastMessage` - Sends a message to all online players
- `sendActionBarMessage` - Sends an action bar message to one or more players
- `translate` - Translates a string

### Compatibility
ChatColorHandler provides built in support for:

- PlaceholderAPI - *requires [PlaceholderAPI](https://www.spigotmc.org/resources/placeholderapi.6245/) plugin*
- MiniPlaceholders - *requires [MiniPlaceholders](https://modrinth.com/plugin/miniplaceholders) plugin*
- MiniMessage - *requires server to be running PaperMC (or forks)*

All support built into ChatColorHandler will automatically be applied to all parsed strings, you can define specific parsers to use in `#translate`

eg.
```java
ChatColorHandler.translate("&#aaee99Example Message", List.of(PlaceholderAPIParser.class, MiniMessageParser.class))
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
ChatColorHandler.translate("Test String")
```

<br>

## Add ChatColorHandler to your project:
You can simply add ChatColorHandler to your project by adding the below into your pom.xml

![Version Number]([https://img.shields.io/jitpack/version/com.github.cooldcb/ChatColorHandler?label=Maven&color=4EC921](https://repo.smrt-1.com/api/badge/latest/releases/me/dave/ChatColorHandler?color=40c14a&name=Maven))

**Javadocs:** https://cooldcb.github.io/ChatColorHandler/me/dave/chatcolorhandler/ChatColorHandler.html

<details open>
<summary>Maven</summary>

**Repository:**
```xml
<repositories>
    <repository>
        <id>smrt-1.com</id>
        <url>https://repo.smrt-1.com/releases/</url>
    </repository>
</repositories>
```
**Artifact:**
```xml
<dependencies>
    <dependency>
        <groupId>me.dave</groupId>
        <artifactId>ChatColorHandler</artifactId>
        <version>v2.5.3</version>
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
    maven { url = "https://repo.smrt-1.com" }
}
```
**Artifact:**
```gradle
dependencies {
    compileOnly "me.dave:ChatColorHandler:v2.5.3"
}
```
</details>
