package com.garfiec.networkchat.client.ui;

public class UI_Strings {
    public static final String GUI_TITLE = "Chat Client";

    public static final String USER_LIST_LABEL_TEXT = "Connected Users";

    // TODO: How to use dialog
    public static final String HOW_TO_USE_TEXT =
            "<html>" +
                "<h2>Connecting to a Server</h2>" +
                "<h3>Configuration</h3>" +
                "<ol>" +
                    "<li>Go to &quot;File &gt; Connect to Server&quot; and fill in the server details box</li>" +
                    "<li>If you would like to configure additional information used for the connection, you may do so now in the &quot;Advanced Settings&quot; box</li>" +
                    "<li>Once you finish, you may click &quot;Connect to Server&quot;</li>" +
                "</ol>" +
                "</h3>Sending a Message</h3>" +
                "<ol>" +
                    "<li>Once you are connected to the server, you may send a message to a connected user</li>" +
                    "<li>Select a target user to send a message to</li>" +
                    "<li>Navigate to the bottom right text box and enter your message</li>" +
                    "<li>Press {Enter} when you are ready to send your message</li>" +
                "</ol>" +
            "</html>";
    public static final String ABOUT_TEXT =
            "<html>Garfie Chiu: gchiu2 <br>" +
            "Andriy Suden: asuden2 <br>" +
            "Ammar Subei: asubei?</html>";

    public static final String CIPHER_USE_NOTE = "Use p, q = 0 to automatically set primes or set p, q to primes such that p*q>128^4";
}
