package org.bytedeco.embeddedpython;

public class NpNdarrayDouble extends NpNdarray {
    public static final long serialVersionUID = 1L;
    public final double[] data;

    public NpNdarrayDouble(double[] data, int[] dimensions, int[] strides) {
        super(dimensions, strides, 8);
        if (data == null) throw new NullPointerException("data = null");
        this.data = data;
    }

    public double[] toArray() {
        if (ndim != 1) throw new RuntimeException("ndim != 1");

        if (strides[0] == itemsize && data.length == dimensions[0]) {
            return data;
        } else {
            int[] indexStrides = indexStrides();
            double[] ary = new double[dimensions[0]];
            for (int i = 0; i < ary.length; i++) {
                ary[i] = data[i * indexStrides[0]];
            }
            return ary;
        }
    }

    public double[][] toArray2d() {
        if (ndim != 2) throw new RuntimeException("ndim != 2");

        int[] indexStrides = indexStrides();
        double[][] ary = new double[dimensions[0]][dimensions[1]];
        for (int i = 0; i < ary.length; i++) {
            for (int j = 0; j < ary[i].length; j++) {
                ary[i][j] = data[i * indexStrides[0] + j * indexStrides[1]];
            }
        }
        return ary;
    }

    public double[][][] toArray3d() {
        if (ndim != 3) throw new RuntimeException("ndim != 3");

        int[] indexStrides = indexStrides();
        double[][][] ary = new double[dimensions[0]][dimensions[1]][dimensions[2]];
        for (int i = 0; i < ary.length; i++) {
            for (int j = 0; j < ary[i].length; j++) {
                for (int k = 0; k < ary[i][j].length; k++) {
                    ary[i][j][k] = data[i * indexStrides[0] + j * indexStrides[1] + k * indexStrides[2]];
                }
            }
        }
        return ary;
    }
}
