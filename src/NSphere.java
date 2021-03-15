import processing.core.PApplet;
import processing.core.PVector;
import java.util.ArrayList;

public class NSphere {

    ArrayList<PVector> vertices;

    NPoint[][][] d3V;

    float angle = 0;

    public NSphere(int dim){

        /*
        //  generate points
        int points = 50;
        int radius = 100;
        vertices = new ArrayList<>();
        for(int i=0;i<points;i++){
            for(int j=0;j<points;j++){
                float alpha = (float)(2*Math.PI)/points*i;
                float polar = (float)(2*Math.PI)/points*j;
                float x = (float)(radius * Math.sin(polar) * Math.cos(alpha));
                float y = (float)(radius * Math.sin(polar) * Math.sin(alpha));
                float z = (float)(radius * Math.cos(polar));
                vertices.add(new PVector(x,y,z));
            }
        }*/

        int scale = 20;
        int radius = 100;
        d3V = new NPoint[scale][scale][scale];
        for(int i=0;i<scale;i++){
            for(int j=0;j<scale;j++){
                for(int k=0;k<scale;k++){

                    float theta1 = (float)(2*Math.PI)/scale*i;
                    float theta2 = (float)(2*Math.PI)/scale*j;
                    float theta3 = (float)(2*Math.PI)/scale*k;
                    float x = (float)(radius * Math.sin(theta1) * Math.cos(theta2)*Math.sin(theta3));
                    float y = (float)(radius * Math.sin(theta1) * Math.sin(theta2)*Math.sin(theta3));
                    float z = (float)(radius * Math.cos(theta1)*Math.sin(theta2));
                    float w = (float)(radius*Math.cos(theta1));
                    d3V[i][j][k] = new NPoint(new float[]{x,y,z,w});
                }
            }
        }
    }

    public void draw(PApplet app){
        angle += 0.01;
        app.strokeWeight(8);
        app.stroke(0,255,0);
        /*
        for(int i=0;i<50;i++) {
            for (int j = 0; j < 50; j++) {
                PVector v = vertices.get(i * 50 + j);
                app.point(v.x, v.y, v.z);
                //if(true)continue;
                //  draw lines

                try{
                    PVector v3 = vertices.get(i * 50 + j);
                    app.line(v.x, v.y, v.z,v3.x,v3.y,v3.z);}
                catch (Exception e){}

                try{
                    PVector v4 = vertices.get(i * 50 + (j+1)%50);
                    app.line(v.x, v.y, v.z,v4.x,v4.y,v4.z);}
                catch (Exception e){}
                //if(true)continue;
                try{
                PVector v1 = vertices.get((i+1)%50 * 50 + j);
                app.line(v.x, v.y, v.z,v1.x,v1.y,v1.z);}
                catch (Exception e){}

                try{
                PVector v2 = vertices.get((i+1)%50 * 50 + (j+1)%50);
                app.line(v.x, v.y, v.z,v2.x,v2.y,v2.z);}
                catch (Exception e){}
                if(true)continue;
            }
        }
        */

        int scale = 20;
        for(int i=0;i<scale;i++) {
            for (int j = 0; j < scale; j++) {
                for (int k = 0; k < scale; k++) {
                    PVector p = NMath.get3D(d3V[i][j][k],angle);
                    PVector[] ps = new PVector[6];
                    ps[0] = NMath.get3D(d3V[i][j][(k+1)%scale],angle);
                    ps[1] = NMath.get3D(d3V[i][j][(k-1)>0?(k-1):scale-1],angle);
                    ps[2] = NMath.get3D(d3V[i][(j+1)%scale][k],angle);
                    ps[3] = NMath.get3D(d3V[i][(j-1)>0?(j-1):scale-1][k],angle);
                    ps[4] = NMath.get3D(d3V[(i+1)%scale][j][k],angle);
                    ps[5] = NMath.get3D(d3V[(i-1)>0?(i-1):scale-1][j][k],angle);

                    app.strokeWeight(8);
                    app.point(p.x,p.y,p.z);
                    app.strokeWeight(2);
                    for(int a=0;a<6;a++){
                        app.line(p.x,p.y,p.z,ps[a].x,ps[a].y,ps[a].z);
                    }
                }
            }
        }
    }
}
