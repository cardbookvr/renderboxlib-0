package com.cardbookvr.renderbox.components;

import com.cardbookvr.renderbox.RenderBox;
import com.cardbookvr.renderbox.materials.Material;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.FloatBuffer;
import java.nio.ShortBuffer;

/**
 * RenderObject abstract class
 * Created by Schoen and Jonathan on 3/7/2016.
 *
 * Generic class for objects that are drawn by the camera
 */
public abstract class RenderObject extends Component {
    private static final String TAG = "RenderObject";

    protected Material material;
    public static float[] model;
    public static float[] lightingModel;


    public RenderObject(){
        super();
        RenderBox.instance.renderObjects.add(this);
    }

    protected static FloatBuffer allocateFloatBuffer(float[] data){
        ByteBuffer bbVertices = ByteBuffer.allocateDirect(data.length * 4);
        bbVertices.order(ByteOrder.nativeOrder());
        FloatBuffer buffer = bbVertices.asFloatBuffer();
        buffer.put(data);
        buffer.position(0);
        return buffer;
    }

    protected static ShortBuffer allocateShortBuffer(short[] data){
        ByteBuffer bbVertices = ByteBuffer.allocateDirect(data.length * 2);
        bbVertices.order(ByteOrder.nativeOrder());
        ShortBuffer buffer = bbVertices.asShortBuffer();
        buffer.put(data);
        buffer.position(0);
        return buffer;
    }

    public void draw(float[] view, float[] perspective){
        //Compute position every frame in case it changed
        transform.drawMatrices();
        material.draw(view, perspective);
    }

}
