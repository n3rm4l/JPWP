package com.test1.bugTest;


import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Vector2;
import com.test1.Constants;

public class TestGrid {

    private Vector2[] grid;
    public int rows;
    public int columns;
    public ShapeRenderer shapeRenderer;

    public TestGrid() {
        this.rows = Constants.HEIGHT / 64;
        this.columns = Constants.WIDTH / 64;

        grid = new Vector2[rows * columns];

        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < columns; j++) {
                int x = 64 * i;
                int y = 64 * j;

                grid[i * columns + j] = new Vector2(x, y);
            }
        }

        shapeRenderer = new ShapeRenderer();
    }

    public void draw() {
        shapeRenderer.begin(ShapeRenderer.ShapeType.Line);

        for (int i = 0; i < rows; i++) {
            shapeRenderer.line(0, 64 * i, Constants.WIDTH, 64 * i);
        }

        for (int i = 0; i < columns; i++) {
            shapeRenderer.line(64 * i, 0, 64 * i, Constants.HEIGHT);
        }

        shapeRenderer.end();
    }
}