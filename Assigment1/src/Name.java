public class Name {
    private String fname;
    private String lname;

    private Name() {
    }

    public Name(String fname, String lname) {
        this.fname = fname;
        this.lname = lname;
    }

    public String getFname() {
        return fname;
    }

    public String getLname() {
        return lname;
    }


    public void setFname(String fname) {
        this.fname = fname;
    }

    public void setLname(String lname) {
        this.lname = lname;
    }


    private String getName() {
        return lname + ", " + fname;
    }

    @Override
    public String toString() {
        return getName();
    }
}