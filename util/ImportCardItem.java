    @Override
    public void appendHoverText(ItemStack stack, TooltipContext context, List<Component> tooltipComponents, TooltipFlag tooltipFlag) {
        super.appendHoverText(stack, context, tooltipComponents, tooltipFlag);
        
        var config = stack.getOrDefault(ae2helpers.IMPORT_CARD_CONFIG.get(), ImportCardConfig.DEFAULT);
        
        tooltipComponents.add(Component.translatable("ae2helpers.importcard.tooltip.mode").withStyle(ChatFormatting.GRAY)
                                .append(config.resultsOnly()
                                          ? Component.translatable("ae2helpers.importcard.tooltip.crafting_results").withStyle(ChatFormatting.GOLD)
                                          : Component.translatable("ae2helpers.importcard.tooltip.everything").withStyle(ChatFormatting.RED)));
        
        tooltipComponents.add(Component.translatable("ae2helpers.importcard.tooltip.sync").withStyle(ChatFormatting.GRAY)
                                .append(config.syncToGrid()
                                          ? Component.translatable("ae2helpers.importcard.tooltip.enabled").withStyle(ChatFormatting.GREEN)
                                          : Component.translatable("ae2helpers.importcard.tooltip.disabled").withStyle(ChatFormatting.RED)));
        
        var dir = config.overriddenDirection();
        var sideText = (dir == null)
                         ? Component.translatable("ae2helpers.importcard.direction.auto")
                         : Component.literal(dir.getName().substring(0, 1).toUpperCase() + dir.getName().substring(1));
        
        tooltipComponents.add(Component.translatable("ae2helpers.importcard.tooltip.side").withStyle(ChatFormatting.GRAY)
                                .append(sideText.withStyle(ChatFormatting.AQUA)));
        
        tooltipComponents.add(Component.translatable("ae2helpers.importcard.tooltip.hint").withStyle(ChatFormatting.DARK_GRAY, ChatFormatting.ITALIC));
    }
}
