package com.company;
import java.awt.*;

public interface ITransport {
    void SetPosition(int x, int y, int width, int height);

    void MoveObject(Direction direction);

    void DrawObject(Graphics g);
}
