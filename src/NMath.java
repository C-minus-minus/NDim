import processing.core.PVector;

public class NMath {

    public static float distance(NPoint a, NPoint b){

        float sum = 0;
        for(int i=0;i<a.coord.length;i++){
            sum += Math.pow(a.coord[i]-b.coord[i],2);
        }
        return (float)Math.sqrt(sum);
    }

    public static PVector get3D(NPoint p,float angle){

        //  if Npoint is less than 3 dimensions
        if(p.coord.length==0){
            return new PVector(0,0);
        }
        else if(p.coord.length==1){
            return new PVector(p.coord[0],0);
        }
        else if(p.coord.length==2){
            return new PVector(p.coord[0],p.coord[1]);
        }

        //  populate with original vertices
        float[] coords = new float[p.coord.length];
        for(int i=0;i<p.coord.length;i++){
            coords[i] = p.coord[i];
        }

        //  translate to 3D
        float sin = (float)Math.sin(angle);
        float cos = (float)Math.cos(angle);
        float depth = coords.length*100-100;
        for(int i=coords.length-1;i>2;i--){

            float v1 = coords[i-1];
            float v2 = coords[i];
            coords[i-1] = (v1*cos-v2*sin);
            coords[i] = (v1*sin+v2*cos);
            for(int j=0;j<i;j++){
                coords[j] = coords[j] * depth / (depth-coords[i]);
            }
        }
        return new PVector(coords[0],coords[1],coords[2]);
    }

}
