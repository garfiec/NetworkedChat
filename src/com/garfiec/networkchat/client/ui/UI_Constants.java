package com.garfiec.networkchat.client.ui;

import javax.swing.border.EmptyBorder;
import java.awt.*;

public class UI_Constants {
    public static final int WIDTH   = 900;
    public static final int HEIGHT  = 500;

    public static final Color USER_MESSAGE_COLOR =  new Color(0, 0, 0);
    public static final Color USER_MESSAGE_HELP_COLOR = new Color(120, 120, 120);

    public static final int STD_PAD = 5;
    public static final EmptyBorder STANDARD_PADDING = new EmptyBorder(STD_PAD, STD_PAD, STD_PAD, STD_PAD);
    public static final EmptyBorder VERTICAL_PADDING = new EmptyBorder(STD_PAD, 0, STD_PAD, 0);
    public static final EmptyBorder HORIZONTAL_PADDING = new EmptyBorder(0, STD_PAD, 0, STD_PAD);
    public static final EmptyBorder TOP_PADDING = new EmptyBorder(STD_PAD, 0, 0,0 );
    public static final EmptyBorder BOTTOM_PADDING = new EmptyBorder(0,0, STD_PAD, 0);
    public static final EmptyBorder LEFT_PADDING = new EmptyBorder(0, STD_PAD, 0, 0);
    public static final EmptyBorder RIGHT_PADDING = new EmptyBorder(0, 0, 0, STD_PAD);
}
