import java.awt.Dimension;
import java.awt.Point;


public class AbsoluteConstraints implements java.io.Serializable  {
        static final long serialVersionUID=5261460716622152494L;

        public int x, y, width=-1, height=-1;


        public AbsoluteConstraints(Point pos) {
            this (pos.x, pos.y);
        }


        public AbsoluteConstraints(int x, int y) {
            this.x=x;
            this.y=y;
        }


        public AbsoluteConstraints(Point pos, Dimension size) {
            this.x=pos.x;
            this.y=pos.y;

            if(size!=null) {
                this.width=size.width;
                this.height=size.height;
            }
        }


        public AbsoluteConstraints(int x, int y, int width, int height) {
            this.x = x;
            this.y = y;
            this.width = width;
            this.height = height;
        }


        public int getX() {
            return x;
        }


        public int getY() {
            return y;
        }


        public int getWidth() {
            return width;
        }


        public int getHeight() {
            return height;
        }


        public String toString() {
            return super.toString() + " [x="+x+", y="+y+", width="+width+", height="+height+"]";
        }


        /**
        @param x represents the x position to be represented by the AbosulteConstraints
        @param y represents the y position to be represented by the AbosulteConstraints
        @param width  The width to be represented by this AbsoluteConstraints or -1 if the component's preferred width should be used  
        @param height The height to be represented by this AbsoluteConstraints or -1 if the component's preferred height should be used
        @param pos  The position to be represented by this AbsoluteConstraints
        @param size The size to be represented by this AbsoluteConstraints or null if the component's preferred size should be used
        **/


}
