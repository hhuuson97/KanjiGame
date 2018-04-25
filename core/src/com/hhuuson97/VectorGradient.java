package com.hhuuson97;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;

class VectorGradient {
    private static final VectorGradient ourInstance = new VectorGradient();
    private ShapeRenderer shapeRenderer;

    static VectorGradient getInstance() {
        return ourInstance;
    }

    private VectorGradient() {
        shapeRenderer = new ShapeRenderer();
    }

    void rect(float x, float y, float width, float height, Color color1, Color color2) {
        shapeRenderer.begin(ShapeRenderer.ShapeType.Filled);
        shapeRenderer.rect(
                x,
                y,
                width,
                height,
                color1,
                color1,
                color2,
                color2
        );
        shapeRenderer.end();
    }
}
