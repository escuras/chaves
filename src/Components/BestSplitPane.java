/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Components;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import javax.swing.JSplitPane;
import javax.swing.plaf.basic.BasicSplitPaneUI;

/**
 *
 * @author toze
 */
public class BestSplitPane extends BasicSplitPaneUI {

    /*
        Code By Joe Rustad and George Lin
     */
    protected Rectangle oldPosition = new Rectangle(0, 0, 0, 0);
    protected Rectangle newPosition = new Rectangle(0, 0, 0, 0);
    protected Rectangle dividerPosition = new Rectangle(0, 0, 0, 0);

    @Override
    protected void dragDividerTo(int location) {
        int dividerSize = this.dividerSize + 1;

        if (getLastDragLocation() != location) {
            if (isContinuousLayout()) {
                setDividerLocation(splitPane, location);
                setLastDragLocation(location);
            } else {
                int lastLoc = getLastDragLocation();

                setLastDragLocation(location);
                if (getOrientation() == JSplitPane.HORIZONTAL_SPLIT) {
                    int splitHeight = splitPane.getSize().height;

                    if (draggingHW) {
                        nonContinuousLayoutDivider.setLocation(getLastDragLocation(), 0);
                    } else {
                        dividerPosition.setBounds(getDividerLocation(splitPane) - 1, 0, dividerSize, splitHeight);
                        oldPosition.setBounds(lastLoc,
                                0, dividerSize, splitHeight);
                        newPosition.setBounds(location,
                                0, dividerSize, splitHeight);

                        Graphics g
                                = splitPane.getGraphics();
                        g.setXORMode(Color.white);

                        if (dividerPosition.intersects(oldPosition)) {
                            if (dividerPosition.x
                                    < lastLoc - 1) {
                                g.fillRect(dividerPosition.x + dividerSize - 1, 0, lastLoc - dividerPosition.x - 1,
                                        splitHeight);
                            } else if (dividerPosition.x > lastLoc - 1) {
                                g.fillRect(lastLoc, 0, dividerPosition.x - lastLoc, splitHeight);
                            }
                        } else {
                            g.fillRect(lastLoc, 0, dividerSize - 1,
                                    splitHeight);
                        }

                        if (dividerPosition.intersects(newPosition)) {
                            if (dividerPosition.x
                                    < location - 1) {
                                g.fillRect(dividerPosition.x + dividerSize - 1, 0, location - dividerPosition.x - 1,
                                        splitHeight);
                            } else if (dividerPosition.x > location - 1) {
                                g.fillRect(location, 0, dividerPosition.x - location, splitHeight);
                            }
                        } else {
                            g.fillRect(location, 0, dividerSize
                                    - 1, splitHeight);
                        }

                        g.setPaintMode();
                    }
                } else {
                    int splitWidth = splitPane.getSize().width;

                    if (draggingHW) {
                        nonContinuousLayoutDivider.setLocation(0,
                                getLastDragLocation());
                    } else {
                        dividerPosition.setBounds(0,
                                getDividerLocation(splitPane) - 1, splitWidth, dividerSize);
                        oldPosition.setBounds(0,
                                lastLoc, splitWidth, dividerSize);
                        newPosition.setBounds(0,
                                location, splitWidth, dividerSize);

                        Graphics g
                                = splitPane.getGraphics();
                        g.setXORMode(Color.white);

                        if (dividerPosition.intersects(oldPosition)) {
                            if (dividerPosition.y
                                    < lastLoc - 1) {
                                g.fillRect(0,
                                        dividerPosition.y + dividerSize - 1, splitWidth, lastLoc - dividerPosition.y);
                            } else if (dividerPosition.y > lastLoc - 1) {
                                g.fillRect(0,
                                        lastLoc - 1, splitWidth, dividerPosition.y - lastLoc);
                            }
                        } else {
                            g.fillRect(0, lastLoc, splitWidth,
                                    dividerSize - 1);
                        }

                        if (dividerPosition.intersects(newPosition)) {
                            if (dividerPosition.y
                                    < location - 1) {
                                g.fillRect(0,
                                        dividerPosition.y + dividerSize - 1, splitWidth, location - dividerPosition.y);
                            } else if (dividerPosition.y > location - 1) {
                                g.fillRect(0,
                                        location - 1, splitWidth, dividerPosition.y - location);
                            }
                        } else {
                            g.fillRect(0, location, splitWidth,
                                    dividerSize - 1);
                        }

                        g.setPaintMode();
                    }
                }
            }
        }
    }
}
