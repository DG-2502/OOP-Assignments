public class MicroscopeAdapter extends Hammer{
    private Microscope microscope;
    public MicroscopeAdapter(Microscope mic){
        microscope = mic;
    }

    @Override
    public boolean hit(Nail nail){
        microscope.bang();
        return false;
    }
}