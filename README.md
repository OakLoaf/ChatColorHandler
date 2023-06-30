# ChatColorHandler

ChatColorHandler allows you to easily add Hex Colour support to your Minecraft plugins.
This Library works with all versions of Spigot and also adds optional MiniMessage support. Please note that MiniMessage support only offers colour, text decoration and gradient support. This is to decrease the file size of projects. If you would like to properly use MiniMessage please look here: https://docs.adventure.kyori.net/minimessage/index.html 
<br></br>
## Using ChatColorHandler
The hex color format added by ChatColorHandler is `&#rrggbb` and does not add gradient support.
To add gradient and MiniMessage color support run `ChatColorHandler.enableMiniMessage(true)` in your `onEnable()`

### Adding Hex Colors to Messages
There are 2 main methods that you will be wanting to use in ChatColorHandler `.sendMessage` and `.translateAlternateColorCodes`.

Moving over from Spigot, there are 2 ways you can change your spigot code over to ChatColorHandler code:

**Spigot:**
```java
player.sendMessage(ChatColor.translateAlternateColorCodes('&', "&cThis is an example message"));
```
**ChatColorHandler:**
```java
// Method 1
player.sendMessage(ChatColorHandler.translateAlternateColorCodes("&cThis is an example message");

// Method 2
ChatColorHandler.sendMessage(player, "&cThis is an example message");
```
### Adding Hex Colors to the rest of Minecraft
With ChatColorHandler you are able to change gui titles, item names, item lore, etc. All by parsing your text through `ChatColorHandler.translateAlternateColorCodes("Test string")`

<br></br>
## Add ChatColorHandler to your project:
You can simply add ChatColorHandler to your project by adding the below into your pom.xml


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
        <version>v1.3</version>
    </dependency>
</dependencies>
```
