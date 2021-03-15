import peasy.PeasyCam;
import processing.core.PApplet;

public class NDimDisplay extends PApplet {

    private NCube hyperCube;
    private NSphere nSphere;
    private PeasyCam cam;

    private int dim;
    private int vertexes;
    private int edges;

    private String[] names;

    public static void main(String[] args) {
        PApplet.main("NDimDisplay");
    }

    public void settings(){
        //size(800,800,P3D);
        fullScreen(P3D);
    }

    public void setup(){
        this.hyperCube = new NCube(0);
        this.cam = new PeasyCam(this,500);
        dim = 0;
        vertexes = 1;
        edges = 0;
        nSphere = new NSphere(3);

        this.names = new String[]{"Point","Line Segment","Square","Cube",
        "Tesseract","Penteract","Hexeract","Hepteract","Octeract","Enneract"};
    }

    float angle = 0;
    public void draw(){
        angle += 0.05;
        background(0);
        //rotateX(degrees(90));
        //rotateZ(angle);
        this.hyperCube.draw(this);
        //this.nSphere.draw(this);
        cam.beginHUD();
        fill(255,0,0);
        textSize(30);
        text("Name: "+dim+"-Cube (aka "+names[dim]+")",0,30);
        text("Dimensions: "+ dim,0,2*30);
        text("Vertices: "+ vertexes,0,3*30);
        text("Edges: "+ edges,0,4*30);
        cam.endHUD();
    }

    public void keyPressed(){
        if(key >= '0'&&key<='9'){
            this.hyperCube = new NCube(key-'0');
            dim = key-'0';
            vertexes = (int)Math.pow(2,dim);
            edges = dim* vertexes /2;
        }
    }
}