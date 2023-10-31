# ChatColorHandler
![Version Number](https://img.shields.io/jitpack/version/com.github.cooldcb/ChatColorHandler?label=Version&color=4EC921)

ChatColorHandler allows you to easily add Hex Colour and MiniMessage support to your Minecraft plugins.
This Library works with all versions of Spigot and also adds [MiniMessage](https://docs.advntr.dev/minimessage/format.html) support for servers running [Paper](https://papermc.io/downloads/paper) (or forks).

## Using ChatColorHandler
The hex color format added by ChatColorHandler is `&#rrggbb` and does not add gradient support.
ChatColorHandler will automatically setup upon the first use of the library

### Adding Hex Colors to Messages
These are the main methods that you will be wanting to use in ChatColorHandler:

- `sendMessage` - Sends a message to one or more players, this supports using MiniMessage's `click` and `hover` tags
- `broadcastMessage` - Sends a message to all online players, this supports using MiniMessage's `click` and `hover` tags
- `sendActionBarMessage` - Sends an action bar message to one or more players
- `translateAlternateColorCodes` - Translates a string

### Compatibility
ChatColorHandler provides built in support for:

- PlaceholderAPI - *requires [PlaceholderAPI](https://www.spigotmc.org/resources/placeholderapi.6245/) plugin*
- MiniMessage - *requires server to be running PaperMC (or forks)*

All support built into ChatColorHandler will automatically be applied to all parsed strings, you can define specific parsers to use in `#translateAlternateColorCodes`

eg.
```java
ChatColorHandler.translateAlternateColorCodes("&#aaee99Example Message", List.of(PlaceholderAPIParser.class, MiniMessageParser.class))
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
ChatColorHandler.translateAlternateColorCodes("Test String")
```

<br>

## Add ChatColorHandler to your project:
You can simply add ChatColorHandler to your project by adding the below into your pom.xml

![Version Number](https://img.shields.io/jitpack/version/com.github.cooldcb/ChatColorHandler?label=Version&color=4EC921)

**Javadocs:** https://cooldcb.github.io/ChatColorHandler/me/dave/chatcolorhandler/ChatColorHandler.html

<details open>
<summary>Maven</summary>

**Repository:**
```xml
<repositories>
    <repository>
        <id>jitpack.io</id>
        <url>https://jitpack.io</url>
    </repository>
</repositories>
```
**Artifact:**
```xml
<dependencies>
    <dependency>
        <groupId>com.github.CoolDCB</groupId>
        <artifactId>ChatColorHandler</artifactId>
        <version>v2.1.3</version>
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
    maven { url = "https://jitpack.io" }
}
```
**Artifact:**
```gradle
dependencies {
    compileOnly "com.github.CoolDCB:ChatColorHandler:v2.1.0"
}
```
</details>
