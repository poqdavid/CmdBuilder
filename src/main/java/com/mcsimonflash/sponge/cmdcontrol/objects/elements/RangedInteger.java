package com.mcsimonflash.sponge.cmdcontrol.objects.elements;

import com.google.common.collect.Lists;
import com.google.common.collect.Range;
import org.spongepowered.api.command.CommandSource;
import org.spongepowered.api.command.args.ArgumentParseException;
import org.spongepowered.api.command.args.CommandArgs;
import org.spongepowered.api.command.args.CommandContext;
import org.spongepowered.api.command.args.CommandElement;
import org.spongepowered.api.text.Text;

import javax.annotation.Nullable;
import java.util.List;

public class RangedInteger extends CommandElement {

    private Range<Integer> range;

    public RangedInteger(@Nullable Text key, Range<Integer> range) {
        super(key);
        this.range = range;
    }

    @Nullable
    @Override
    protected Object parseValue(CommandSource source, CommandArgs args) throws ArgumentParseException {
        String arg = args.next();
        try {
            int num = Integer.parseInt(arg);
            if (range.contains(num)) {
                return num;
            }
            throw args.createError(Text.of("Integer \"" + num + "\" is not within range \"" + range + "\"."));
        } catch (NumberFormatException ignored) {
            throw args.createError(Text.of("Argument \"" + arg + "\" is not an Integer."));
        }
    }

    @Override
    public List<String> complete(CommandSource src, CommandArgs args, CommandContext context) {
        return Lists.newArrayList();
    }

}