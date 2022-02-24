public class Major {

    private int majorid;

    public int getMajorId() {
        return majorid;
    }

    public void setMajorId(int majorId) {
        this.majorid = majorId;
    }

    public String getMajorName() {
        return majorname;
    }

    public void setMajorName(String majorName) {
        this.majorname = majorName;
    }

    private String majorname;

    @Override
    public String toString() { return "Major[id=" + this.majorid + ",name=" + this.majorname + "]"; }
}
