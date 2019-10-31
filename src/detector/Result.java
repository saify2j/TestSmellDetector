package detector;

public class Result {
    private String filepath;
    private String smellpath;
    private int linenumber;

    public Result(String filepath, String smellpath, int linenumber) {
        this.filepath = filepath;
        this.smellpath = smellpath;
        this.linenumber = linenumber;
    }

    public String getFilepath() {
        return filepath;
    }

    public void setFilepath(String filepath) {
        this.filepath = filepath;
    }

    public String getSmellpath() {
        return smellpath;
    }

    public void setSmellpath(String smellpath) {
        this.smellpath = smellpath;
    }

    public int getLinenumber() {
        return linenumber;
    }

    public void setLinenumber(int linenumber) {
        this.linenumber = linenumber;
    }

    @Override
    public String toString() {
        return "Result{" +
                "filepath='" + filepath + '\'' +
                ", smellpath='" + smellpath + '\'' +
                ", linenumber=" + linenumber +
                '}';
    }
}
