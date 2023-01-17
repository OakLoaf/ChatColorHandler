/*
 * This file is part of adventure, licensed under the MIT License.
 *
 * Copyright (c) 2017-2022 KyoriPowered
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in all
 * copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 * SOFTWARE.
 */

package me.dave.chatcolorhandler.utils;

import net.kyori.adventure.builder.AbstractBuilder;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.TextComponent;
import net.kyori.adventure.text.event.ClickEvent;
import net.kyori.adventure.text.format.Style;
import net.kyori.adventure.text.serializer.ComponentSerializer;
import net.kyori.adventure.util.Buildable;
import net.kyori.adventure.util.PlatformAPI;
import org.jetbrains.annotations.ApiStatus;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.function.Consumer;
import java.util.regex.Pattern;

public interface LegacyComponentSerializer extends ComponentSerializer<Component, TextComponent, String>, Buildable<LegacyComponentSerializer, LegacyComponentSerializer.Builder> {

    /**
     * Creates a new {@link LegacyComponentSerializer.Builder}.
     *
     * @return the builder
     * @since 4.0.0
     */
    static @NotNull LegacyComponentSerializer.Builder builder() {
        return new LegacyComponentSerializerImpl.BuilderImpl();
    }

    /**
     * The legacy character used by Minecraft. ('§')
     *
     * @since 4.0.0
     */
    char SECTION_CHAR = '§';

    /**
     * The legacy character frequently used by configurations and commands. ('&amp;')
     *
     * @since 4.0.0
     */
    char AMPERSAND_CHAR = '&';

    /**
     * The legacy character used to prefix hex colors. ('#')
     *
     * @since 4.0.0
     */
    char HEX_CHAR = '#';

    /**
     * Deserialize a component from a legacy {@link String}.
     *
     * @param input the input
     * @return the component
     */
    @Override
    @NotNull TextComponent deserialize(final @NotNull String input);

    /**
     * Serializes a component into a legacy {@link String}.
     *
     * @param component the component
     * @return the string
     */
    @Override
    @NotNull String serialize(final @NotNull Component component);

    /**
     * A builder for {@link LegacyComponentSerializer}.
     *
     * @since 4.0.0
     */
    interface Builder extends AbstractBuilder<LegacyComponentSerializer>, Buildable.Builder<LegacyComponentSerializer> {
        /**
         * Sets that the serializer should extract URLs into {@link ClickEvent}s
         * when deserializing.
         *
         * @param pattern the url pattern
         * @param style the style to apply to indicate that text is a link
         * @return this builder
         * @since 4.2.0
         */
        @NotNull LegacyComponentSerializer.Builder extractUrls(final @NotNull Pattern pattern, final @Nullable Style style);

        /**
         * Builds the serializer.
         *
         * @return the built serializer
         */
        @Override
        @NotNull LegacyComponentSerializer build();
    }

    /**
     * A {@link LegacyComponentSerializer} service provider.
     *
     * @since 4.8.0
     */
    @ApiStatus.Internal
    @PlatformAPI
    interface Provider {
        /**
         * Provides a {@link LegacyComponentSerializer} using {@link #AMPERSAND_CHAR}.
         *
         * @return a {@link LegacyComponentSerializer}
         * @since 4.8.0
         */
        @ApiStatus.Internal
        @PlatformAPI
        @NotNull LegacyComponentSerializer legacyAmpersand();

        /**
         * Provides a {@link LegacyComponentSerializer} using {@link #SECTION_CHAR}.
         *
         * @return a {@link LegacyComponentSerializer}
         * @since 4.8.0
         */
        @ApiStatus.Internal
        @PlatformAPI
        @NotNull LegacyComponentSerializer legacySection();

        /**
         * Completes the building process of {@link LegacyComponentSerializer.Builder}.
         *
         * @return a {@link Consumer}
         * @since 4.8.0
         */
        @ApiStatus.Internal
        @PlatformAPI
        @NotNull Consumer<LegacyComponentSerializer.Builder> legacy();
    }
}
