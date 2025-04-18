package com.mars.cithotfix;

import com.google.common.collect.Lists;
import com.mars.deimos.config.DeimosConfig;

import java.util.List;


public class Config extends DeimosConfig {
    @Entry public static List<String> possible_roots = Lists.newArrayList("mcpatcher", "optifine", "citresewn");
}
