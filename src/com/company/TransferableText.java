package com.company;

import java.awt.*;
import java.awt.datatransfer.DataFlavor;
import java.awt.datatransfer.Transferable;
import java.awt.datatransfer.UnsupportedFlavorException;

public class TransferableText implements Transferable {
    protected static DataFlavor[] supportedFlavors = { DataFlavor.stringFlavor };
    String str;
    public TransferableText(String s) {
        this.str = s;
    }
    public DataFlavor[] getTransferDataFlavors() {
        return supportedFlavors;
    }
    public boolean isDataFlavorSupported(DataFlavor flavor) {
        if (flavor.equals(DataFlavor.stringFlavor))
            return true;
        return false;
    }
    public Object getTransferData(DataFlavor flavor) throws UnsupportedFlavorException {
        if (flavor.equals(DataFlavor.stringFlavor))
            return str;
        else
            throw new UnsupportedFlavorException(flavor);
    }
}
