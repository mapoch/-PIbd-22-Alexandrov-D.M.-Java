package com.company;

import java.awt.*;
import java.awt.datatransfer.DataFlavor;
import java.awt.datatransfer.UnsupportedFlavorException;
import java.awt.datatransfer.*;

public class TransferableAdd implements Transferable {
    protected static DataFlavor addFlavor = new DataFlavor(Additionals_Draw.class, "A Color Object");
    protected static DataFlavor[] supportedFlavors = { addFlavor };
    Additionals_Draw add;
    public TransferableAdd(Additionals_Draw a) {
        this.add = a;
    }
    public DataFlavor[] getTransferDataFlavors() {
        return supportedFlavors;
    }
    public boolean isDataFlavorSupported(DataFlavor flavor) {
        if (flavor.equals(addFlavor) || flavor.equals(DataFlavor.stringFlavor))
            return true;
        return false;
    }
    public Object getTransferData(DataFlavor flavor) throws UnsupportedFlavorException {
        if (flavor.equals(addFlavor))
            return add;
        else if (flavor.equals(DataFlavor.stringFlavor))
            return add.toString();
        else
            throw new UnsupportedFlavorException(flavor);
    }
}
