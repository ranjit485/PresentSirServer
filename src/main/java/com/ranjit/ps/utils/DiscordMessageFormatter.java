package com.ranjit.ps.utils;

import com.ranjit.ps.model.User;

public class DiscordMessageFormatter {

    private DiscordMessageFormatter() {
        // Private constructor to prevent instantiation
    }

    public static String formatUserJoinedMessage(User user) {
        String userBusName = user.getBus().getRouteName();

        return "**" + user.getName() + " has joined PresentSir!**\n\n" +
               "**Details:**\n" +
               "- **Bus ID:** " + userBusName + "\n" +
               "- **Email:** " + user.getEmail() + "\n" +
               "- **Contact:** " + user.getContact();
    }


}
