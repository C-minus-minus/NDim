import processing.core.PApplet;
import processing.core.PVector;

import java.util.ArrayList;

public class NCube {

    private int dim;
    private NPoint[] points;
    private ArrayList<NLine> lines;

    private float angle;
    float speed;

    public NCube(int dim){

        //  how many dimensions
        this.dim = dim;

        //  set up rotation angle
        angle = 0;
        speed = 0.01f;

        //  set up points
        points = new NPoint[(int)Math.pow(2,dim)];
        for(int i=0;i<points.length;i++){

            float[] point = new float[dim];
            int bit = 1;
            for(int j=0;j<dim;j++){
                point[j] = (bit&i)==0?100:-100;
                bit<<=1;
            }
            points[i] = new NPoint(point);
        }

        int x = 0;
        for(int i=1;i<points.length/2;i*=2){
            for(int j=0;j+i<points.length;j+=i+1){
                x++;
                //lines.add(new NLine(points[j],points[j+i]));
            }
        }
        System.out.println(x);

        //  find lines
        this.lines = new ArrayList<>();

        for(int i=0;i<points.length;i++){
            for(int j=i+1;j<points.length;j++){
                if(NMath.distance(points[i],points[j])==200){
                    lines.add(new NLine(points[i],points[j]));
                }
            }
        }
    }

    public void draw(PApplet app){

        //  increase angle
        angle += speed;

        //  set up looks
        app.strokeWeight(10-dim>1?10-dim:1);
        app.stroke(0,255,0);

        //  draw all lines
        for(int i=0;i<lines.size();i++){
            PVector a = NMath.get3D(lines.get(i).a,angle);
            PVector b = NMath.get3D(lines.get(i).b,angle);
            app.line(a.x,a.y,a.z,b.x,b.y,b.z);
        }

        //  draw all points
        for(int i=0;i<points.length;i++){

            PVector p = NMath.get3D(points[i],angle);
            app.point(p.x,p.y,p.z);
        }
    }
}
